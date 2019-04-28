package bishopfish.db;

import bishopfish.emergency.EmergencyRequestEntry;
import bishopfish.employees.DepartmentEntry;
import bishopfish.employees.EmployeeEntry;
import bishopfish.map.EdgeEntry;
import bishopfish.map.NodeEntry;
import bishopfish.scheduler.BookingEntry;
import bishopfish.scheduler.WorkstationEntry;
import bishopfish.servicerequest.GiftEntry;
import bishopfish.servicerequest.ServiceRequestEntry;
import bishopfish.servicerequest.SimpleEntry;
import bishopfish.utils.EmployeeLoginEntry;
import bishopfish.utils.FeatureTypeEntry;
import bishopfish.utils.PermissionDecoderEntry;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

public enum DBMI {
    // id must be the first column
    ServiceRequest      (new DBInfo(ServiceRequestEntry.class, "jdbc:derby:Databases/serviceRequest;create=true", "SERVICEREQUESTS", "SRID", "SRID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), FID VARCHAR(4), TICKETCONTENT VARCHAR(500), ISFULFILLED VARCHAR(6), DESTNODEID VARCHAR(10)")),
    Departments         (new DBInfo(DepartmentEntry.class, "jdbc:derby:Databases/employees;create=true", "DEPARTMENTS", "DEPTID", "DEPTID VARCHAR(4) NOT NULL PRIMARY KEY, NAME VARCHAR(255), TYPE VARCHAR(20)")),
    Employees           (new DBInfo(EmployeeEntry.class, "jdbc:derby:Databases/employees;create=true", "EMPLOYEES", "EMPID", "EMPID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), DEPTID VARCHAR(4), EMAIL VARCHAR(255), PHONE VARCHAR(40)")),
    Gifts               (new DBInfo(GiftEntry.class, "jdbc:derby:Databases/serviceRequest;create=true", "GIFTS", "ID" , "ID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(20), DESCRIPTION VARCHAR(40)")),
    ReligiousServices   (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true", "RELIGIOUSSERVICES", "ID" , "ID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(30)")),
    Accommodation       (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","ACCOMMODATIONSERVICES","ACCOMMODATIONSERVICEID" , "ACCOMMODATIONSERVICEID VARCHAR(8) NOT NULL PRIMARY KEY, ACCOMMODATION VARCHAR(30)")),
    //Sample              (new DBInfo("jdbc:derby:Databases/serviceRequest;create=true","SAMPLES", "SAMPLEID", "SAMPLEID VARCHAR(8) NOT NULL PRIMARY KEY, SAMPLE VARCHAR(30)")),
    TransportType       (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","TRANSPORTTYPE","ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, TRANSPORT VARCHAR(30)")),
    WheelchairOptions   (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","WHEELCHAIROPTIONS","ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, WHEELCHAIR VARCHAR(30)")),
    InterpreterRequests (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","INTERPRETER","ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(30)")),
    Floral              (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","FLORAL","ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, FLORAL VARCHAR(30)")),
    PetTherapy          (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","PETTHERAPY","ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, PET VARCHAR(30)")),
    Reading             (new DBInfo(SimpleEntry.class, "jdbc:derby:Databases/serviceRequest;create=true","BOOKS", "ID", "ID VARCHAR(8) NOT NULL PRIMARY KEY, BOOK VARCHAR(40)")),
//    RoomBookingRooms    (new DBInfo(RoomEntry.class, "jdbc:derby:Databases/roomBooking;create=true", "ROOMS", "ROOMID" , "ROOMID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), NODEID VARCHAR(10), OCCUPANCY VARCHAR(4)")),
    RoomBookingBookings (new DBInfo(BookingEntry.class, "jdbc:derby:Databases/roomBooking;create=true", "BOOKINGS", "BOOKINGID" , "BOOKINGID VARCHAR(36) NOT NULL PRIMARY KEY, ROOMID VARCHAR(10), STARTDATE VARCHAR(11), ENDDATE VARCHAR(11), RESERVER VARCHAR(255)")),
    TestNodes           (new DBInfo(NodeEntry.class, "jdbc:derby:Databases/tmp/tests;create=true", "TESTNODES", "NODEID" , "NODEID VARCHAR(10) NOT NULL PRIMARY KEY, XCOORD VARCHAR(4), YCOORD VARCHAR(4), FLOOR VARCHAR(2), BUILDING VARCHAR(10), NODETYPE VARCHAR(4), LONGNAME VARCHAR(48), SHORTNAME VARCHAR(45)")),
    TestEdges           (new DBInfo(EdgeEntry.class, "jdbc:derby:Databases/tmp/tests;create=true", "TESTEDGES", "EDGEID" , "EDGEID VARCHAR(21) NOT NULL PRIMARY KEY, STARTNODE VARCHAR(10), ENDNODE VARCHAR(10)")),
    TestDBUpdater2      (new DBInfo(FakeDBEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBUPDATER", "ID" , "ID VARCHAR(5) NOT NULL PRIMARY KEY, VALUE1 VARCHAR(10), VALUE2 VARCHAR(10)")),
    TestDBUpdater3      (new DBInfo(FakeDBEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBUPDATER", "ID" , "ID VARCHAR(5) NOT NULL PRIMARY KEY, VALUE1 VARCHAR(10), VALUE2 VARCHAR(10), VALUE3 VARCHAR(10)")),
    TestDBBuffer        (new DBInfo(TestEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBBUFFER", "ID" , "ID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(20), VALUE VARCHAR(20)")),
    TestDBCustom        (new DBInfo(TestEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBCUSTOM", "ID" , "ID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(20), VALUE VARCHAR(20)")),
    TestDBUpdaterAutoId (new DBInfo(TestEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBAUTOID", "ID" , "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME VARCHAR(20), VALUE VARCHAR(20)")),
    TestDBUpdaterAutoId2(new DBInfo(TestEntry.class, "jdbc:derby:/tmp/tests;create=true", "TESTDBAUTOID2", "ID" , "ID VARCHAR(36) NOT NULL PRIMARY KEY, NAME VARCHAR(20), VALUE VARCHAR(20)")),
    Workstations        (new DBInfo(WorkstationEntry.class, "jdbc:derby:Databases/roomBooking;create=true", "WORKSTATION", "WORKSTATIONID" , "WORKSTATIONID VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), NODEID VARCHAR(10), OCCUPANCY VARCHAR(4), ISOCCUPIED VARCHAR(10)")),
    Nodes               (new DBInfo(NodeEntry.class, "jdbc:derby:Databases/map;create=true", "NODES", "NODEID" , "NODEID VARCHAR(10) NOT NULL PRIMARY KEY, XCOORD VARCHAR(4), YCOORD VARCHAR(4), FLOOR VARCHAR(2), BUILDING VARCHAR(10), NODETYPE VARCHAR(4), LONGNAME VARCHAR(48), SHORTNAME VARCHAR(45)")),
    Edges               (new DBInfo(EdgeEntry.class, "jdbc:derby:Databases/map;create=true", "EDGES", "EDGEID" , "EDGEID VARCHAR(21) NOT NULL PRIMARY KEY, STARTNODE VARCHAR(10), ENDNODE VARCHAR(10)")),
    Emergency           (new DBInfo(EmergencyRequestEntry.class, "jdbc:derby:Databases/emergency;create=true","EMERGENCYREQUESTS","ID","ID VARCHAR(10) NOT NULL PRIMARY KEY, EMERGENCYTYPE VARCHAR(30), NODEID VARCHAR(30), TIMEOFREQUEST VARCHAR(100), PHOTONAME VARCHAR(150)")),
    EmployeeLogins      (new DBInfo(EmployeeLoginEntry.class, "jdbc:derby:Databases/employeeLogins;create=true", "EMPLOYEELOGINS", "EMPID", "EMPID VARCHAR(20) NOT NULL PRIMARY KEY, SALTEDPASSWORD VARCHAR(60), CLEARANCE VARCHAR(36)")),
    PermissionDecoder   (new DBInfo(PermissionDecoderEntry.class, "jdbc:derby:Databases/PermissionDecoder;create=true", "PERMISSIONDECODER", "ID", "ID VARCHAR(1) NOT NULL PRIMARY KEY, FID VARCHAR(4)")),
    FeatureType         (new DBInfo(FeatureTypeEntry.class, "jdbc:derby:Databases/PermissionDecoder;create=true", "FEATUREID", "FID", "FID VARCHAR(4) NOT NULL PRIMARY KEY, FEATURENAME VARCHAR(40), PERMISSIONTYPE VARCHAR(1), FEATURETYPE VARCHAR(2)")),
        // permissionType is Request(R) or Fullfill(F); featureType is serviceRequest (SR) or other (o)
    ;
    public final DBInfo value;
    DBMI(DBInfo dbmi) {
        this.value = dbmi;
    }

    public static class DBInfo {
        Class<? extends DBEntry> entry;
        String dbConnString;
        String tableName;
        String primaryKey;
        String colString;

        public Class<? extends DBEntry> getEntry() {
            return entry;
        }

        public String getDbConnString() {
            return dbConnString;
        }

        public String getTableName() {
            return tableName;
        }

        public String getPrimaryKey() {
            return primaryKey;
        }

        public String getColString() {
            return colString;
        }

        public DBInfo(Class<? extends DBEntry> entry, String dbConnString, String tableName, String primaryKey, String colString) {
            this.entry = entry;
            this.dbConnString = dbConnString;
            this.tableName = tableName;
            this.primaryKey = primaryKey;
            this.colString = colString;
        }
    }

    @Override
    public String toString() {
        return this.value.getTableName();
    }

    class FakeDBEntry extends DBEntry {
        public FakeDBEntry(ArrayList<String> values) {
            super(values);
        }
    }

}