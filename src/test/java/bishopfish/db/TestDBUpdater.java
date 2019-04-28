package bishopfish.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

public class TestDBUpdater {


    @BeforeEach
    void setUp() {
        DBMI.DBInfo info = DBMI.TestDBUpdater2.value;
        // manually drop table
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            Connection connection = DriverManager.getConnection(info.getDbConnString());
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE " + info.getTableName());
        } catch (SQLException e) {
            String doesNotExistsMsg = String.format("'DROP TABLE' cannot be performed on '%s' because it does not exist.", info.getTableName());
            if (e.getMessage().equals(doesNotExistsMsg)) {
                // table already didn't exist
            } else {
                System.out.println("SETUP FAILED");
                e.printStackTrace();
            }
        }
    }

    @Test
    void testConnection() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertNotNull(dbu.getConnection());
    }

    @Test
    void testCreateExistsDropTable() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertFalse(dbu.tableExists());
        assertTrue(dbu.createTable());
        assertTrue(dbu.tableExists());
        assertTrue(dbu.dropTable());
        assertFalse(dbu.tableExists());
        assertFalse(dbu.dropTable());
        assertFalse(dbu.tableExists());
        assertTrue(dbu.createTable());
        assertTrue(dbu.tableExists());
        assertFalse(dbu.createTable());
        assertTrue(dbu.tableExists());
        assertTrue(dbu.dropTable());
        assertFalse(dbu.tableExists());
    }

    @Test
    void testGetColNum(){
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertTrue(dbu.createTable());
        assertNotEquals(-1, dbu.getNumCols());
        assertEquals(3, dbu.getNumCols());
        assertTrue(dbu.dropTable());
        assertEquals(-1, dbu.getNumCols());
    }

//    @Test
//    void testGetPrimaryKey() {
//        bishopfish.bishopfish.db.DBUpdater dbu = new bishopfish.bishopfish.db.DBUpdater();
//        dbu.setTableName("table1");
//        String colString = "ID VARCHAR(5) NOT NULL PRIMARY KEY, VALUE1 VARCHAR(10), VALUE2 VARCHAR(10)";
//        assertTrue(dbu.createTable(colString));
//        assertEquals("ID", dbu.getPrimaryKey());
//
//    }


    @Test
    void testUpdateAddQueryString() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        dbu.updateAddQueryString();
        String tn = DBMI.TestDBUpdater2.value.getTableName();   // table name
        assertEquals("INSERT INTO " + tn + " VALUES ()", dbu.getAddQueryString());

        assertTrue(dbu.createTable());
        assertEquals("INSERT INTO " + tn + " VALUES (?, ?, ?)", dbu.getAddQueryString());
        assertTrue(dbu.dropTable());
        assertEquals("INSERT INTO " + tn + " VALUES ()", dbu.getAddQueryString());
    }

    @Test
    void testGetUpdateQueryString() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater3.value);
        ArrayList<String> one = new ArrayList<String>() {{ add("ONEONE"); add("ONE"); add("O");}};
        ArrayList<String> two = new ArrayList<String>() {{ add("TWOTWO"); add(null);  add("TW");}};
        ArrayList<String> three = new ArrayList<String>() {{ add(null); add("THREE"); add("THR");}};
        ArrayList<String> four = new ArrayList<String>() {{ add(null); add(null); add("F");}};
        ArrayList<String> five = new ArrayList<String>() {{ add(null); add("FIVE"); add(null);}};
        ArrayList<String> six = new ArrayList<String>() {{ add(null); add(null); add(null);}};
        ArrayList<String> seven = new ArrayList<String>() {{ add("SEVEN"); add(null); add(null);}};
        assertEquals("", dbu.getUpdateQueryString(one));

        assertTrue(dbu.createTable());
        String tn = DBMI.TestDBUpdater2.value.getTableName();   // table name
        String pk = DBMI.TestDBUpdater2.value.getPrimaryKey();  // primary key name
        assertEquals("UPDATE " + tn + " SET VALUE1 = ?, VALUE2 = ?, VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(one));
        assertEquals("UPDATE " + tn + " SET VALUE1 = ?, VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(two));
        assertEquals("UPDATE " + tn + " SET VALUE2 = ?, VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(three));
        assertEquals("UPDATE " + tn + " SET VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(four));
        assertEquals("UPDATE " + tn + " SET VALUE2 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(five));
        assertEquals("", dbu.getUpdateQueryString(six));
        assertEquals("UPDATE " + tn + " SET VALUE1 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(seven));
        assertEquals("UPDATE " + tn + " SET VALUE1 = ?, VALUE2 = ?, VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(one));
        assertEquals("UPDATE " + tn + " SET VALUE1 = ?, VALUE2 = ?, VALUE3 = ? WHERE " + pk + " = ?", dbu.getUpdateQueryString(one));

        assertTrue(dbu.dropTable());
        assertEquals("", dbu.getUpdateQueryString(one));
    }


    @Test
    void testAddGetEntry() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); add("ONEONE"); add("ONE"); }};
        assertEquals(1, dbu.addEntry(one));
        assertEquals(one, dbu.getEntry(one.get(0)));
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWOTWO"); add("TWO"); }};
        assertEquals(1, dbu.addEntry(two));
        assertEquals(two, dbu.getEntry(two.get(0)));
        ArrayList<String> three = new ArrayList<String>() {{ add("3"); add("THREETHREE"); add("THREE"); }};
        assertEquals(1, dbu.addEntry(three));
        assertEquals(three, dbu.getEntry(three.get(0)));
        ArrayList<String> four = new ArrayList<String>() {{ add("1"); add("FOURFOUR"); add("FOUR"); }};
        assertEquals(0, dbu.addEntry(four));
        assertEquals(one, dbu.getEntry(four.get(0)));
        ArrayList<String> five = new ArrayList<String>() {{ add("5"); add("FIVEFIVEFIVEFIVE"); add("FIVEFIVE"); }};
        assertEquals(0, dbu.addEntry(five));
        assertEquals(new ArrayList<String>(), dbu.getEntry(five.get(0)));
        assertTrue(dbu.dropTable());
    }

    @Test
    void testAddSizeMissMatchEntry() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); }};
        assertThrows(IndexOutOfBoundsException.class, ()->{dbu.addEntry(one);});
        assertEquals(new ArrayList<String>(), dbu.getEntry(one.get(0)));
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWOTWO");}};
        assertThrows(IndexOutOfBoundsException.class, ()->{dbu.addEntry(two);});
        assertEquals(new ArrayList<String>(), dbu.getEntry(two.get(0)));
        // !!!
        ArrayList<String> four = new ArrayList<String>() {{ add("4"); add("FOURFOUR"); add("FOUR"); add(""); }};
        assertEquals(1, dbu.addEntry(four));
        assertEquals(new ArrayList<String>() {{ add("4"); add("FOURFOUR"); add("FOUR");}}, dbu.getEntry(four.get(0)));
        ArrayList<String> five = new ArrayList<String>() {{ add("5"); add("FIVEFIVE"); add("FIVE"); add("X"); add("Y"); }};
        assertEquals(1, dbu.addEntry(five));
        assertEquals(new ArrayList<String>() {{ add("5"); add("FIVEFIVE"); add("FIVE");}}, dbu.getEntry(five.get(0)));
        ArrayList<String> six = new ArrayList<String>() {{ add("6"); add("SIXSIX"); add("SIX"); add(null); }};
        assertEquals(1, dbu.addEntry(six));
        assertEquals(new ArrayList<String>() {{ add("6"); add("SIXSIX"); add("SIX");}}, dbu.getEntry(six.get(0)));
        ArrayList<String> seven = new ArrayList<String>() {{ add("7"); add(null); add(null); }};
        assertEquals(1, dbu.addEntry(seven));
        assertEquals(seven, dbu.getEntry(seven.get(0)));
        assertTrue(dbu.dropTable());
    }

    @Test
    void testRemoveGetEntry() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); add("ONEONE"); add("ONE"); }};
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWOTWO"); add("TWO"); }};
        assertEquals(1, dbu.addEntry(one));
        assertEquals(one, dbu.getEntry(one.get(0)));
        assertEquals(1, dbu.addEntry(two));
        assertEquals(two, dbu.getEntry(two.get(0)));
        assertEquals(1, dbu.removeEntry(two.get(0)));
        assertEquals(new ArrayList<String>(), dbu.getEntry(two.get(0)));
        assertEquals(0, dbu.removeEntry("3"));
        assertEquals(one, dbu.getEntry(one.get(0)));
        assertTrue(dbu.dropTable());
    }



    @Test
    void testUpdateGetEntry() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater2.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); add("ONEONE"); add("ONE"); }};
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWOTWO"); add("TWO"); }};
        assertEquals(1, dbu.addEntry(one));
        assertEquals(one, dbu.getEntry(one.get(0)));
        assertEquals(1, dbu.addEntry(two));
        assertEquals(two, dbu.getEntry(two.get(0)));
        ArrayList<String> onev2 = new ArrayList<String>() {{ add("ONEONEV2"); add(null); }};
        ArrayList<String> twov2 = new ArrayList<String>() {{ add(null); add(null); }};
        ArrayList<String> twov3 = new ArrayList<String>() {{ add(null); add("TWOV2"); }};
        assertEquals(1, dbu.updateEntry("1", onev2));
        assertEquals(new ArrayList<String>() {{ add("1"); add("ONEONEV2"); add("ONE"); }}, dbu.getEntry(one.get(0)));
        assertEquals(0, dbu.updateEntry("2", twov2));
        assertEquals(two, dbu.getEntry(two.get(0)));
        assertEquals(1, dbu.updateEntry("2", twov3));
        assertEquals(new ArrayList<String>() {{ add("2"); add("TWOTWO"); add("TWOV2"); }}, dbu.getEntry(two.get(0)));
        assertEquals(1, dbu.removeEntry(two.get(0)));
        assertEquals(new ArrayList<String>(), dbu.getEntry(two.get(0)));
        assertTrue(dbu.dropTable());
    }

    // not compleat case just ideal case !!!
    @Test
    void testCsv() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater3.value);
        File csvInFile = new File("src/test/resources/testDBUpdaterIn.csv");
        assertTrue(dbu.createTable());
        dbu.fillTableFromCsv(csvInFile, false, false);
        ArrayList<String> a = new ArrayList<String>() {{ add("A"); add("A2"); add("A3"); add("A4"); }};
        ArrayList<String> b = new ArrayList<String>() {{ add("B"); add("B2"); add("B3"); add("B4"); }};
        ArrayList<String> c = new ArrayList<String>() {{ add("C"); add("C2"); add("C3"); add("C4"); }};
        ArrayList<String> d = new ArrayList<String>() {{ add("D"); add("D2"); add("D3"); add("D4"); }};
        assertEquals(a, dbu.getEntry("A"));
        assertEquals(b, dbu.getEntry("B"));
        assertEquals(c, dbu.getEntry("C"));
        assertEquals(d, dbu.getEntry("D"));
        String csvContent = "ID,VALUE1,VALUE2,VALUE3\n" +
                            "A,A2,A3,A4\n" +
                            "B,B2,B3,B4\n" +
                            "C,C2,C3,C4\n" +
                            "D,D2,D3,D4\n";
        assertEquals(csvContent, dbu.getCsvString());
    }

    // not compleat case just ideal case !!!
    @Test
    void testGetData() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater3.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); add("ONE1"); add("ONE2"); add("ONE3"); }};
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWO1"); add("TWO2"); add("TWO3"); }};
        assertEquals(1, dbu.addEntry(one));
        assertEquals(one, dbu.getEntry(one.get(0)));
        assertEquals(1, dbu.addEntry(two));
        assertEquals(two, dbu.getEntry(two.get(0)));
        assertEquals(new HashMap<String, ArrayList<String>>() {{ put(one.get(0), one); put(two.get(0), two); }}, dbu.getData(dbu.getFullResultSet()));




    }

    @Test
    void testGetColumnNames() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdater3.value);
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("ID"); add("VALUE1"); add("VALUE2"); add("VALUE3"); }};
        assertEquals(one, dbu.getColumnNames());


    }


    @Test
    void testAddAutoId() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBUpdaterAutoId2.value);
        dbu.dropTable();
        assertTrue(dbu.createTable());
        ArrayList<String> al = new ArrayList<>();
        Object[] results;
        for (int i = 0; i < 10000; i++) {
            al.clear();
            al.add("name" + i);
            al.add("value" + i);
            results = dbu.addEntryR(al);
            assertEquals(1, results[0]);
            assertNotNull(results[1]);
            assertEquals(36, ((String) results[1]).length());


        }

    }




}
