import bishopfish.controllers.GiftRequestController;
import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.map.*;
import bishopfish.servicerequest.GiftEntry;
import bishopfish.servicerequest.GiftRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class AStarDBTest {

    @BeforeEach
    public void reloadMapDB() {
        DBUpdater nodeDbu = new DBUpdater(DBMI.TestNodes.value);
        nodeDbu.dropTable();
        nodeDbu.createTable();
        nodeDbu.fillTableFromCsv(new File("nodes.csv"), true, false);
        nodeDbu.close();
        // load in edges
        DBUpdater edgeDbu = new DBUpdater(DBMI.TestEdges.value);
        edgeDbu.dropTable();
        edgeDbu.createTable();
        edgeDbu.fillTableFromCsv(new File("edges.csv"), true, true);
        edgeDbu.close();
    }



    @Test
    public void testSimpleDBAStar() {
        // use below two edges from lines 5 and 6 of the edges csv
        //ACONF0010G_AHALL0070G,ACONF0010G,AHALL0070G
        //ACONF0010G_AHALL0080G,ACONF0010G,ACONF0010G

        ArrayList<Node> nodesList = makeNodeList();
        HashMap<String, Node> nodeHm = makeNodeHm(nodesList);
        ArrayList<Edge> edgesList = makeEdgesList(nodeHm);
        Graph graph = new Graph(nodesList, edgesList);

        AStar aStar = new AStar(graph);

        ArrayList<Node> expectedPath = new ArrayList<Node>() {{
            add(nodeHm.get("AHALL0070G"));
            add(nodeHm.get("ACONF0010G"));
            add(nodeHm.get("AHALL0080G"));
        }};

        Node startNode = nodeHm.get("AHALL0070G");
        Node endNode = nodeHm.get("AHALL0080G");
        assertNotNull(startNode);
        assertNotNull(endNode);

        try {
            aStar.run(startNode, endNode);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(expectedPath, aStar.listNodes);

    }

    @Test
    public void testAddEdgeNodeDBAStar() {
        // use below two edges from lines 5 and 6 of the edges csv
        //ACONF0010G_AHALL0070G,ACONF0010G,AHALL0070G
        //ACONF0010G_AHALL0080G,ACONF0010G,ACONF0010G

        NodeEntry n1 = new NodeEntry("NodeA", "100", "100", "G", "Test", "Test", "Test NodeA", "Test NodeA");
        NodeEntry n2 = new NodeEntry("NodeB", "200", "200", "G", "Test", "Test", "Test NodeB", "Test NodeB");
        NodeEntry n3 = new NodeEntry("NodeC", "300", "100", "G", "Test", "Test", "Test NodeC", "Test NodeC");
        EdgeEntry e1 = new EdgeEntry("NodeA_NodeB", "NodeA", "NodeB");
        EdgeEntry e2 = new EdgeEntry("NodeB_AHALL0070G", "NodeB", "AHALL0070G");
        EdgeEntry e3 = new EdgeEntry("AHALL0080G_NodeC", "AHALL0080G", "NodeC");

        {
            DBBuffer<NodeEntry> nodesDbb = new DBBuffer<>(DBMI.TestNodes.value);
            nodesDbb.add(n1);
            nodesDbb.add(n2);
            nodesDbb.add(n3);
            nodesDbb.close();
        }
        {
            DBBuffer<EdgeEntry> edgesDbb = new DBBuffer<>(DBMI.TestEdges.value);
            edgesDbb.add(e1);
            edgesDbb.add(e2);
            edgesDbb.add(e3);
            edgesDbb.add(e1.getReverse());
            edgesDbb.add(e2.getReverse());
            edgesDbb.add(e3.getReverse());
            edgesDbb.close();
        }

        ArrayList<Node> nodesList = makeNodeList();
        HashMap<String, Node> nodeHm = makeNodeHm(nodesList);
        ArrayList<Edge> edgesList = makeEdgesList(nodeHm);
        Graph graph = new Graph(nodesList, edgesList);

        AStar aStar = new AStar(graph);

        ArrayList<Node> expectedPath = new ArrayList<Node>() {{
            add(nodeHm.get("NodeA"));
            add(nodeHm.get("NodeB"));
            add(nodeHm.get("AHALL0070G"));
            add(nodeHm.get("ACONF0010G"));
            add(nodeHm.get("AHALL0080G"));
            add(nodeHm.get("NodeC"));
        }};

        Node startNode = nodeHm.get("NodeA");
        Node endNode = nodeHm.get("NodeC");
        assertNotNull(startNode);
        assertNotNull(endNode);

        try {
            aStar.run(startNode, endNode);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(expectedPath, aStar.listNodes);
        try {
            aStar.run(endNode, startNode);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(expectedPath, aStar.listNodes);

    }

    public ArrayList<Node> makeNodeList() {
        ArrayList<Node> nodesList = new ArrayList<>();
        DBBuffer dbbNodes = new DBBuffer(DBMI.TestNodes.value);
        HashMap<String, NodeEntry> nodesHm = dbbNodes.getAll();
        for (HashMap.Entry<String, NodeEntry> entry : nodesHm.entrySet()) {
            nodesList.add(new Node(entry.getValue()));
        }
        dbbNodes.close();
        return nodesList;
    }

    public HashMap<String, Node> makeNodeHm(ArrayList<Node> nodesList) {
        HashMap<String, Node> nodeHm = new HashMap<>();
        for (Node n : nodesList) {
            nodeHm.put(n.getName(), n);
        }
        return nodeHm;
    }

    public ArrayList<Edge> makeEdgesList(HashMap<String, Node> nodeHm) {
        ArrayList<Edge> edgesList = new ArrayList<>();
        DBBuffer dbbEdges = new DBBuffer(DBMI.TestEdges.value);
        HashMap<String, EdgeEntry> edgesHm = dbbEdges.getAll();
        dbbEdges.close();
        for (HashMap.Entry<String, EdgeEntry> entry : edgesHm.entrySet()) {
            edgesList.add(new Edge(entry.getValue(), nodeHm));
        }
        return edgesList;
    }


}
