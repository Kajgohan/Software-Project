package bishopfish.db;

import bishopfish.map.Node;
import bishopfish.map.NodeEntry;
import bishopfish.utils.FeatureTypeEntry;
import bishopfish.utils.PermissionDecoderEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * class where you can write any of your own custom sql commands
 */
public class DBCustom {
    private static final Logger LOGGER = Logger.getLogger(DBUpdater.class.getName());

    public DBCustom() {

    }

    /**
     * take in a result set with one column selected
     * @param data the result set with one column selected
     * @return
     */
    public static ObservableList<String> getOLString(ResultSet data) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            while (data != null && data.next()) {
                observableList.add(data.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return observableList;
    }

    public static ObservableList<String> getOLString(ResultSet data, int colNum) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            while (data != null && data.next()) {
                observableList.add(data.getString(colNum));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return observableList;
    }

    public static ArrayList<String> getALString(ResultSet data) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            while (data != null && data.next()) {
                arrayList.add(data.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return arrayList;
    }

    public static ObservableList<String> getOLNodeIdBtm2() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getOLString(dbu.getResultSet("SELECT NODEID FROM " + dbu.getTableName() + " WHERE BUILDING='BTM' AND FLOOR='2'"));
    }

    public static NodeEntry getNodeWithId(String id) {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        ResultSet rs = dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODEID = '" + id + "'");
        try {
            if (rs.next()) {
                ArrayList<String> al = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    al.add(rs.getString(1));
                }
                return new NodeEntry(al);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
        }
        return null;
    }

    public static ObservableList<String> getGiftNames() {
        DBUpdater dbu = new DBUpdater(DBMI.Gifts.value);
        return getOLString(dbu.getResultSet("SELECT NAME FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getReligiousServices() {
        DBUpdater dbu = new DBUpdater(DBMI.ReligiousServices.value);
        return getOLString(dbu.getResultSet("SELECT NAME FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getAccommodationServices() {
        DBUpdater dbu = new DBUpdater(DBMI.Accommodation.value);
        return getOLString(dbu.getResultSet("SELECT ACCOMMODATION FROM " + dbu.getTableName()));
    }

    // main function for fast testing
//    public static ObservableList<String> getSample() {
//        DBUpdater dbu = new DBUpdater(DBMI.Sample.value);
//        return getObservableListId(dbu.getResultSet("SELECT SAMPLE FROM " + dbu.getTableName()));
//    }
    public static ObservableList<String> getInterpreterRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.InterpreterRequests.value);
        return getOLString(dbu.getResultSet("SELECT NAME FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getFloralRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.Floral.value);
        return getOLString(dbu.getResultSet("SELECT FLORAL FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getPetTherapyRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.PetTherapy.value);
        return getOLString(dbu.getResultSet("SELECT PET FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getReadingRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.Reading.value);
        return getOLString(dbu.getResultSet("SELECT BOOK FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getTransportRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.TransportType.value);
        return getOLString(dbu.getResultSet("SELECT TRANSPORT FROM " + dbu.getTableName()));
    }

    public static ObservableList<String> getWheelchairOptionRequest(){
        DBUpdater dbu = new DBUpdater(DBMI.WheelchairOptions.value);
        return getOLString(dbu.getResultSet("SELECT WHEELCHAIR FROM " + dbu.getTableName()));
    }


    public static ObservableList<String> getDepartments(String type) {
        DBUpdater dbu = new DBUpdater(DBMI.Departments.value);
        return getOLString(dbu.getResultSet("SELECT DEPARTMENTS FROM " + dbu.getTableName() + " WHERE TYPE = '" + type + "'"));
    }
    public static ObservableList<String> getEmployee(String department) {
        DBUpdater dbu = new DBUpdater(DBMI.Employees.value);
        return getOLString(dbu.getResultSet("SELECT EMPLOYEES FROM " + dbu.getTableName() + " WHERE DEPTID = '" + department + "'"));
    }

    // main function for fast testing
    public static void main(String[] args) {
        ObservableList<String> ol = DBCustom.getOLNodeIdBtm2();
       // System.out.println(ol);
    }

    public static ObservableList<String> getReading() {
        DBUpdater dbu = new DBUpdater(DBMI.Reading.value);
        return getOLString(dbu.getResultSet("SELECT BOOK FROM " + dbu.getTableName()));
    }

    public static String getSaltedPassword(String employeeID) {
        DBUpdater dbu = new DBUpdater(DBMI.EmployeeLogins.value);
        ResultSet rs = dbu.getResultSet("SELECT SALTEDPASSWORD FROM " + dbu.getTableName() +" WHERE EMPID = '" + employeeID + "'");
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
        }

        return "";
    }

    public static ArrayList<Node> getNodeAL(ResultSet data) {
        int numCol = 8; // hard coded for node entry !!!
        ArrayList<Node> nodelist = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            while (data != null && data.next()) {
                for (int i = 0; i < numCol; i++) {
                    arrayList.add(data.getString(i));
                }
                nodelist.add(new Node(new NodeEntry(arrayList)));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return nodelist;
    }

    public static ObservableList<Node> getNodeOl(ResultSet data) {
        int numCol = 0;
        try {
            numCol = data.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Node> nodelist = FXCollections.observableArrayList();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            while (data.next()) {
                arrayList.clear();
                for (int i = 1; i <= numCol; i++) {
                    arrayList.add(data.getString(i));
                }
                nodelist.add(new Node(new NodeEntry(arrayList)));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Unexpected Error", e);
        }
        return nodelist;
    }

    public static ObservableList<Node> getAllNodes() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName()));
    }

    public static ObservableList<Node> getBathroomsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'BATH' OR NODETYPE = 'REST'"));
    }

    public static ObservableList<Node> getElevatorsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'ELEV'"));
    }

    public static ObservableList<Node> getExitsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'EXIT'"));
    }

    public static ObservableList<Node> getInfoDesksList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'INFO'"));
    }

    public static ObservableList<Node> getStairsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'STAI'"));
    }

    public static ObservableList<Node> getRetailList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'RETL'"));
    }

    public static ObservableList<Node> getServicesList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'SERV'"));
    }

    public static ObservableList<Node> getConferencesList(){
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'CONF'"));
    }

    public static ObservableList<Node> getDepartmentsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'DEPT'"));
    }

    public static ObservableList<Node> getLabsList() {
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'LABS'"));
    }

    public static  ObservableList<Node> getCurrentLocation(){
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODEID = 'AHALL00602'"));
    }

    //MAKE SURE THAT THIS WORKS
    public static ObservableList<Node> getFlexibleWorkspace(){
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE BUILDING = 'Flex'"));
    }


    public static String getPermissions(String employeeID){
        DBUpdater dbu = new DBUpdater(DBMI.EmployeeLogins.value);
        ResultSet rs = dbu.getResultSet("SELECT CLEARANCE FROM " + dbu.getTableName() +" WHERE EMPID = '" + employeeID + "'");
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
        }

        return "";
    }

    // fid to permission id
    public static HashMap<String, String> getPermissionsHM() {
        DBBuffer dbbPd = new DBBuffer(DBMI.PermissionDecoder.value);
        HashMap<String, PermissionDecoderEntry> pdFullHm = dbbPd.getBufferHashMap();
        HashMap<String, String> pdHm = new HashMap<>();
        for(HashMap.Entry<String, PermissionDecoderEntry> entry : pdFullHm.entrySet()){
            pdHm.put(entry.getValue().getFeatureId(), entry.getValue().getId());
        }
        dbbPd.close();
        return pdHm;
    }

    public static HashMap<String, String> getFidToPtypeHM() {
        DBBuffer dbbPd = new DBBuffer(DBMI.FeatureType.value);
        HashMap<String, FeatureTypeEntry> pdFullHm = dbbPd.getBufferHashMap();
        HashMap<String, String> pdHm = new HashMap<>();
        for(HashMap.Entry<String, FeatureTypeEntry> entry : pdFullHm.entrySet()){
            pdHm.put(entry.getValue().getId(), entry.getValue().getPermissionType());
        }
        dbbPd.close();
        return pdHm;
    }

    // full name to fid
    public static HashMap<String, String> getFNameToFidHM() {

        DBBuffer dbbFt = new DBBuffer(DBMI.FeatureType.value);
        HashMap<String, FeatureTypeEntry> ftFullHm = dbbFt.getBufferHashMap();
        HashMap<String, String> pdHm = new HashMap<>();
        for(HashMap.Entry<String, FeatureTypeEntry> entry : ftFullHm.entrySet()){
            pdHm.put(entry.getValue().getFeatureName(), entry.getValue().getId());
        }
        dbbFt.close();
        return pdHm;
    }


    public static  ObservableList<Node> getRooms(){
        DBUpdater dbu = new DBUpdater(DBMI.Nodes.value);
        return getNodeOl(dbu.getResultSet("SELECT * FROM " + dbu.getTableName() + " WHERE NODETYPE = 'CLAS' OR NODETYPE = 'AUDI' OR NODETYPE = 'FCON' "));
    }

}
