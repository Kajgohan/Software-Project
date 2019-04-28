package bishopfish.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;

// database has either varchar or integer columns
// primary key must be string
// table only has one primary key of the id
public class DBUpdater {
    private Connection connection;
    private String connString;      // connection string that can be passed into getConnection
    private String tableName;       // name of the table this dbupdater points too
    private String primaryKeyName;  // the primary key of the table. needed for where for add, update, and selects
    private String colString;       // a string for the body of the Create Table command with all the columns and their types

    private String removeQueryString;
    private String addQueryString;
    private String blankSelectString;

    private String csvDelimiter = ",";

    private static final Logger LOGGER = Logger.getLogger(DBUpdater.class.getName());


    private DBUpdater(String connString) {
        //this.connString = "jdbc:derby:/tmp/testDB2;create=true";  // temporary bishopfish.db for testing
        this.connString = connString;
        //System.out.println(connString);
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            this.connection = DriverManager.getConnection(this.connString);
        } catch (SQLException e) {
            //e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);

        }
        //this.connString = connectionString;
    }

    public DBUpdater(DBMI.DBInfo info) {
        this(info.getDbConnString());
        this.setTable(info.tableName.toUpperCase(), info.primaryKey.toUpperCase());
        this.colString = info.colString;
    }

    public String getTableName() {
        return tableName;
    }

    private void setTable(String tableName, String primaryKeyName) {
        this.tableName = tableName;
        //System.out.println(this.tableName);
        this.primaryKeyName = primaryKeyName;
        this.removeQueryString = "DELETE FROM " + this.tableName + " WHERE " + primaryKeyName + " = ?";
        this.blankSelectString = "SELECT * FROM " + this.tableName + " WHERE 1=2";
        updateAddQueryString();
    }

    /**
     * recreate the addQueryString from the database calculated number of columns
     */
    public void updateAddQueryString() {
        String temp = ("INSERT INTO " + this.tableName + " VALUES (");
        for (int i = 0; i < getNumCols(); i++) {
            temp += "?";
            if (i != getNumCols() - 1) {
                temp += ", ";
            }
        }
        temp += ")";
        this.addQueryString = temp;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getAddQueryString() {
        return addQueryString;
    }

    /**
     * check if the table exists
     *
     * @return boolean of if the table exists
     */
    public boolean tableExists() {
        ResultSet tables;
        try {
            tables = this.connection.getMetaData().getTables(null, null, tableName.toUpperCase(), null);
//            boolean one = tables.next();
//            boolean two = tables.next();
//            System.out.println(one);
//            System.out.println(two);
//            return one;
            boolean ret = tables.next();
            tables.close();
            return ret;
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.println("tableExists crashed");
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return false;
    }


    /**
     * create a table in the database if it doesn't already exist. ignores error from table already existing
     *
     * @return a boolean of if the table was created
     */
    public boolean createTable() {
        try {
            Statement stmt = connection.createStatement();
          //  System.out.println("CREATE TABLE " + tableName + " (" + this.colString + ")");
            stmt.execute("CREATE TABLE " + tableName + " (" + this.colString + ")");
            stmt.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            String alreadyExistsMsg = String.format("Table/View '%s' already exists in Schema 'APP'.", tableName);
            if (e.getMessage().equals(alreadyExistsMsg)) {
                LOGGER.log(Level.WARNING, String.format("Table %s already exists.", tableName));
            } else {
                LOGGER.log(Level.SEVERE, "Unexpected Error", e);
            }
            return false;   // create failed
        }
        //System.out.println(colString);
        updateAddQueryString();
        return true;       // create succeeded
    }

    /**
     * drop the table. ignore error bc probably is just table did not exist
     *
     * @return a boolean of weather the drop worked
     */
    public boolean dropTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE " + tableName);
            stmt.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            String doesNotExistsMsg = String.format("'DROP TABLE' cannot be performed on '%s' because it does not exist.", tableName);
            if (e.getMessage().equals(doesNotExistsMsg)) {
                LOGGER.log(Level.WARNING, String.format("Table %s couldn't be dropped because it doesn't exist.", tableName));
            } else {
                LOGGER.log(Level.SEVERE, "Unexpected Error", e);
            }
            return false;   // drop failed
        }
        updateAddQueryString();
        return true;        // drop succeeded
    }

    /**
     * get ResultSet of all columns in database table
     *
     * @return result set of select *. may be null if select failed
     */
    public ResultSet getFullResultSet() {
        ResultSet results;
        try {
            Statement stmt = connection.createStatement();
            results = stmt.executeQuery("SELECT * FROM " + this.tableName);
        } catch (SQLException e) {
            results = null;
            //e.printStackTrace();
            // allow it to return a null results set
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return results;
    }

    // crashes if array list of the wrong length

    /**
     * add entry to the table. Ignores extra elements when list is too long
     * @param values the values to add. in the order of the columns in the database
     * @return number of rows affected
     * @throws IndexOutOfBoundsException when list is too short
     */
    public int addEntry(ArrayList<String> values) throws IndexOutOfBoundsException {
        int rowAffected = 0;
        if (tableExists()) {
            try {
                //System.out.println(addQueryString);
                PreparedStatement pstmt = connection.prepareStatement(addQueryString);
                for (int i = 0; i < getNumCols(); i++) {
                    pstmt.setString(i+1, values.get(i));
                }
                rowAffected = pstmt.executeUpdate();
                if (rowAffected != 1) {
           //         System.out.println("OH NO!!! MULTIPLE ENTRIES WERE ADDED? ¯\\_(ツ)_/¯");
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                // beginning
                String uniqueErrorMsgBeginning = "The statement was aborted because it would have caused a duplicate key value in a unique or primary key constraint or unique index";
                if (e.getMessage().startsWith(uniqueErrorMsgBeginning)) {
                    LOGGER.log(Level.SEVERE, String.format("Couldn't add %s to %s because that key already exists.", values.toString(), tableName));
                } else {
                    LOGGER.log(Level.SEVERE, "Unexpected Error", e);
                }

                //System.out.println(e);
            }
        } else {
            LOGGER.log(Level.SEVERE, String.format("Tried adding an entry to but the table doesn't exist. Table: %s. Entry: %s.", tableName, values.toString()));
        }
        return rowAffected;
    }

    //

    /**
     *
     * @param values all values besides the id
     * @return the number of values change, the generated id
     * @throws IndexOutOfBoundsException
     */
    public Object[] addEntryR(ArrayList<String> values) throws IndexOutOfBoundsException {
        values.add(0, randomUUID().toString().replace('-', Character.MIN_VALUE));
        Integer changed = addEntry(values);
        return new Object[] {changed, values.get(0)};
    }


    /**
     * remove the row from the database with the given value for the primary key
     *
     * @param id the value of the primary key to remove
     * @return the number of rows affected
     */
    public int removeEntry(String id) {
        int rowAffected = 0;
        if (tableExists()) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(removeQueryString);
                pstmt.setString(1, id);
                rowAffected = pstmt.executeUpdate();
                if (rowAffected > 1) {
            //        System.out.println("OH NO!!! MULTIPLE ENTRIES (" + rowAffected + ") WERE DELETED WHILE REMOVING ID " + id + " ¯\\_(ツ)_/¯");
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                //System.out.println(e);
                LOGGER.log(Level.WARNING, "Unexpected Error", e);
            }
        } else {
            LOGGER.log(Level.SEVERE, "Tried removing an entry but the table doesn't exist.");
        }
        return rowAffected;
    }

    public ArrayList<String> getEntry(String id) {
        ArrayList<String> values = new ArrayList<>();
        if (tableExists()) {
            try {
                String getString = "SELECT * FROM " + this.tableName + " WHERE " + primaryKeyName + " = ?";     // move this to be stored globally
                PreparedStatement pstmt = connection.prepareStatement(getString);
                pstmt.setString(1, id);
                ResultSet results = pstmt.executeQuery();
                while (results.next()) {
                    for (int i = 1; i <= getNumCols(); i++) {
                        values.add(results.getString(i));
                    }
                }
                results.close();
            } catch (SQLException e) {
                //e.printStackTrace();
                LOGGER.log(Level.WARNING, "Unexpected Error", e);
            }
        } else {
            LOGGER.log(Level.SEVERE, "Tried getting an entry but the table doesn't exist.");
        }

        return values;
    }

    /**
     * updated the entry according to the id
     * @param id the id in the primary key column
     * @param values changed values not including the primary key. null if value has not changed
     * @return number of rows affected
     */
    public int updateEntry(String id, ArrayList<String> values) {
        int rowAffected = 0;
        if (tableExists()) {
            try {
                String updateString = getUpdateQueryString(values);
                if (updateString.isEmpty()) {
                    return rowAffected;
                }
                PreparedStatement pstmt = connection.prepareStatement(updateString);
                int j = 1;
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i) != null)
                        pstmt.setString(j++, values.get(i));
                }
                pstmt.setString(j, id);
                rowAffected = pstmt.executeUpdate();
                if (rowAffected > 1) {
           //         System.out.println("OH NO!!! MULTIPLE ENTRIES (" + rowAffected + ") WERE UPDATED WHILE UPDATING ID " + id + " ¯\\_(ツ)_/¯");
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                //System.out.println(e);
                LOGGER.log(Level.WARNING, "Unexpected Error", e);
            }
        } else {
            LOGGER.log(Level.SEVERE, "Tried updating an entry but the table doesn't exist.");
        }
        return rowAffected;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to close connection");
        }
    }

    public void open() {
        try {
            this.connection = DriverManager.getConnection(this.connString);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to open connection");
        }
    }

    // returns empty string if not safe to run the update (no columns to change)
    public String getUpdateQueryString(ArrayList<String> values) {
        String colValuesString = "";
        ArrayList<String> colNames = getColumnNames();
        for (int i = 0; i < colNames.size() - 1; i++) {
            if (values.get(i) != null) {
                colValuesString += (colValuesString.isEmpty()) ? "" : ", ";    // if its not the first one, use a comma
                colValuesString += colNames.get(i+1) + " = ?";
            }
        }
        if (colValuesString.isEmpty()) {
            return "";  // if no value to update return empty string so we know to do nothing
        }
        return "UPDATE " + this.tableName + " SET " + colValuesString + " WHERE " + this.primaryKeyName + " = ?";
    }

//    public String getPrimaryKey() {
//        try {
//            return connection.getMetaData().getPrimaryKeys(null, null, "%" + tableName + "%").get;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "nope";
//        }
//    }

    /**
     * get the number of the columns in the selected table. check if the table exist first
     * @return number of columns; -1 if table doesn't exist or sql crash
     */
    public int getNumCols() {
        Statement stmt;
        int columnsNumber = -1;
        if (tableExists()) {    // only get col nums from table if it exists
            try {
                stmt = connection.createStatement();
                //System.out.println(blankSelectString);
                ResultSet results = stmt.executeQuery(blankSelectString); // empty select so faster
                ResultSetMetaData rsmd = results.getMetaData();
                //System.out.println(rsmd.getColumnCount());
                columnsNumber = rsmd.getColumnCount();
                results.close();
            } catch (SQLException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Unexpected Error", e);
            }
        }
        return columnsNumber;
    }

    /**
     * get an array of all column names. check if table exist first
     * @return array list of the column names. empty array list if table doesn't exist or sql crash
     */
    public ArrayList<String> getColumnNames() {
        ArrayList<String> columnNames = new ArrayList<>();
        if (tableExists()) {    // only get names from table if it exists
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(blankSelectString);
                ResultSetMetaData rsmd = rs.getMetaData();
                rs.close();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(rsmd.getColumnName(i));
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Unexpected Error", e);
            }
        }
        return columnNames;
    }

    public HashMap<String, ArrayList<String>> getData(ResultSet results) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        ArrayList<String> fieldValues;
        try {
            while (results.next()) {
                fieldValues = new ArrayList<>();
                for (String colName : getColumnNames()) {
                    fieldValues.add(results.getString(colName));
                }
                result.put(results.getString(primaryKeyName), fieldValues);
            }
            results.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.println(e);
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
        }
        return result;
    }

    public HashMap<String, ArrayList<String>> getHashMap() {
        return getData(getFullResultSet());
    }


    /**
     * read in the csv and add each row as an entry in the database
     *
     * @param csvFile the path to the csv file to fill into the database
     */
    public void fillTableFromCsv(File csvFile, boolean hasHeader, boolean biGraph) {
        if (csvFile != null) {    // if csv file exist
            try (Scanner scanner = new Scanner(csvFile)) {
                ArrayList<String> rowData;
                if (hasHeader && scanner.hasNextLine()) {   // ignore first line
                    scanner.nextLine();
                }
                // add entries to table
                while (scanner.hasNextLine()) {
                    rowData = this.getCsvLineData(scanner.nextLine());     // get data from csv
                    this.addEntry(rowData);
                    if (biGraph) {
                        ArrayList<String> biEntry = new ArrayList<>();
                        biEntry.add(rowData.get(2) + "_" + rowData.get(1));
                        biEntry.add(rowData.get(2));
                        biEntry.add(rowData.get(1));
                        this.addEntry(biEntry);
                    }
                }
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Couldn't load from csv because the file could not be found.", e);
            }
            //globalConnection.commit();
        }
    }

    private ArrayList<String> getCsvLineData(String line) {
        ArrayList<String> values = new ArrayList<>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(csvDelimiter);
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next());
        }
        return values;
    }


    public String getCsvString() {
        Map<String, ArrayList<String>> allData = this.getData(getFullResultSet());
        String csvString = "";
        ArrayList<String> colNames = this.getColumnNames();
        csvString += this.makeCsvLine(colNames) + "\n";
        for (Map.Entry<String, ArrayList<String>> entry : allData.entrySet()) {
            ArrayList<String> rowValues = new ArrayList<>();
            for (String s : entry.getValue()) {
                if (!s.equals("")) {
                    rowValues.add(s);
                }
            }
            //String[] a = new String[rowValues.size()];
            csvString += this.makeCsvLine(rowValues) + "\n";
        }
        return csvString;
    }

    private String makeCsvLine(ArrayList<String> lineData) {
        String result = "";
        for (int i = 0; i < lineData.size(); i++) {
            result = result.concat(lineData.get(i));
            if (i < lineData.size() - 1) {
                result = result.concat(csvDelimiter);
            }
        }
        return result;
    }


    /**
     * get ResultSet of given sql command
     *
     * @return result set of select
     */
    public ResultSet getResultSet(String q) {
        ResultSet results;
        try {
            Statement stmt = connection.createStatement();
            results = stmt.executeQuery(q);
        } catch (SQLException e) {
            results = null;
            //e.printStackTrace();
            // allow it to return a null results set
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
        }
        return results;
    }



}
