package bishopfish.map;

import bishopfish.controllers.CSI;
import bishopfish.mapBuilder.GroupBuilder;
import bishopfish.controllers.AStarController;
import bishopfish.controllers.HomePageController;
import com.jfoenix.controls.JFXSnackbar;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.math.*;

import java.util.*;

import bishopfish.utils.SendText;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import static bishopfish.map.Node.distanceFrom;

public class AStar {
    //used for implementation of singleton
    private static AStar single_instance = null;

    public Graph graph;
    // might need to be ArrayList<Edge>
    public ArrayList<Edge> edges;
    public HashSet searched = new HashSet();
    //changed to PriorityQueue // Changed back to a queue but it should not make a difference
    public PriorityQueue<Node> unsearched = new PriorityQueue<>();
    public Node beginning;
    public Node end;
    public ArrayList<Node> listNodes;

    SequentialTransition sequentialTransition = new SequentialTransition();


    //@Todo: add a singleton class for storing both nodes and edges

    public AStar(Graph graph) {
        this.graph = graph;
        this.edges = graph.getEdges();
        System.out.println(edges);
    }

    private AStar() {
        //empty constructor used for Singleton purposes
        //:)
    }

    public ArrayList<Node> getListNodes() {
        return listNodes;
    }

    public void setListNodes(ArrayList<Node> listNodes) {
        this.listNodes = listNodes;
    }

    //singleton class. this can go away as i fix the singleton class
    //will be changed to selecting the type of pathfinding that is being used
    public static AStar getInstance() {
        if (single_instance == null) {
            single_instance = new AStar();
        }
        return single_instance;
    }

    /*
    public static String methodToUse(){
        String methodToRun = new String();
        int methodNum = 0;
        switch(methodNum){
            case(0):
                methodToRun = "AStar";
            case(1):
                methodToRun = "BFS";
            case(2):
                methodToRun = "DFS";
        }
        return methodToRun;
    }
    */


    // determines the shortest path from start to end and prints the path
    public void run(@NotNull Node start, @NotNull Node end) throws PathNotFoundException{
        searched = new HashSet();
        unsearched = new PriorityQueue<>();

        //set the current node to @param start
        Node current = start;

        //set start node's heuristic value s(g(x) and h(x))
        start.setDistance(0.0);
        start.setHeuristic(end);
        this.beginning = start;
        this.end = end;

        //Add @param start to the queue
        unsearched.add(start);

        while (!unsearched.isEmpty()) {
            // Pop the PriorityQueue and set current to the top element;
            current = (Node) unsearched.poll();

            System.out.println("This is Current " + current);
            if (current.equals(end)) {
                Node.printPath(end);
                listNodes = Node.getPath(end);
                return;
            }

            //move the current node to the searched list
            searched.add(current);

            //update neighbor operates on two nodes
            updateNeighbor(current, end);
        }

        //only get here if we did not find the shortest path
        //may need different error handling
        System.out.println("Shortest path between " + start + " and " + end +
                " was not found.");

    }

    // determines the shortest path from start to end and prints the path
    public ArrayList<Node> returnPath(@NotNull Node start, @NotNull Node end) {
        searched = new HashSet();
        unsearched = new PriorityQueue<>();

        //set the current node to @param start
        Node current = start;

        //set start node's heuristic value s(g(x) and h(x))
        start.setDistance(0.0);
        start.setHeuristic(end);
        this.beginning = start;
        this.end = end;

        //Add @param start to the queue
        unsearched.add(start);

        while (!unsearched.isEmpty()) {
            // Pop the PriorityQueue and set current to the top element;
            current = (Node) unsearched.poll();

            //System.out.println("This is Current " + current);
            if (current.equals(end)) {
                Node.printPath(end);
                listNodes = Node.getPath(end);
                System.out.println("THIS IS LISTNODES RIGHT BEFORE IT RETURNS: " + listNodes);
                return listNodes;
            }

            //move the current node to the searched list
            searched.add(current);

            //update neighbor operates on two nodes
            updateNeighbor(current, end);
        }

        return listNodes;

        //only get here if we did not find the shortest path
        //may need different error handling
        //System.out.println("Shortest path between " + start + " and " + end +
        //        " was not found.");
        //return new ArrayList<Node>();

    }

    /*
    @param current node whose neighbors are to be checked and updated if necessary
    @param destination node which heuristics will be calculated from (ex. distance from @param destination
     */

    public void updateNeighbor(Node curr, Node destination) {
        //slightly changed from List neighbors to ArrayList<Node> neighbors
        ArrayList<Node> neighbors = Graph.getNeighbors(edges, curr);
        // @TODO this is a cast now? might work?

        //distance is the current node's distance to the start
        Double distance = curr.getDistance();

        for (Node neighbor : neighbors) {
            //temp is the distance from current node to a neighbor
            Double temp = Graph.getDistanceFrom(edges, curr, neighbor);
            System.out.println("hi");
            System.out.println(neighbors);
            // if searched already contains neighbor, no need to double check
            // continue in loop
            if (!searched.contains(neighbor)) {
                System.out.println("tmp: " + temp);
                if (distance + temp < neighbor.getDistance()) {
                    //shorter path has been found
                    //updating neighboring node
                    neighbor.setPrevious(curr);
                    neighbor.setDistance(distance + temp);
                    neighbor.setHeuristic(destination);
                    //allow neighbor to be searched through by adding to unsearched queue
                    unsearched.add(neighbor);
                }
            }
        }
    }


    //get beginning and get end functions?
    //get list of nodes from database
    /*
    public void drawLines(GraphicsContext gc){
       int cirRadius = 10;
        for (int i = 0; i <listNodes.size()-1; i++){
            Node firstNode = listNodes.get(i);
            Node secondNode = listNodes.get(i+1);
            gc.strokeLine(scaleX(firstNode.getXCoord()+cirRadius), scaleY(firstNode.getYCoord()+cirRadius), scaleX(secondNode.getXCoord()+cirRadius), scaleY(secondNode.getYCoord()+cirRadius));

        }
    }
    */

    //take floor that need to draw on out of arraylist
    //add all entities we made in addCircles to this group
    //public getters and setters are in the home page controller
    //arraylist[0] is 3rd floor, etc (top to bottom)
    //1 is 2nd floor, 2 is 1st floor, 3 is ground, 4 is L1, 5 is L2

    public int groupIndex(String floor) {
        if (floor.equals("3")) {
            return 0;
        } else if (floor.equals("2")) {
            return 1;
        } else if (floor.equals("1")) {
            return 2;
        } else if (floor.equals("G")) {
            return 3;
        } else if (floor.equals("L1")) {
            return 4;
        } else {
            return 5;
        }
    }

    //changed from Group g to ArrayList<Group>
    public void addCircles(ArrayList<Group> allGroups) {
        int counter = 0;
        ArrayList<Node> listNodes1 = listNodes;
//        ArrayList<Node> listNodes1 = (ArrayList<Node>)graph.getNodes();
//        ArrayList<Edge> listNodes2 = (ArrayList<Edge>)graph.getEdges();
//        for(Edge i : listNodes2){
//            allGroups.get(groupIndex(i.getStart().getFloor())).getChildren().add(new Line(i.getStart().getXCoord()/5, i.getStart().getYCoord()/5, i.getEnd().getXCoord()/5, i.getEnd().getYCoord()/5));
//        }
        System.out.println("Adding them circles");

        while (counter < listNodes1.size()) {
            counter = floorHandler(counter, allGroups, listNodes1);
        }

    }

    private Integer floorHandler(Integer count, ArrayList<Group> allGroups, ArrayList<Node> listNodes1) {

        Path path = new Path();
        String firstFloor = listNodes1.get(count).getFloor();
        MoveTo moveTo = new MoveTo((float) listNodes1.get(count).getXCoord() / 5, (float) listNodes1.get(count).getYCoord() / 5);
        path.getElements().add(moveTo);
        Circle circleStart = new Circle(5);
        Circle circleEnd = new Circle(5);
        int endCircle = listNodes1.size()-1;
        circleStart.setCenterX(listNodes1.get(count).getXCoord() / 5);
        circleStart.setCenterY(listNodes1.get(count).getYCoord() / 5);
        circleStart.setUserData(listNodes1.get(count));
        circleEnd.setCenterX(listNodes1.get(endCircle).getXCoord() / 5);
        circleEnd.setCenterY(listNodes1.get(endCircle).getYCoord() / 5);
        circleEnd.setUserData(listNodes1.get(endCircle));
        circleStart.setFill(Color.BLUE);
        circleEnd.setFill(Color.RED);
        allGroups.get(groupIndex(listNodes1.get(count).getFloor())).getChildren().addAll(circleStart);
        count += 1;

        boolean flag = false;
        while (count < listNodes1.size() && listNodes1.get(count).getFloor().equals(firstFloor)) {
            flag = true;
            System.out.println("Called");
            Circle circle = new Circle(2);
            circle.setCenterX(listNodes1.get(count).getXCoord() / 5);
            circle.setCenterY(listNodes1.get(count).getYCoord() / 5);
            circle.setUserData(listNodes1.get(count));
            LineTo lineTo = new LineTo(listNodes1.get(count).getXCoord() / 5, listNodes1.get(count).getYCoord() / 5);
            path.getElements().add(lineTo);
            count += 1;
            allGroups.get(groupIndex(listNodes1.get(count-1).getFloor())).getChildren().addAll(circle);

        }

        if (count < listNodes1.size()) {
            if (!listNodes1.get(count).getFloor().equals(firstFloor)) {
                final int nextFloor = groupIndex(listNodes1.get(count).getFloor());
                Circle circle = new Circle(2);
                circle.setFill(Color.GREEN);
                circle.setRadius(6);
                circle.setCenterX(listNodes1.get(count-1).getXCoord() / 5);
                circle.setCenterY(listNodes1.get(count-1).getYCoord() / 5);
                circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Here232323323");
                        CSI.getInstance().getHpController().onClick(nextFloor);
                        //JFXSnackbar jfxSnackbar = new JFXSnackbar(x.imgAnchor);
                        //jfxSnackbar.show("Floor " + nextFloor, 300);

                    }
                });
                allGroups.get(groupIndex(listNodes1.get(count-1).getFloor())).getChildren().addAll(circle);
            }

        }

        if(flag) {
            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(20));
            pt.setPath(path);

            Rectangle rectPath = new Rectangle(0, 0, 10, 10);
            rectPath.setArcHeight(5);
            rectPath.setArcWidth(5);
            Image img = new Image("/bishopfish/stickman.jpg");
            rectPath.setFill(new ImagePattern(img));
            pt.setNode(rectPath);
            pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.play();

            allGroups.get(groupIndex(firstFloor)).getChildren().addAll(rectPath);

        }

//        final Node y = listNodes1.get(count - 1);
//        pt.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                int i = groupIndex(y.getFloor());
//                x.onClick(i);
//            }
//        });
//        a.seqTrans.getChildren().addAll(pt);


        allGroups.get(groupIndex(firstFloor)).getChildren().addAll(path);
        return count;
    }

    /*
    //this function draws the circle over all of the nodes that we need to display on the map
    public void drawCircles(GraphicsContext gc){
        //adding circles to a group
        //System.out.println("Drawing circle");
        //@Todo: make this a circle not oval make

        for (int i = 0; i < listNodes.size(); i++){
            //circle color
            gc.setStroke(Color.CORNFLOWERBLUE);
            //line width
            gc.setLineWidth(5);

            System.out.println(listNodes.get(i).getXCoord() + " " + listNodes.get(i).getYCoord());
            //call fillOval function to fill in the object with the correct coordinates
            gc.fillOval(scaleX(listNodes.get(i).getXCoord()), scaleY(listNodes.get(i).getYCoord()),
                    10, 10);
        }
    }
    */

    public int scaleX(int value) {
        return value / 10;
    }

    public int scaleY(int value) {
        return value / 10;
    }


    /*
    public void sendDirections(Node start, Node destination){
        String toSend = sendTextDirections(start, destination);
        SendText.sendText("6037142952", "Verizon", toSend,
                "Directions from kiosk to " + destination.getName() + ".");
    }
    */

}
