package bishopfish.map;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapToolTest {
    @Test
    public void testAddEdge(){
        DBBuffer<EdgeEntry> edgeAdd = new DBBuffer<>(DBMI.Edges.value);
        EdgeEntry tester = new EdgeEntry(("2d"+ "_" + "5s"), "5s", "2d");
        EdgeEntry tester2 = new EdgeEntry(("2d"+ "_" + "6s"), "6s", "2d");
        EdgeEntry tester3 = new EdgeEntry(("2d"+ "_" + "7s"), "7s", "2d");
        EdgeEntry tester4 = new EdgeEntry(("2d"+ "_" + "8s"), "8s", "2d");

        edgeAdd.add(tester);
        edgeAdd.add(tester2);
        edgeAdd.add(tester3);
        edgeAdd.add(tester4);
        assertEquals(tester, edgeAdd.get("2d_5s"));
        assertEquals(tester2, edgeAdd.get("2d_6s"));
        assertEquals(tester3, edgeAdd.get("2d_7s"));
        assertEquals(tester4, edgeAdd.get("2d_8s"));

        DBUpdater dbu = new DBUpdater(DBMI.Edges.value);
        assertEquals(tester.toStringArrayList(), dbu.getHashMap().get("2d_5s"));
        assertEquals(tester2.toStringArrayList(), dbu.getHashMap().get("2d_6s"));
        assertEquals(tester3.toStringArrayList(), dbu.getHashMap().get("2d_7s"));
        assertEquals(tester4.toStringArrayList(), dbu.getHashMap().get("2d_8s"));
    }

    @Test
    public void testAddNode(){
        DBBuffer<NodeEntry> nodeAdd = new DBBuffer<>(DBMI.Nodes.value);
        NodeEntry tester = new NodeEntry("1","2","3","4","5","6","7","8");
        NodeEntry tester2 = new NodeEntry("2","2","3","4","5","6","7","8");
        NodeEntry tester3 = new NodeEntry("3","2","3","4","5","6","7","8");
        NodeEntry tester4 = new NodeEntry("4","2","3","4","5","6","7","8");

        nodeAdd.add(tester);
        nodeAdd.add(tester2);
        nodeAdd.add(tester3);
        nodeAdd.add(tester4);
        assertEquals(tester, nodeAdd.get("1"));
        assertEquals(tester2, nodeAdd.get("2"));
        assertEquals(tester3, nodeAdd.get("3"));
        assertEquals(tester4, nodeAdd.get("4"));

        DBUpdater dbuNode = new DBUpdater(DBMI.Nodes.value);
        assertEquals(tester.toStringArrayList(), dbuNode.getHashMap().get("1"));
        assertEquals(tester2.toStringArrayList(), dbuNode.getHashMap().get("2"));
        assertEquals(tester3.toStringArrayList(), dbuNode.getHashMap().get("3"));
        assertEquals(tester4.toStringArrayList(), dbuNode.getHashMap().get("4"));
    }

    @Test
    public void testUpdateNode(){
        DBBuffer<NodeEntry> nodeAdd = new DBBuffer<>(DBMI.Nodes.value);
        nodeAdd.remove("112");
        NodeEntry tester = new NodeEntry("112","2","3","4","5","6","7","8");
        nodeAdd.add(tester);
        ArrayList<String> updater = new ArrayList<>();
        updater.add(null);
        updater.add("12");
        updater.add(null);
        updater.add("45");
        updater.add("6");
        updater.add(null);
        updater.add("99");
        ArrayList<String> checker = new ArrayList<>();
        checker.add("112");
        checker.add("2");
        checker.add("12");
        checker.add("4");
        checker.add("45");
        checker.add("6");
        checker.add("7");
        checker.add("99");
        DBUpdater dbuNode = new DBUpdater(DBMI.Nodes.value);
        assertEquals(tester.toStringArrayList(), dbuNode.getHashMap().get("112"));
        nodeAdd.update("112", updater);
        assertEquals(checker, dbuNode.getHashMap().get("112"));
    }

    @Test
    public void testRemoveNode(){
        DBBuffer<NodeEntry> nodeAdd = new DBBuffer<>(DBMI.Nodes.value);
        NodeEntry tester = new NodeEntry("1143","2","3","4","5","6","7","8");
        nodeAdd.add(tester);
        nodeAdd.remove("1143");
        assertNull(nodeAdd.get("1143"));
    }

    @Test
    public void testRemoveEdge(){
        DBBuffer<EdgeEntry> edgeAdd = new DBBuffer<>(DBMI.Edges.value);
        EdgeEntry tester = new EdgeEntry(("22d"+ "_" + "52s"), "52s", "22d");

        edgeAdd.add(tester);
        assertEquals(tester, edgeAdd.get("22d_52s"));
        edgeAdd.remove(tester.getId());
        assertNull(edgeAdd.get(tester.getId()));


    }
}
