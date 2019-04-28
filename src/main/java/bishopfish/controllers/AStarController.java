package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.*;
import bishopfish.mapBuilder.GroupBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import javafx.util.Callback;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class AStarController extends Controller implements Initializable {

    public Canvas canvas;

    @FXML
    public Button submit;

    @FXML
    private ComboBox<String> directoriesDropdownStart;

    @FXML
    private ComboBox<String> directoriesDropdownFinish;

    @FXML
    private  ComboBox<Node> optionsDropdownStart;
    @FXML
    private ComboBox<Node> optionsDropdownFinish;

    //return list of items from column
    @FXML
    private ObservableList<String> directoriesDropdownListFinish = FXCollections.observableArrayList("Conference Rooms", "Departments", "Elevators", "Exits", "Information", "Labs", "Restrooms", "Retail", "Services", "Stairs", "Flexible Workspaces");

    @FXML
    private ObservableList<String> directoriesDropdownListStart = FXCollections.observableArrayList("Current Location","Conference Rooms", "Departments", "Elevators", "Exits", "Information", "Labs", "Restrooms", "Retail", "Services", "Stairs", "Flexible Workspaces");

    @FXML
    private ObservableList<Node> optionsFieldListFinish;

    @FXML
    private ObservableList<Node> optionsFieldListStart;

    // DBBuffer<NodeEntry> nodeConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting the list of directories (not from a database)
        directoriesDropdownStart.setItems(directoriesDropdownListStart);
        directoriesDropdownFinish.setItems(directoriesDropdownListFinish);
        //setting the list of options from the database
        optionsFieldListStart =DBCustom.getAllNodes();
        optionsFieldListFinish = DBCustom.getAllNodes();

        optionsDropdownStart.setItems(optionsFieldListStart);
        optionsDropdownFinish.setItems(optionsFieldListFinish);

        directoriesDropdownFinish.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // here
                optionsUpdaterFinish();
            }
        });

        directoriesDropdownStart.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // here
                optionsUpdaterStart();
            }
        });
    }

    public void optionsUpdaterFinish() {
        if (directoriesDropdownFinish.getValue().equals("Conference Rooms")) {
            optionsFieldListFinish = DBCustom.getConferencesList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Departments")) {
            optionsFieldListFinish = DBCustom.getDepartmentsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Elevators")) {
            optionsFieldListFinish = DBCustom.getElevatorsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Exits")) {
            optionsFieldListFinish = DBCustom.getExitsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Information")) {
            optionsFieldListFinish = DBCustom.getInfoDesksList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Labs")) {
            optionsFieldListFinish = DBCustom.getLabsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Restrooms")) {
            optionsFieldListFinish = DBCustom.getBathroomsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Retail")) {
            optionsFieldListFinish = DBCustom.getRetailList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Services")) {
            optionsFieldListFinish = DBCustom.getServicesList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Stairs")) {
            optionsFieldListFinish = DBCustom.getStairsList();
            optionsDropdownFinish.setItems(optionsFieldListFinish);

        } else if (directoriesDropdownFinish.getValue().equals("Flexible Workspaces")) {
            optionsFieldListFinish = DBCustom.getFlexibleWorkspace();
            optionsDropdownFinish.setItems(optionsFieldListFinish);
        }
    }

    public void optionsUpdaterStart() {
        if (directoriesDropdownStart.getValue().equals("Current Location")){
            optionsFieldListStart = DBCustom.getCurrentLocation();
            optionsDropdownStart.setItems(optionsFieldListStart);
        } else if (directoriesDropdownStart.getValue().equals("Conference Rooms")) {
            optionsFieldListStart = DBCustom.getConferencesList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Departments")) {
            optionsFieldListStart = DBCustom.getDepartmentsList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Elevators")) {
            optionsFieldListStart = DBCustom.getElevatorsList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Exits")) {
            optionsFieldListStart = DBCustom.getExitsList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Information")) {
            optionsFieldListStart = DBCustom.getInfoDesksList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Labs")) {
            optionsFieldListStart = DBCustom.getLabsList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Restrooms")) {
            optionsFieldListStart = DBCustom.getBathroomsList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Retail")) {
            optionsFieldListStart = DBCustom.getRetailList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Services")) {
            optionsFieldListStart = DBCustom.getServicesList();
            optionsDropdownStart.setItems(optionsFieldListStart);

        } else if (directoriesDropdownStart.getValue().equals("Stairs")) {
            optionsFieldListStart = DBCustom.getStairsList();
            optionsDropdownStart.setItems(optionsFieldListStart);
        }

        else if (directoriesDropdownStart.getValue().equals("Flexible Workspaces")) {
            optionsFieldListStart = DBCustom.getFlexibleWorkspace();
            optionsDropdownStart.setItems(optionsFieldListStart);
        }
    }

    @FXML
    public void onSubmitClick(ActionEvent event) throws IOException, PathNotFoundException {

        if (!hasValidInput(event)) {
            return;
        }

        String start = optionsDropdownStart.getValue().getName();
        String end = optionsDropdownFinish.getValue().getName();

//        Object[] rootAndController = initLoader("homePage.fxml");
//        Parent root = (Parent) rootAndController[0];
//        Controller controller = (Controller) rootAndController[1];
////        passPermissions(controller);
////        passBack(controller, "homePage.fxml", this.getCurFxml());
//        controller.secondInit();
//        loadWindow(event, root);
//
//        HomePageController x = (HomePageController) controller;

        HpController x = CSI.getInstance().getHpController();

        for(Group g: x.getFloorGroups()){
            g.getChildren().clear();
        }

        int groupNumber = groupIndex(optionsDropdownStart.getValue().getFloor());
        x.onClick(groupNumber);

        x.getGpane();



        ArrayList<Node> listNodes = new ArrayList<>();
        ArrayList<Edge> listEdges = new ArrayList<>();
        HashMap<String, Node> hmNodes = new HashMap<>();
        HashMap<String, Edge> hmEdges = new HashMap<>();

        DBBuffer<NodeEntry> dbbNode = new DBBuffer<>(DBMI.Nodes.value);
        HashMap<String, NodeEntry> nodeEntryHM = dbbNode.getBufferHashMap();
        DBBuffer<EdgeEntry> dbbEdge = new DBBuffer<>(DBMI.Edges.value);
        HashMap<String, EdgeEntry> edgeEntryHM = dbbEdge.getBufferHashMap();

        for (Map.Entry<String, NodeEntry> entry : nodeEntryHM.entrySet()) {
            Node node = new Node(entry.getValue());
            listNodes.add(node);
            hmNodes.put(node.getName(), node);
        }


        for (Map.Entry<String, EdgeEntry> entry : edgeEntryHM.entrySet()) {
            EdgeEntry ee = entry.getValue();
            Edge edge = new Edge(ee, hmNodes);

            // bi-directional nodes
            String biId = ee.getStartNode() + "_" + ee.getEndNode();
            Node biStart = hmNodes.get(ee.getEndNode());
            Node biEnd = hmNodes.get(ee.getStartNode());
            Edge biEdge = new Edge(biId, biStart, biEnd, Node.distanceFrom(biStart, biEnd));

            listEdges.add(edge);
            listEdges.add(biEdge);
            hmEdges.put(entry.getValue().getId(), edge);
        }

        /*
        //editing here
        if(hmNodes.get(start).getBuilding().equals("Flex")){
            Graph graph1 = new Graph(listNodes, listEdges);
            AStar startFlex = new AStar(graph1);
            //get tthe elevator
            Node elevator = hmNodes.get();
            //AStar from start to elevator
            startFlex.run(hmNodes.get(start),hmNodes.get);
            //AStar from elevator on floor 2 to destination
        }
        else if(hmNodes.get(end).getBuilding().equals("Flex")){

        }
        */

        //THIS was the only original code
        Graph graph1 = new Graph(listNodes, listEdges);
        String directions = x.getDirectionsFlag();
        AStar testingAStar = new AStar(graph1);
        testingAStar.run(hmNodes.get(start), hmNodes.get(end));
        System.gc();
        testingAStar.addCircles(x.getFloorGroups());
        x.getScrollPane().setVisible(true);
        x.getScrollPane().setMouseTransparent(false);
        x.getToggleDirections().setVisible(true);
        x.getTextArea().setText(returnDirections(testingAStar));

        /*
        if (directions == "AStar"){
            AStar testingAStar = new AStar(graph1);
            testingAStar.run(hmNodes.get(start), hmNodes.get(end));
            System.gc();
            testingAStar.addCircles(x.getFloorGroups());
            x.getScrollPane().setVisible(true);
            x.getScrollPane().setMouseTransparent(false);
            x.getToggleDirections().setVisible(true);
            x.getTextArea().setText(returnDirections(testingAStar));
        }
        */
        //else if
        if (directions == "BFS"){
//            BFS testingBFS = new BFS(graph1);
//            testingBFS.run(graph1, hmNodes.get(start), hmNodes.get(end));
//            System.gc();
//            testingBFS.addCircles(x.getFloorGroups());
//            x.getScrollPane().setVisible(true);
//            x.getScrollPane().setMouseTransparent(false);
//            x.getToggleDirections().setVisible(true);
//            x.getTextArea().setText(returnDirections(testingBFS));
        }
        else if (directions == "DFS"){
//            DFS testingDFS = new DFS(graph1);
//            testingDFS.run(graph1, hmNodes.get(start), hmNodes.get(end));
//            System.gc();
//            testingDFS.addCircles(x.getFloorGroups());
//            x.getScrollPane().setVisible(true);
//            x.getScrollPane().setMouseTransparent(false);
//            x.getToggleDirections().setVisible(true);
//            x.getTextArea().setText(returnDirections(testingDFS));
        }
        else if (directions == "Dijkstra's"){
//            Dijkstra testingDijkstar = new Dijkstra(graph1);
//            testingDijkstar.run(graph1, hmNodes.get(start), hmNodes.get(end));
//            System.gc();
//            testingDijkstar.addCircles(x.getFloorGroups());
//            x.getScrollPane().setVisible(true);
//            x.getScrollPane().setMouseTransparent(false);
//            x.getToggleDirections().setVisible(true);
//            x.getTextArea().setText(returnDirections(testingDijkstar));
        }









    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }


    private int groupIndex(String floor){
        if (floor.equals("3")){
            return 0;
        }
        else if(floor.equals("2")){
            return 1;
        }
        else if(floor.equals("1")){
            return 2;
        }
        else if(floor.equals("G")){
            return 3;
        }
        else if(floor.equals("L1")){
            return 4;
        }
        else{
            return 5;
        }
    }


    public double getAngle(Node source, Node destination){
        double destinationX = (double) destination.getXCoord();
        double sourceX = (double) source.getXCoord();
        double destinationY = (double) destination.getYCoord();
        double sourceY = (double) source.getYCoord();
        double xDiff = destinationX - sourceX;
        double yDiff = destinationY - sourceY;
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        if(angle < 0){
            angle = angle + 360;
            return angle;
        }
        else{
            return angle;
        }
    }

    public String returnDirections(AStar aStar){
        //actually returns the directions
        String directions = new String();

        //return the path to be given directions for
        //Graph graph = new Graph(nodes, allEdges);
        //AStar textDirectionsAStar = new AStar(graph);
        ArrayList<Node> path = new ArrayList<>(aStar.getListNodes());


        if (path.size() < 2) {
            //the user is trying to navigate to itself
            directions += "You have already arrived at your destination.";
        }

        else {
            //the user has to navigate to a legitimate distance (further than one step)

            //first node of the path
            Node beginningNode = path.get(0);
            //last node of the path
            Node endNode = path.get(path.size()-1);
            //divide by 60 to get num minutes
            double min = (beginningNode.distanceFrom(beginningNode, endNode)/14)/60;

            //if elevator or stairs are used, calculate how many floors it goes
            //add one minute for one floor, 30 seconds for every other floor
            for (int i = 0; i < path.size() - 1; i++){
                if(path.get(i).getNodeType().equals("ELEV") || path.get(i).getNodeType().equals("STAI")){
                    Node first = path.get(i);
                    Node second = path.get(i+1);
                    int firstFloor = parseFloor(first.getFloor());
                    int secondFloor = parseFloor(second.getFloor());
                    int floorDiff = Math.abs(firstFloor - secondFloor);
                    switch(floorDiff){
                        case 0:
                            //don't have to move any floors
                            min += 0;
                            break;
                        case 1:
                            //move 1 floor
                            min += 1;
                            break;
                        case 2:
                            //move 2 floors
                            min += 1.5;
                            break;
                        case 3:
                            //move 3 floors
                            min += 2;
                            break;
                        case 4:
                            //move 4 floors
                            min += 2.5;
                            break;
                        case 5:
                            //move 5 floors
                            min += 3;
                            break;
                        case 6:
                            min += 3.5;
                            break;
                    }
                }
            }
            //RETURN THE ESTIMATED TIME TO REACH YOUR DESTINATION IN MINUTES
            double estimatedMin = Math.round(min);
            if (estimatedMin <= 0.0){
                estimatedMin = 1.0;
            }

            //begin the directions
            directions += ("Directions from " + beginningNode.getLongName() + " to " + endNode.getLongName() + ".\n");

            //display the walking time
            directions += ("\nAVERAGE WALKING TIME:\n" + estimatedMin + " minutes.\n\n");

            //begin text directions
            directions += ("\nDIRECTIONS FOR FLOOR " + beginningNode.getFloor() + ".\n");
            directions += "\tBegin from " + beginningNode.getLongName() + ".\n";
            for (int i = 0; i < path.size() - 1; i++){
                //these nodes cannot null or we are going to get an empty text box
                @NotNull Node firstNode = path.get(i);
                @NotNull Node secondNode = path.get(i+1);

                //finds the distance between the first and second nodes
                double distance = firstNode.distanceFrom(firstNode, secondNode)/14;
                distance = Math.round(distance);
                if(distance <= 0.0){
                    distance = 0.5;
                }

                // find elevator or stairs
                // DEBUG THESE - prints from floor 3 to floor 3
                if(firstNode.getLongName().equals("Elevator S 02") && secondNode.getLongName().equals("Flexible Workspace Elevator")){
                    directions += "\tTake the elevator from floor 2 to floor 4.\n\n";
                    directions += "DIRECTIONS FOR FLOOR 4.\n\n";
                    firstNode = path.get(i+1);
                    secondNode = path.get(i+2);
                }

                else if(secondNode.getLongName().equals("Flexible Workspace Elevator")){
                    directions += "\tTake the elevator from floor " + firstNode.getFloor() + " to floor 4.\n\n";
                    directions += "DIRECTIONS FOR FLOOR 4.\n\n";
                    firstNode = path.get(i+1);
                    secondNode = path.get(i+2);
                }


                //added && statement
                if(secondNode.getNodeType().equals("ELEV") && !(firstNode.getFloor().equals(secondNode.getFloor()))){
                    directions += ("\tTake the elevator from floor " + firstNode.getFloor() + " to floor " + secondNode.getFloor() + ".\n");
                    directions += ("\tDistance: " + distance + "m.\n\n");

                    //we are now on a new floor
                    if(!(firstNode.getFloor().equals(secondNode.getFloor()))){
                        //the floor is changing.
                        directions += "DIRECTIONS FOR FLOOR " + secondNode.getFloor() +".\n\n";
                    }
                }

                /*
                if(secondNode.getBuilding().equals("Flex") || secondNode.getBuilding().equals("Flex2")){
                    directions += "Take the elevator from floor 2 to floor 4.\n";
                    directions += "DIRECTIONS FOR FLOOR 4.\n\n";
                }
                */

                //added && statement
                else if(secondNode.getNodeType().equals("STAI") && !(firstNode.getFloor().equals(secondNode.getFloor()))){
                    directions += ("\tTake the stairs from floor " + firstNode.getFloor() + " to floor " + secondNode.getFloor() + ".\n");
                    directions += ("\tDistance: " + distance + "m.\n\n");

                    //we are now on a new floor
                    if(!(firstNode.getFloor().equals(secondNode.getFloor()))){
                        //the floor is changing.
                        directions += "DIRECTIONS FOR FLOOR " + secondNode.getFloor() +".\n\n";
                    }
                }
                else if(secondNode.getNodeType().equals("EXIT")){
                    directions += ("\tExit from " + secondNode.getLongName() + ".\n");
                    directions += ("\tDistance: " + distance + "m.\n\n");
                }

                //if not at a flight of stairs or an elevator, must figure out if straight or turn
                //create helper fcn to change which direction is now being headed
                else{
                    //not exiting/changing floors? check angle
                    double angle = getAngle(firstNode, secondNode);
                    if(angle >= 0 && angle < 75){
                        //turn right
                        directions += ("\tTurn right from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                        directions += ("\tDistance: " + distance + "m.\n\n");
                    }
                    else if(angle >= 75 && angle < 105 || angle >= 195 && angle < 330){
                        int currDistance = 0;
                        if(firstNode.getNodeType().equals("HALL") && secondNode.getNodeType().equals("HALL")){
                            currDistance += distance;
                        }
                        else{
                            directions += ("\tGo straight from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                            directions += ("\tDistance: " + (distance += currDistance) + "m.\n\n");
                        }
                    }
                    else if(angle >= 105 && angle < 195){
                        //turn left
                        directions += ("\tTurn left from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                        directions += ("\tDistance: " + distance + "m.\n\n");
                    }
                    else{
                        //turn right
                        directions += ("\tTurn right from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                        directions += ("\tDistance: " + distance + "m.\n\n");
                    }
                }
            }
            directions += ("You have arrived at your destination: " + endNode.getLongName() + ".\n");
        }
        return directions;
    }

    public int parseFloor(String floor){
        int floorInt = 100;
        switch (floor){
            case("L2"):
                floorInt = 0;
                break;
            case("L1"):
                floorInt = 1;
                break;
            case("G"):
                floorInt = 2;
                break;
            case("1"):
                floorInt = 3;
                break;
            case("2"):
                floorInt = 4;
                break;
            case("3"):
                floorInt = 5;
                break;
            case("4"):
                floorInt = 6;
                break;
        }
        return floorInt;
    }

}