import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.map.EdgeEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeDBTest {

    @BeforeEach
    public void setUp() {
        // enpty out nodes table
        DBUpdater nodeDbu = new DBUpdater(DBMI.TestNodes.value);
        nodeDbu.dropTable();
        nodeDbu.createTable();
        nodeDbu.close();
        // enpty out edges table
        DBUpdater edgeDbu = new DBUpdater(DBMI.TestEdges.value);
        edgeDbu.dropTable();
        edgeDbu.createTable();
        edgeDbu.close();
    }


    //makes sure edges are adding properly to db
    @Test
    public void testAddEdges() {
        EdgeEntry ee1 = new EdgeEntry(("2d"+ "_" + "5s"), "5s", "2d");
        EdgeEntry ee2 = new EdgeEntry(("2d"+ "_" + "6s"), "6s", "2d");
        EdgeEntry ee3 = new EdgeEntry(("2d"+ "_" + "7s"), "7s", "2d");
        EdgeEntry ee4 = new EdgeEntry(("2d"+ "_" + "8s"), "8s", "2d");

        {
            DBBuffer<EdgeEntry> edgeDbb = new DBBuffer<>(DBMI.TestEdges.value);
            edgeDbb.add(ee1);
            edgeDbb.add(ee2);
            edgeDbb.add(ee3);
            edgeDbb.add(ee4);
            assertEquals(ee1, edgeDbb.get("2d_5s"));
            assertEquals(ee2, edgeDbb.get("2d_6s"));
            assertEquals(ee3, edgeDbb.get("2d_7s"));
            assertEquals(ee4, edgeDbb.get("2d_8s"));
            edgeDbb.close();
        }

        DBUpdater dbu = new DBUpdater(DBMI.TestEdges.value);
        assertEquals(ee1.toStringArrayList(), dbu.getHashMap().get("2d_5s"));
        assertEquals(ee2.toStringArrayList(), dbu.getHashMap().get("2d_6s"));
        assertEquals(ee3.toStringArrayList(), dbu.getHashMap().get("2d_7s"));
        assertEquals(ee4.toStringArrayList(), dbu.getHashMap().get("2d_8s"));
        dbu.close();
    }

    @Test
    public void testAddEdgesCsv() {

        DBUpdater dbu = new DBUpdater(DBMI.TestEdges.value);
        dbu.fillTableFromCsv(new File("src/integration-test/resources/testEdges.csv"), false, true);

//        DBBuffer<EdgeEntry> edgeDbb = new DBBuffer<>(DBMI.TestEdges.value);
//        edgeDbb.getAll();

        HashMap<String, ArrayList<String>> al = dbu.getHashMap();
        dbu.close();

        assertEquals(new ArrayList<String>() {{add("A_B"); add("A"); add("B"); }}, al.get("A_B"));
        assertEquals(new ArrayList<String>() {{add("A_C"); add("A"); add("C"); }}, al.get("A_C"));
        assertEquals(new ArrayList<String>() {{add("D_C"); add("D"); add("C"); }}, al.get("D_C"));
        assertEquals(new ArrayList<String>() {{add("E_A"); add("E"); add("A"); }}, al.get("E_A"));
        assertEquals(new ArrayList<String>() {{add("C_B"); add("C"); add("B"); }}, al.get("C_B"));
        assertEquals(new ArrayList<String>() {{add("B_A"); add("B"); add("A"); }}, al.get("B_A"));
        assertEquals(new ArrayList<String>() {{add("C_A"); add("C"); add("A"); }}, al.get("C_A"));
        assertEquals(new ArrayList<String>() {{add("C_D"); add("C"); add("D"); }}, al.get("C_D"));
        assertEquals(new ArrayList<String>() {{add("A_E"); add("A"); add("E"); }}, al.get("A_E"));
        assertEquals(new ArrayList<String>() {{add("B_C"); add("B"); add("C"); }}, al.get("B_C"));


    }



}
