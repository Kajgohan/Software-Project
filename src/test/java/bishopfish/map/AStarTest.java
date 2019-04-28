package bishopfish.map;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.jupiter.api.Assertions.*;

public class AStarTest {
    @Test
    public void testPrint(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Edge edge = new Edge("edge", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB));
        Graph graph = new Graph(listNodes, listEdges);
        AStar printAStar = new AStar(graph);
        try {
            printAStar.run(nodeB, nodeA);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDistanceFrom(){
        //distanceFrom is a function in the Node class
        Node node1 = new Node("node 1", 3, 4);
        Node node2 = new Node("node 2", 1, 2);
        Double suggestedAnswer = Math.sqrt(8);
        assertEquals(suggestedAnswer, Node.distanceFrom(node1, node2));
    }

    //kind of useless because i need to change this
    /*
    @Test
    public void testMethodToUse(){
        String result = "DFS";
        assertTrue(result == AStar.methodToUse());
    }
    */

    @Test
    public void testRun(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Node nodeC = new Node("node C", 10, 60);
        Node nodeD = new Node("node D", 20, 30);
        Node nodeE = new Node("node E", 0, 50);
        Node nodeF = new Node("node F",50,30);

        Edge edge1 = new Edge("edge 1(AD)", nodeA, nodeD, Node.distanceFrom(nodeA, nodeD));
        Edge edge2 = new Edge("edge 2(DA)", nodeD, nodeA, Node.distanceFrom(nodeD, nodeA));
        Edge edge3 = new Edge("edge 3(DC)", nodeD, nodeC, Node.distanceFrom(nodeD, nodeC));
        Edge edge4 = new Edge("edge 4(CD)", nodeC, nodeD, Node.distanceFrom(nodeC, nodeD));
        Edge edge5 = new Edge("edge 5(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
        Edge edge6 = new Edge("edge 6(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
        Edge edge7 = new Edge("edge 7(CB)", nodeC, nodeB, Node.distanceFrom(nodeC, nodeB));
        Edge edge8 = new Edge("edge 8(BC)", nodeB, nodeC, Node.distanceFrom(nodeB, nodeC));
        Edge edge9 = new Edge("edge 9(CE)", nodeC, nodeE, Node.distanceFrom(nodeC, nodeE));
        Edge edge10 = new Edge("edge 10(EC)", nodeE, nodeC, Node.distanceFrom(nodeE, nodeC));
        Edge edge11 = new Edge("edge 11(EA)", nodeE, nodeA, Node.distanceFrom(nodeE, nodeA));
        Edge edge12 = new Edge("edge 12(AE)", nodeA, nodeE, Node.distanceFrom(nodeA, nodeE));
        Edge edge13 = new Edge("edge 13(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));
        Edge edge14 = new Edge("edge 14(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
        Edge edge15 = new Edge("edge 15(AF)", nodeA, nodeF, Node.distanceFrom(nodeA, nodeF));
        Edge edge16 = new Edge("edge 16(FA)", nodeF, nodeA, Node.distanceFrom(nodeF, nodeA));
        Edge edge17 = new Edge("edge 17(FC)", nodeF, nodeC, Node.distanceFrom(nodeF, nodeC));
        Edge edge18 = new Edge("edge 18(CF)", nodeC, nodeF, Node.distanceFrom(nodeC, nodeF));
        Edge edge19 = new Edge("edge 19(AB)", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        Edge edge20 = new Edge("edge 20(BA)", nodeB, nodeA, Node.distanceFrom(nodeB, nodeA));
        Edge edge21 = new Edge("edge 21(BF)", nodeB, nodeF, Node.distanceFrom(nodeB, nodeF));
        Edge edge22 = new Edge("edge 22(FB)", nodeF, nodeB, Node.distanceFrom(nodeF, nodeB));
//        Edge edge23 = new Edge("edge 23(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
//        Edge edge24 = new Edge("edge 24(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
//        Edge edge25 = new Edge("edge 25(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
//        Edge edge26 = new Edge("edge 26(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));

        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,edge9,edge10,edge11,edge12
                ,edge13,edge14,edge15,edge16,edge17,edge18,edge19,edge20, edge21,edge22/*,edge23,edge24,edge25,edge26*/));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE,nodeF));

        Graph graph1 = new Graph(listNodes, listEdges);
        //Set set1 = new HashSet();
        //Queue queue1 = new PriorityQueue();

        AStar testingAStar = new AStar(graph1);


        try {
            testingAStar.run(nodeB,nodeE);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }

        //testingAStar.run(nodeB, nodeE);
        //this function returns void, so this test is going to fail
        //must just check output when it runs
    }

    @Test
    public void testUpdateNeighbor(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Node nodeC = new Node("node C", 10, 60);
        Node nodeD = new Node("node D", 20, 30);
        Node nodeE = new Node("node E", 0, 50);
        Node nodeF = new Node("node F",50,30);

        Edge edge1 = new Edge("edge 1(AD)", nodeA, nodeD, Node.distanceFrom(nodeA, nodeD));
        Edge edge2 = new Edge("edge 2(DA)", nodeD, nodeA, Node.distanceFrom(nodeD, nodeA));
        Edge edge3 = new Edge("edge 3(DC)", nodeD, nodeC, Node.distanceFrom(nodeD, nodeC));
        Edge edge4 = new Edge("edge 4(CD)", nodeC, nodeD, Node.distanceFrom(nodeC, nodeD));
        Edge edge5 = new Edge("edge 5(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
        Edge edge6 = new Edge("edge 6(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
        Edge edge7 = new Edge("edge 7(CB)", nodeC, nodeB, Node.distanceFrom(nodeC, nodeB));
        Edge edge8 = new Edge("edge 8(BC)", nodeB, nodeC, Node.distanceFrom(nodeB, nodeC));
        Edge edge9 = new Edge("edge 9(CE)", nodeC, nodeE, Node.distanceFrom(nodeC, nodeE));
        Edge edge10 = new Edge("edge 10(EC)", nodeE, nodeC, Node.distanceFrom(nodeE, nodeC));
        Edge edge11 = new Edge("edge 11(EA)", nodeE, nodeA, Node.distanceFrom(nodeE, nodeA));
        Edge edge12 = new Edge("edge 12(AE)", nodeA, nodeE, Node.distanceFrom(nodeA, nodeE));
        Edge edge13 = new Edge("edge 13(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));
        Edge edge14 = new Edge("edge 14(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
        Edge edge15 = new Edge("edge 15(AF)", nodeA, nodeF, Node.distanceFrom(nodeA, nodeF));
        Edge edge16 = new Edge("edge 16(FA)", nodeF, nodeA, Node.distanceFrom(nodeF, nodeA));
        Edge edge17 = new Edge("edge 17(FC)", nodeF, nodeC, Node.distanceFrom(nodeF, nodeC));
        Edge edge18 = new Edge("edge 18(CF)", nodeC, nodeF, Node.distanceFrom(nodeC, nodeF));
        Edge edge19 = new Edge("edge 19(AB)", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        Edge edge20 = new Edge("edge 20(BA)", nodeB, nodeA, Node.distanceFrom(nodeB, nodeA));
        Edge edge21 = new Edge("edge 21(BF)", nodeB, nodeF, Node.distanceFrom(nodeB, nodeF));
        Edge edge22 = new Edge("edge 22(FB)", nodeF, nodeB, Node.distanceFrom(nodeF, nodeB));
//        Edge edge23 = new Edge("edge 23(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
//        Edge edge24 = new Edge("edge 24(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
//        Edge edge25 = new Edge("edge 25(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
//        Edge edge26 = new Edge("edge 26(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));

        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,edge9,edge10,edge11,edge12
                ,edge13,edge14,edge15,edge16,edge17,edge18,edge19,edge20, edge21,edge22/*,edge23,edge24,edge25,edge26*/));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE,nodeF));

        Graph graph2 = new Graph(listNodes, listEdges);

        AStar testingAStar2 = new AStar(graph2);

        testingAStar2.updateNeighbor(nodeB, nodeE);
    }

    @Test
    public void testGroupIndex(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Edge edge = new Edge("edge", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB));
        Graph graph = new Graph(listNodes, listEdges);
        AStar groupAStar = new AStar(graph);

        String floor = "2";
        int floorFound = groupAStar.groupIndex(floor);
        assertEquals(1, floorFound);
    }

    @Test
    public void testScaleX(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Edge edge = new Edge("edge", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB));
        Graph graph = new Graph(listNodes, listEdges);
        AStar scaleXAStar = new AStar(graph);
        int expected = 10;
        int real = scaleXAStar.scaleX(100);
        assertEquals(10, real);
    }

    @Test
    public void testScaleY(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Edge edge = new Edge("edge", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB));
        Graph graph = new Graph(listNodes, listEdges);
        AStar scaleYAStar = new AStar(graph);
        int expected = 10;
        int real = scaleYAStar.scaleY(100);
        assertEquals(10, real);
    }

    /*
    @Test
    public void testGetAngle(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Edge edge = new Edge("edge", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB));
        Graph graph = new Graph(listNodes, listEdges);
        AStar getAngleAStar = new AStar(graph);
        TextDirections directions = new TextDirections(nodeB, nodeA, listNodes, listEdges);
        double angle = directions.getAngle(nodeB, nodeA);

        Node nodeC = new Node("node C", 0, -5);
        Node nodeD = new Node("node D", 0,0);
        Edge edge2 = new Edge("edge", nodeC, nodeD, Node.distanceFrom(nodeC, nodeD));
        ArrayList<Edge> listEdges2 = new ArrayList<Edge>(Arrays.asList(edge));
        ArrayList<Node> listNodes2 = new ArrayList<Node>(Arrays.asList(nodeC, nodeD));
        Graph graph2 = new Graph(listNodes2, listEdges2);
        TextDirections directions2 = new TextDirections(nodeD, nodeC, listNodes2, listEdges2);
        double angle2 = directions.getAngle(nodeD, nodeC);

        assertEquals(270, angle2);
    }
    */

    @Test
    public void testReturnPath(){
        Node nodeA = new Node("node A", 10, 50);
        Node nodeB = new Node("node B", 0,0);
        Node nodeC = new Node("node C", 10, 60);
        Node nodeD = new Node("node D", 20, 30);
        Node nodeE = new Node("node E", 0, 50);
        Node nodeF = new Node("node F",50,30);

        Edge edge1 = new Edge("edge 1(AD)", nodeA, nodeD, Node.distanceFrom(nodeA, nodeD));
        Edge edge2 = new Edge("edge 2(DA)", nodeD, nodeA, Node.distanceFrom(nodeD, nodeA));
        Edge edge3 = new Edge("edge 3(DC)", nodeD, nodeC, Node.distanceFrom(nodeD, nodeC));
        Edge edge4 = new Edge("edge 4(CD)", nodeC, nodeD, Node.distanceFrom(nodeC, nodeD));
        Edge edge5 = new Edge("edge 5(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
        Edge edge6 = new Edge("edge 6(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
        Edge edge7 = new Edge("edge 7(CB)", nodeC, nodeB, Node.distanceFrom(nodeC, nodeB));
        Edge edge8 = new Edge("edge 8(BC)", nodeB, nodeC, Node.distanceFrom(nodeB, nodeC));
        Edge edge9 = new Edge("edge 9(CE)", nodeC, nodeE, Node.distanceFrom(nodeC, nodeE));
        Edge edge10 = new Edge("edge 10(EC)", nodeE, nodeC, Node.distanceFrom(nodeE, nodeC));
        Edge edge11 = new Edge("edge 11(EA)", nodeE, nodeA, Node.distanceFrom(nodeE, nodeA));
        Edge edge12 = new Edge("edge 12(AE)", nodeA, nodeE, Node.distanceFrom(nodeA, nodeE));
        Edge edge13 = new Edge("edge 13(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));
        Edge edge14 = new Edge("edge 14(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
        Edge edge15 = new Edge("edge 15(AF)", nodeA, nodeF, Node.distanceFrom(nodeA, nodeF));
        Edge edge16 = new Edge("edge 16(FA)", nodeF, nodeA, Node.distanceFrom(nodeF, nodeA));
        Edge edge17 = new Edge("edge 17(FC)", nodeF, nodeC, Node.distanceFrom(nodeF, nodeC));
        Edge edge18 = new Edge("edge 18(CF)", nodeC, nodeF, Node.distanceFrom(nodeC, nodeF));
        Edge edge19 = new Edge("edge 19(AB)", nodeA, nodeB, Node.distanceFrom(nodeA, nodeB));
        Edge edge20 = new Edge("edge 20(BA)", nodeB, nodeA, Node.distanceFrom(nodeB, nodeA));
        Edge edge21 = new Edge("edge 21(BF)", nodeB, nodeF, Node.distanceFrom(nodeB, nodeF));
        Edge edge22 = new Edge("edge 22(FB)", nodeF, nodeB, Node.distanceFrom(nodeF, nodeB));
//        Edge edge23 = new Edge("edge 23(BD)", nodeB, nodeD, Node.distanceFrom(nodeB, nodeD));
//        Edge edge24 = new Edge("edge 24(DB)", nodeD, nodeB, Node.distanceFrom(nodeD, nodeB));
//        Edge edge25 = new Edge("edge 25(BE)", nodeB, nodeE, Node.distanceFrom(nodeB, nodeE));
//        Edge edge26 = new Edge("edge 26(EB)", nodeE, nodeB, Node.distanceFrom(nodeE, nodeB));

        ArrayList<Edge> listEdges = new ArrayList<Edge>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,edge9,edge10,edge11,edge12
                ,edge13,edge14,edge15,edge16,edge17,edge18,edge19,edge20, edge21,edge22/*,edge23,edge24,edge25,edge26*/));
        ArrayList<Node> listNodes = new ArrayList<Node>(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE,nodeF));

        Graph graph1 = new Graph(listNodes, listEdges);
        //Set set1 = new HashSet();
        //Queue queue1 = new PriorityQueue();

        AStar testingAStar = new AStar(graph1);
        testingAStar.returnPath(nodeB, nodeE);
    }

}