package bishopfish.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import net.kurobako.gesturefx.GesturePane;

public class GuestHomePageController {
//    public AnchorPane imgAnchor;
//    public ImageView imgView;
//
//    public GesturePane getGpane() {
//        return gpane;
//    }
//
//    public GesturePane gpane;
//    @FXML
//    private AnchorPane anchorPane;
//
//    @FXML
//    AnchorPane listenerAnchor;
//
//
//    @FXML
//    private ToggleButton toggleDirections1;
//
//    @FXML
//    Canvas pathCanvas;
//
//    @FXML
//    AnchorPane subControllerPane;
//
//    @FXML
//    private Button floor3;
//    @FXML
//    private Button floor2;
//    @FXML
//    private Button floor1;
//    @FXML
//    private Button floorG;
//    @FXML
//    private Button floorL1;
//    @FXML
//    private Button floorL2;
//
//    @FXML
//    private ScrollPane scrollPane;
//
//    @FXML
//    AnchorPane directionsFxml;
//
//    MapBuilderDisplayController mapBuilderDisplayController = new MapBuilderDisplayController();
//
//
//    //@FXML
//    //private BorderPane borderPane;
//
//
//    @FXML
//    JFXTextArea textDirectionsBox;
//
//
//    @FXML
//    private Slider mapSlider;
//
//    private Image image01;
//    private Image image00;
//    private Image image0;
//    private Image image1;
//    private Image image2;
//    private Image image3;
//
//
//    Group groupMap3;
//    Group groupMap2;
//    Group groupMap1;
//    Group groupMap0;
//    Group groupMap00;
//    Group groupMap01;
//
//    ArrayList<Group> groups;
//
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        CSI.getInstance().setCurFxml("guestHomePage.fxml");
//        CSI.getInstance().setHpController(this);
//        groups = new ArrayList<>();
//        initMapView();
//        initGroups();
//        imgView.setPreserveRatio(true);
//        imgView.setFitWidth(1000);
//        imgView.setImage(image2);
//        gpane.setContent(imgView);
//        gpane.setFitWidth(true);
//
//        setGroup(groupMap2);
//        floor2.setStyle("-fx-background-color: #6ca0fa");
//    }
//
//
//    public void secondInit() {
//
////        this.setCurFxml("homePage.fxml");
////        this.setBackFxmlStack(new Stack<String>() {{ push("welcomeScreen.fxml"); }});
//    }
//
//
//
//    private void initGroups() {
//        groupMap3 = new Group();
//        groups.add(groupMap3);
//
//        groupMap2 = new Group();
//        groups.add(groupMap2);
//
//        groupMap1 = new Group();
//        groups.add(groupMap1);
//
//        groupMap0 = new Group();
//        groups.add(groupMap0);
//
//        groupMap00 = new Group();
//        groups.add(groupMap00);
//
//        groupMap01 = new Group();
//        groups.add(groupMap01);
//
//        //imgAnchor.getChildren().addAll(groupMap3,groupMap2,groupMap0,groupMap00,groupMap1,groupMap01);
//
//    }
//
//    private void addMouseMapListener(Node i){
//        i.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override public void handle(MouseEvent event) {
//                event.consume();
//                System.out.println(event.getX());
//                System.out.println(event.getY());
//                System.out.println(event.getSceneX());
//                System.out.println(event.getSceneY());
//                System.out.println(event.getScreenX());
//                System.out.println(event.getScreenY());
//                System.out.println("---------------------------------------------------");
//            }
//        });
//    }
//
//    public Image getImage01() {
//        return image01;
//    }
//
//    public Image getImage00() {
//        return image00;
//    }
//
//    public Image getImage0() {
//        return image0;
//    }
//
//    public Image getImage1() {
//        return image1;
//    }
//
//    public Image getImage2() {
//        return image2;
//    }
//
//    public Image getImage3() {
//        return image3;
//    }
//
//    private void initMapView(){
//        image01 = new Image(getClass().getResourceAsStream("00_thelowerlevel2.png"));
//        image00 = new Image(getClass().getResourceAsStream("00_thelowerlevel1.png"));
//        image0 = new Image(getClass().getResourceAsStream("00_thegroundfloor.png"));
//        image1 = new Image(getClass().getResourceAsStream("01_thefirstfloor.png"));
//        image2 = new Image(getClass().getResourceAsStream("02_thesecondfloor.png"));
//        image3 = new Image(getClass().getResourceAsStream("03_thethirdfloor.png"));
//    }
//
//    public void onAdminViewClick(ActionEvent event) throws IOException {
//        loadFxml("adminLandingPage.fxml");
//    }
//    public void onEmergencyClick(ActionEvent event) throws IOException {
//        loadFxml("emergencyRequestPage.fxml");
//    }
//    public void onReserveARoomClick(ActionEvent event) throws IOException {
//        //need to update this path when merging reservations
//        loadFxml("roomView.fxml");
//    }
//    public void onServiceRequestClick(ActionEvent event) throws IOException {
//        loadFxml("chooseServiceRequest.fxml");
//    }
//    public void onDirectoriesClick(ActionEvent event) throws IOException {
//        subControllerPane.getChildren().clear();
//        loadFxml("aStar.fxml");
//    }
//    public void onMapBuilderClick(ActionEvent event) throws IOException {
//        loadFxml("mapBuilderDisplay.fxml");
//    }
//    public void onSettingsClick(ActionEvent event) throws IOException {
//        loadFxml("settingsPage.fxml");
//    }
//    public void onAboutClick(ActionEvent event) throws IOException {
//        loadFxml("aboutPage.fxml");
//    }
//
//    public void toggleTextDirections(ActionEvent event){
//
//    }
//
//
//
//
//    public void onClick(ActionEvent event) {
//        setButtonsToWhite();
//        ((Button) event.getSource()).setStyle("-fx-background-color: #6ca0fa");
//        if(event.getSource() == floorL2) {
//            imgView.setImage(image01);
//            gpane.setContent(imgView);
//            setGroup(groupMap01);
//        }
//        if(event.getSource() == floorL1){
//            imgView.setImage(image00);
//            gpane.setContent(imgView);
//            setGroup(groupMap00);
//        }
//        if(event.getSource() == floorG){
//            imgView.setImage(image0);
//            gpane.setContent(imgView);
//            setGroup(groupMap0);
//        }
//        if(event.getSource() == floor1){
//            imgView.setImage(image1);
//            gpane.setContent(imgView);
//            setGroup(groupMap1);
//        }
//        if(event.getSource() == floor2){
//            imgView.setImage(image2);
//            gpane.setContent(imgView);
//            setGroup(groupMap2);
//        }
//        if(event.getSource() == floor3){
//            imgView.setImage(image3);
//            gpane.setContent(imgView);
//            setGroup(groupMap3);
//        }
//    }
//
//    public void setButtonsToWhite(){
//        floor3.setStyle("-fx-background-color: white");
//        floor2.setStyle("-fx-background-color: white");
//        floor1.setStyle("-fx-background-color: white");
//        floorG.setStyle("-fx-background-color: white");
//        floorL1.setStyle("-fx-background-color: white");
//        floorL2.setStyle("-fx-background-color: white");
//    }
//
//
//    public void onClick(int i) {
//        if(i == 0) {
//            imgView.setImage(image3);
//            gpane.setContent(imgView);
//            setGroup(groupMap3);
//            setButtonsToWhite();
//            floor3.setStyle("-fx-background-color: #6ca0fa");
//        }
//        if(i == 1) {
//            imgView.setImage(image2);
//            gpane.setContent(imgView);
//            setGroup(groupMap2);
//            setButtonsToWhite();
//            floor2.setStyle("-fx-background-color: #6ca0fa");
//
//        }
//        if(i == 2) {
//            imgView.setImage(image1);
//            gpane.setContent(imgView);
//            setGroup(groupMap1);
//            setButtonsToWhite();
//            floor1.setStyle("-fx-background-color: #6ca0fa");
//
//        }
//        if(i == 3) {
//            imgView.setImage(image0);
//            gpane.setContent(imgView);
//            setGroup(groupMap0);
//            setButtonsToWhite();
//            floorG.setStyle("-fx-background-color: #6ca0fa");
//
//        }
//        if(i == 4) {
//            imgView.setImage(image00);
//            gpane.setContent(imgView);
//            setGroup(groupMap00);
//            setButtonsToWhite();
//            floorL1.setStyle("-fx-background-color: #6ca0fa");
//
//
//        }
//        if(i == 5) {
//            imgView.setImage(image01);
//            gpane.setContent(imgView);
//            setGroup(groupMap01);
//            setButtonsToWhite();
//            floorL2.setStyle("-fx-background-color: #6ca0fa");
//        }
//
//    }
//
//    @Override
//    protected boolean hasValidInput(ActionEvent e) {
//        return true;
//    }
//
//
//    public void setGroup(Group g){
//        imgAnchor.getChildren().clear();
//        imgAnchor.getChildren().add(g);
//
////        for (Group gps : groups)
////        {
////            if(gps == g){
////                gps.setVisible(true);
////            }
////            else{
////                gps.setVisible(false);
////            }
////        }
//    }
//
//    public ArrayList<Group> getFloorGroups(){
//        return groups;
//    }
//
//    //public Button getNextFloorButton(){return nextFloor;}
//    //public Button getPreviousFloorButton(){return previousFloor;}
//
//    public ScrollPane getScrollPane(){return scrollPane;}
//
//    public JFXTextArea getTextArea(){return textDirectionsBox;}
//
//    @Override
//    public AnchorPane getSubP() {
//        return subControllerPane;
//    }
//
//    @Override
//    public AnchorPane getDirectionsFxml(){return directionsFxml;}
//
//    public ImageView getImgView() { return imgView;}
//
//    public String getFxml() {
//        return "guestHomePage.fxml";
//    }
//
//    public Canvas getPathCanvas() {
//        return pathCanvas;
//    }
//
//    public ToggleButton getToggleDirections(){
//        return toggleDirections1;
//    }
}




