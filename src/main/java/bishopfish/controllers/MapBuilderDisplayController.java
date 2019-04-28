package bishopfish.controllers;
import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.Edge;
import bishopfish.map.EdgeEntry;
import bishopfish.map.Node;
import bishopfish.map.NodeEntry;
import bishopfish.mapBuilder.GroupMapBuilder;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import net.kurobako.gesturefx.GesturePane;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

public class MapBuilderDisplayController extends Controller implements Initializable {
    public AnchorPane imgAnchor;
    public ImageView imgView;
    public GesturePane gpane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    Canvas pathCanvas;


    @FXML
    private Button floor32;
    @FXML
    private Button floor22;
    @FXML
    private Button floor12;
    @FXML
    private Button floorG2;
    @FXML
    private Button floorL12;
    @FXML
    private Button floorL22;
    @FXML
    private Button showEdges;
    @FXML
    private Button showNodes;

    @FXML
    JFXTextField xCoord;

    @FXML
    JFXTextField yCoord;

    @FXML
    JFXTextField floorField;

    @FXML
    JFXTextField buildingField;

    @FXML
    JFXTextField nodeType;

    @FXML
    JFXTextField longName;

    @FXML
    JFXTextField shortName;

    @FXML
    JFXButton submitButton;

    static final int max1 = 10;
    static final int max2 = 4;
    static final int max3 = 4;
    static final int max4 = 2;
    static final int max5 = 10;
    static final int max6 = 4;
    static final int max7 = 48;
    static final int max8 = 45;

   DBBuffer<NodeEntry> dbbNode;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        dbbNode = new DBBuffer<>(DBMI.Nodes.value);
//    }

    String filledID;
    String filledXCoord;
    String filledYCoord;
    String filledfloor;
    String filledBuilding;
    String fillednodeT;
    String filledLongN;
    String filledShortN;

    private Image image01;
    private Image image00;
    private Image image0;
    private Image image1;
    private Image image2;
    private Image image3;


    Group groupMap3;
    Group groupMap2;
    Group groupMap1;
    Group groupMap0;
    Group groupMap00;
    Group groupMap01;

    ArrayList<Group> groups;
    ArrayList<bishopfish.map.Node> listNodes;
    ArrayList<Edge> listEdges;
    GroupMapBuilder mbGb;
    @FXML
    JFXButton addDeleteToggle;

    DBBuffer<EdgeEntry> dbbEdge;
    String currentFloor;
    HashMap<String, bishopfish.map.Node> hmNodes;

    int addOrDelete;

    int edgesCounter;
    int nodesCounter;

    @FXML
    AnchorPane getImgAnchor;

    public HashMap<String, Node> getHmNodes() {
        return hmNodes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edgesCounter = 0;
        nodesCounter = 0;
        groups = new ArrayList();
        initMapView();
        initGroups();
        imgView.setPreserveRatio(true);
        imgView.setFitWidth(5000/3);
        imgView.setImage(image2);
        gpane.setContent(imgAnchor);
        gpane.setFitWidth(true);

        addOrDelete = 0;

        addMouseMapListener(imgAnchor);

        setGroup(groupMap2);
        floor22.setStyle("-fx-background-color: #6ca0fa");
        currentFloor = "2";

        listNodes = new ArrayList<>();
        listEdges = new ArrayList<>();

        dbbNode = new DBBuffer<>(DBMI.Nodes.value);
        dbbEdge = new DBBuffer<>(DBMI.Edges.value);

        updateLists();
        setTextFiledsVisibility(false);

//        this.setCurFxml("mapBuilderDisplay.fxml");
//        this.setBackFxmlStack(new Stack<String>() {{
//            push("homePage.fxml");
//        }});
//
        mbGb = new GroupMapBuilder(this, groups, listNodes, listEdges);

        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid5 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid6 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid7 = new RequiredFieldValidator("Field is required");

        addValidators(xCoord,valid1);
        addValidators(yCoord,valid2);
        addValidators(floorField,valid3);
        addValidators(buildingField,valid4);
        addValidators(nodeType,valid5);
        addValidators(longName,valid6);
        addValidators(shortName,valid7);

        showAllNodes(new ActionEvent());
        showAllNodes(new ActionEvent());

    }



    public double getScale(){
        return gpane.getCurrentScale();
    }

    public JFXTextField getxCoord() {
        return xCoord;
    }

    public JFXTextField getyCoord() {
        return yCoord;
    }

    public JFXTextField getFloorField() {
        return floorField;
    }

    public JFXTextField getBuildingField() {
        return buildingField;
    }

    public JFXTextField getNodeType() {
        return nodeType;
    }

    public JFXTextField getLongName() {
        return longName;
    }

    public JFXTextField getShortName() {
        return shortName;
    }

    public void setFilledID(String filledID) {
        this.filledID = filledID;
    }

    public void setFilledXCoord(String filledXCoord) {
        this.filledXCoord = filledXCoord;
    }

    public void setFilledYCoord(String filledYCoord) {
        this.filledYCoord = filledYCoord;
    }

    public void setFilledFloor(String filledfloor) {
        this.filledfloor = filledfloor;
    }

    public void setFilledBuilding(String filledBuilding) {
        this.filledBuilding = filledBuilding;
    }

    public void setFillednodeT(String fillednodeT) {
        this.fillednodeT = fillednodeT;
    }

    public void setFilledLongN(String filledLongN) {
        this.filledLongN = filledLongN;
    }

    public void setFilledShortN(String filledShortN) {
        this.filledShortN = filledShortN;
    }

    public void setxCoordpromt(String prompt){
        xCoord.setText(prompt);
    }

    public void setyCoordpromt(String prompt){
        yCoord.setText(prompt);
    }

    public void setFloorFiledpromt(String prompt){
        floorField.setText(prompt);
    }

    public void setBuildingFieldpromt(String prompt){
        buildingField.setText(prompt);
    }

    public void setNodeTypepromt(String prompt){
        nodeType.setText(prompt);
    }

    public void setLongNamepromt(String prompt){
        longName.setText(prompt);
    }

    public void setShortNamepromt(String prompt){
        shortName.setText(prompt);
    }

    public void setTextFiledsVisibility(Boolean visibility){
        xCoord.setVisible(visibility);
        yCoord.setVisible(visibility);
        floorField.setVisible(visibility);
        buildingField.setVisible(visibility);
        nodeType.setVisible(visibility);
        longName.setVisible(visibility);
        shortName.setVisible(visibility);
        submitButton.setVisible(visibility);
    }
    @FXML
    public void addDelete(ActionEvent event){
        if(addOrDelete == 0){
            addOrDelete = 1;
            addDeleteToggle.setText("Add");
        }
        else if (addOrDelete == 1){
            addOrDelete = 2;
            addDeleteToggle.setText("Delete");
        }
        else{
            addOrDelete = 0;
            addDeleteToggle.setText("View");
        }
    }

    public DBBuffer<NodeEntry> getDbbNode() {
        return dbbNode;
    }

    public DBBuffer<EdgeEntry> getDbbEdge() {
        return dbbEdge;
    }

    public void updateLists(){
        listNodes.clear();
        listEdges.clear();
        hmNodes = new HashMap<>();
        HashMap<String, Edge> hmEdges = new HashMap<>();
        HashMap<String, NodeEntry> nodeEntryHM = dbbNode.getBufferHashMap();
        HashMap<String, EdgeEntry> edgeEntryHM = dbbEdge.getBufferHashMap();

        for (Map.Entry<String, NodeEntry> entry : nodeEntryHM.entrySet()) {
            bishopfish.map.Node node = new bishopfish.map.Node(entry.getValue());
            listNodes.add(node);
            hmNodes.put(node.getName(), node);
        }
        for (Map.Entry<String, EdgeEntry> entry : edgeEntryHM.entrySet()) {
            EdgeEntry ee = entry.getValue();
            Edge edge = new Edge(ee, hmNodes);
            // bi-directional nodes
            String biId = ee.getStartNode() + "_" + ee.getEndNode();
            bishopfish.map.Node biStart = hmNodes.get(ee.getEndNode());
            bishopfish.map.Node biEnd = hmNodes.get(ee.getStartNode());
            Edge biEdge = new Edge(biId, biStart, biEnd, bishopfish.map.Node.distanceFrom(biStart, biEnd));

            listEdges.add(edge);
            listEdges.add(biEdge);
            hmEdges.put(entry.getValue().getId(), edge);
        }
    }

    private void initGroups() {
        groupMap3 = new Group();
        groups.add(groupMap3);
//        addMouseMapListener(groupMap3);
        groupMap2 = new Group();
        groups.add(groupMap2);
  //      addMouseMapListener(groupMap2);
        groupMap1 = new Group();
        groups.add(groupMap1);
    //    addMouseMapListener(groupMap1);
        groupMap0 = new Group();
        groups.add(groupMap0);
      //  addMouseMapListener(groupMap0);
        groupMap00 = new Group();
        groups.add(groupMap00);
        //addMouseMapListener(groupMap00);
        groupMap01 = new Group();
        groups.add(groupMap01);
        //addMouseMapListener(groupMap01);

    }

    private void addMouseMapListener(javafx.scene.Node i) {
        i.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(addOrDelete == 1){
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();

                        double xClick = (event.getX());
                        double yClick = (event.getY());

                        String x = String.valueOf((int) xClick * 3);
                        String y = String.valueOf((int) yClick * 3);

                        ArrayList<Node> tempList = mbGb.getListNodes();
                        NodeEntry tempNodeEntry = new NodeEntry(x + "_" + y, x, y, currentFloor, "", "", "", "New Node");
                        Node tempNode = new Node(tempNodeEntry);

                        tempList.add(tempNode);
                        mbGb.setListNodes(tempList);
                        dbbNode.add(tempNodeEntry);
                        hmNodes.put(tempNode.getName(), tempNode);
                        mbGb.clearAndDrawAll();
                    }
                    if (event.getButton().equals(MouseButton.SECONDARY)){
                        mbGb.getPrimed().getCircle().setFill(Color.BLACK);
                        mbGb.getPrimed().getCircle().setRadius(2);
                        mbGb.setPrimed(null);
                    }

                }
            }
        });
    }
    private void initMapView() {
        image01 = new Image(getClass().getResourceAsStream("00_thelowerlevel2.png"));
        image00 = new Image(getClass().getResourceAsStream("00_thelowerlevel1.png"));
        image0 = new Image(getClass().getResourceAsStream("00_thegroundfloor.png"));
        image1 = new Image(getClass().getResourceAsStream("01_thefirstfloor.png"));
        image2 = new Image(getClass().getResourceAsStream("02_thesecondfloor.png"));
        image3 = new Image(getClass().getResourceAsStream("03_thethirdfloor.png"));
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }

    private void setGroup(Group g) {
        imgAnchor.getChildren().clear();
        imgAnchor.getChildren().add(imgView);
        imgAnchor.getChildren().add(g);
    }

    public String getCurrentFloor() {
        return currentFloor;
    }

    public void setButtonsToWhite(){
        floor32.setStyle("-fx-background-color: white");
        floor22.setStyle("-fx-background-color: white");
        floor12.setStyle("-fx-background-color: white");
        floorG2.setStyle("-fx-background-color: white");
        floorL12.setStyle("-fx-background-color: white");
        floorL22.setStyle("-fx-background-color: white");
    }

    public int getAddOrDelete() {
        return addOrDelete;
    }

    public void onClick(ActionEvent event) {
        setButtonsToWhite();
        ((Button) event.getSource()).setStyle("-fx-background-color: #6ca0fa");
        if (event.getSource() == floorL22) {
            imgView.setImage(image01);
            setGroup(groupMap01);
            currentFloor = "L2";
        }
        if (event.getSource() == floorL12) {
            imgView.setImage(image00);
            setGroup(groupMap00);
            currentFloor = "L1";
        }
        if (event.getSource() == floorG2) {
            imgView.setImage(image0);
            setGroup(groupMap0);
            currentFloor = "G";
        }
        if (event.getSource() == floor12) {
            imgView.setImage(image1);
            setGroup(groupMap1);
            currentFloor = "1";
        }
        if (event.getSource() == floor22) {
            imgView.setImage(image2);
            setGroup(groupMap2);
            currentFloor = "2";
        }
        if (event.getSource() == floor32) {
            imgView.setImage(image3);
            setGroup(groupMap3);
            currentFloor = "3";
        }
    }
    public ArrayList<Group> getFloorGroups(){
        return groups;
    }


    public void showAllNodes(ActionEvent event) {
        nodesCounter+=1;
        if (nodesCounter%2 == 0 && edgesCounter%2 == 0) {
            for (Group i : groups) {
                i.getChildren().clear();
            }
            showNodes.setText("Show Nodes");
        }
        else if (nodesCounter%2 == 0) {
            for (Group i : groups) {
                i.getChildren().clear();
            }
            mbGb.allEdges();
            showNodes.setText("Show Nodes");
        }
        else {
            if (edgesCounter % 2 == 1) {
                mbGb.clearAndDrawAll();
            }else{
                mbGb.allNodes();
            }
            showNodes.setText("Hide Nodes");
        }
    }


    public void showAllEdges(ActionEvent event) {
        edgesCounter += 1;
        if (edgesCounter % 2 == 0 && nodesCounter % 2 == 0) {
            for (Group i : groups) {
                i.getChildren().clear();
            }
            showEdges.setText("Show Edges");
        } else if (edgesCounter % 2 == 0) {
            for (Group i : groups) {
                i.getChildren().clear();
            }
            mbGb.allNodes();
            showEdges.setText("Show Edges");
        } else {
            mbGb.allEdges();
            showEdges.setText("Hide Edges");
        }
    }

//    private int getAllGroupElements(){
//        int size = 0;
//        for (Group i : groups) {
//            size += i.getChildren().size();
//        }
//        return size;
//    }


    public void submitBtn(ActionEvent e){
        String xCoordinate = getxCoord().getText();
        String yCoordinate = getyCoord().getText();
        String floor = getFloorField().getText();
        String building = getBuildingField().getText();
        String nodeT = getNodeType().getText();
        String longN = getLongName().getText();
        String shortN = getShortName().getText();
        setTextFiledsVisibility(false);
        mbGb.setPrimed(null);

        System.out.println("Node Edited: xCoordinate: " + xCoordinate + ", yCoordinate: " + yCoordinate + ", Node Type: " + nodeT + ", Floor: " + floor + ", Building:" + building + ", Short Name: " + shortN + ", Long Name: " + longN);
        ArrayList<String> conUpdate = new ArrayList<>();
        ArrayList<String> conUpdate1 = new ArrayList<>();

        if(xCoordinate.equals(filledXCoord) || xCoordinate.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(filledXCoord);
        }
        else {
            if(xCoordinate.length()>max2){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An xCoord " + max1 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(xCoordinate);
                conUpdate1.add(xCoordinate);
            }
        }
        if(yCoordinate.equals(filledYCoord) || yCoordinate.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(filledYCoord);

        }
        else{
            if(yCoordinate.length()>max3){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An yCoord " + max2 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(yCoordinate);
                conUpdate1.add(yCoordinate);

            }
        }
        if(floor.equals(filledfloor) || floor.isEmpty()) {
            conUpdate.add(null);
            conUpdate1.add(filledfloor);

        }
        else{
            if(floor.length()>max4){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An floor " + max3 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(floor);
                conUpdate1.add(floor);

            }
        }
        if(building.equals(filledBuilding) || building.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(filledBuilding);

        }
        else{
            if(building.length()>max5){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An building " + max4 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(building);
                conUpdate1.add(building);

            }
        }
        if(nodeT.equals(fillednodeT) || nodeT.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(fillednodeT);

        }
        else{
            if(nodeT.length()>max3){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Node Type " + max5 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(nodeT);
                conUpdate1.add(nodeT);

            }
        }
        if(longN.equals(filledLongN) || longN.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(filledLongN);

        }
        else{
            if(longN.length()>max7){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Long Name " + max6 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(longN);
                conUpdate1.add(longN);

            }
        }
        if(shortN.equals(filledShortN) || shortN.isEmpty()){
            conUpdate.add(null);
            conUpdate1.add(filledShortN);

        }
        else{
            if(shortN.length()>max8){
                showAlert(ERROR, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Short Name " + max7 + " Characters or Less");
                return;
            }
            else {
                //System.out.println(xCoorTb.getText());
                conUpdate.add(shortN);
                conUpdate1.add(shortN);

            }
        }
        int index1 = listNodes.indexOf(new Node(dbbNode.get(filledID)));

        dbbNode.update(filledID, conUpdate);
        conUpdate.add(0, filledID);
        ArrayList<Node> lN = mbGb.getListNodes();
        lN.set(index1, new Node(dbbNode.getBufferHashMap().get(filledID)));
        mbGb.setListNodes(lN);
        showAlert(CONFIRMATION, ((javafx.scene.Node) e.getSource()).getScene().getWindow(), "Edited Entry", "Node Has Been Edited Successfully");
        mbGb.clearAndDrawAll();
    }
}




