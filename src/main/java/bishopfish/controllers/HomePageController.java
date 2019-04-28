package bishopfish.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//import bishopfishapi.Emergency;
import bishopfish.utils.NewPlayer;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
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
import net.aksingh.owmjapis.api.APIException;
import net.kurobako.gesturefx.GesturePane;

public class HomePageController extends Controller implements Initializable, HpController {
    public AnchorPane imgAnchor;
    public ImageView imgView;

    public GesturePane getGpane() {
        return gpane;
    }

    public GesturePane gpane;



    @FXML
    private AnchorPane anchorPane;


    public String directionsFlag;



    @FXML
    private ToggleButton toggleDirections;

    @FXML
    AnchorPane listenerAnchor;

    @FXML
    Canvas pathCanvas;

    @FXML
    AnchorPane subControllerPane;

    @FXML
    private Button floor3;
    @FXML
    private Button floor2;
    @FXML
    private Button floor1;
    @FXML
    private Button floorG;
    @FXML
    private Button floorL1;
    @FXML
    private Button floorL2;

    @FXML
    private Button emergencyButton;
    @FXML
    private Button servicesButton;
    @FXML
    private Button schedulerButton;
    @FXML
    private Button directionsButton;
    @FXML
    private Button mapBuilderButton;
    @FXML
    private Button aboutButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button relaxButton;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    AnchorPane directionsFxml;

    MapBuilderDisplayController mapBuilderDisplayController = new MapBuilderDisplayController();


    //@FXML
    //private BorderPane borderPane;


    @FXML
    JFXTextArea textDirectionsBox;


    @FXML
    private Slider mapSlider;

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

    volatile NewPlayer player;
    boolean playing = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        CSI.getInstance().setCurFxml("homePage.fxml");
        CSI.getInstance().setHpController(this);
        CSI.getInstance().resetBackStack();
        CSI.getInstance().clearAllAstar();
        groups = new ArrayList<>();
        initMapView();
        initGroups();
        imgView.setPreserveRatio(true);
        imgView.setFitWidth(1000);
        imgView.setImage(image2);
        gpane.setContent(imgView);
        gpane.setFitWidth(true);

        setGroup(groupMap2);
        floor2.setStyle("-fx-background-color: #6ca0fa");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (CSI.getInstance().isMementoActive()) {
                            CSI.getInstance().setMementoCountdown(30);
                            CSI.getInstance().setMementoActive(false);
                        }
                        //System.out.println(CSI.getInstance().getMementoCountdown());
                        CSI.getInstance().setMementoCountdown(CSI.getInstance().getMementoCountdown() - 1);
                        if (CSI.getInstance().getMementoCountdown() < 0) {
                            CSI.getInstance().setMementoCountdown(30);
                            goHome();
                            //mementoTimer.cancel();
                        }
                        CSI.getInstance().setMementoCountdown(CSI.getInstance().getMementoCountdown());
                    }
                });
            }
        };
        CSI.getInstance().setMementoTimer(new Timer(), task);
    }


    public void secondInit() {

//        this.setCurFxml("homePage.fxml");
//        this.setBackFxmlStack(new Stack<String>() {{ push("welcomeScreen.fxml"); }});
//        this.setHpController(this);

    }



    public void toggleTextDirections(ActionEvent event){
        CSI.getInstance().clearAllAstar();
        toggleDirections.setVisible(false);
    }

    private void initGroups() {
        groupMap3 = new Group();
        groups.add(groupMap3);

        groupMap2 = new Group();
        groups.add(groupMap2);

        groupMap1 = new Group();
        groups.add(groupMap1);

        groupMap0 = new Group();
        groups.add(groupMap0);

        groupMap00 = new Group();
        groups.add(groupMap00);

        groupMap01 = new Group();
        groups.add(groupMap01);

        //imgAnchor.getChildren().addAll(groupMap3,groupMap2,groupMap0,groupMap00,groupMap1,groupMap01);

    }

    private void addMouseMapListener(Node i){
        i.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                event.consume();
                System.out.println(event.getX());
                System.out.println(event.getY());
                System.out.println(event.getSceneX());
                System.out.println(event.getSceneY());
                System.out.println(event.getScreenX());
                System.out.println(event.getScreenY());
                System.out.println("---------------------------------------------------");
            }
        });
    }

    public Image getImage01() {
        return image01;
    }

    public Image getImage00() {
        return image00;
    }

    public Image getImage0() {
        return image0;
    }

    public Image getImage1() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }

    public Image getImage3() {
        return image3;
    }

    private void initMapView(){
        image01 = new Image(getClass().getResourceAsStream("00_thelowerlevel2.png"));
        image00 = new Image(getClass().getResourceAsStream("00_thelowerlevel1.png"));
        image0 = new Image(getClass().getResourceAsStream("00_thegroundfloor.png"));
        image1 = new Image(getClass().getResourceAsStream("01_thefirstfloor.png"));
        image2 = new Image(getClass().getResourceAsStream("02_thesecondfloor.png"));
        image3 = new Image(getClass().getResourceAsStream("03_thethirdfloor.png"));
    }

    public void onButtonClick(ActionEvent event) throws IOException{
        if (event.getSource() != relaxButton) {
            if (player != null) {
                player.stop();
                relaxButton.setText("Relax");
                playing = false;
            }
        }
        if(event.getSource() == emergencyButton){
            goHome();
//            Emergency.setSender("Bloodorangebishopfish");
//            Emergency.setSenderPassword("Software1");
//            Emergency.setRecipient("Bloodorangebishopfish@gmail.com");
//
//            Emergency e = new Emergency();
//            try {
//                e.run(50,50, 2000, 1500, "/bishopfish/curie.css", "destNode", "originNode");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
        }
        else if(event.getSource() == servicesButton){
            goHome();
            loadFxml("chooseServiceRequest.fxml");
        }
        else if(event.getSource() == schedulerButton){
            goHome();
            //need to update this path when merging reservations
            loadFxml("roomView.fxml");
        }
        else if(event.getSource() == directionsButton){
            goHome();
            CSI.getInstance().load("aStar.fxml");
            loadFxml("aStar.fxml", directionsFxml);
            CSI.getInstance().getHpController().getDirectionsFxml().setVisible(true);
            CSI.getInstance().getHpController().getDirectionsFxml().setMouseTransparent(false);
        }
        else if(event.getSource() == mapBuilderButton){
            goHome();
            loadFxml("mapBuilderDisplay.fxml");
        }
        else if(event.getSource() == adminButton){
            goHome();
            loadFxml("adminLandingPage.fxml");
        }
        else if(event.getSource() == settingsButton){
            goHome();
            loadFxml("settingsPage.fxml");
        }
        else if(event.getSource() == aboutButton){
            goHome();
            loadFxml("aboutPage.fxml");
        }
        else if(event.getSource() == relaxButton){
            if (playing) {
                player.stop();
                relaxButton.setText("Relax");
                playing = false;
            } else {
                player = new NewPlayer("bishopfish/FreeMindfulness3MinuteBreathing.wav");
                player.start();
                relaxButton.setText("Stop");
                playing = true;
            }
        }
    }


    public void onClick(ActionEvent event) {
        setButtonsToWhite();
        ((Button) event.getSource()).setStyle("-fx-background-color: #6ca0fa");
        if(event.getSource() == floorL2) {
            imgView.setImage(image01);
            gpane.setContent(imgView);
            setGroup(groupMap01);
        }
        if(event.getSource() == floorL1){
            imgView.setImage(image00);
            gpane.setContent(imgView);
            setGroup(groupMap00);
        }
        if(event.getSource() == floorG){
            imgView.setImage(image0);
            gpane.setContent(imgView);
            setGroup(groupMap0);
        }
        if(event.getSource() == floor1){
            imgView.setImage(image1);
            gpane.setContent(imgView);
            setGroup(groupMap1);
        }
        if(event.getSource() == floor2){
            imgView.setImage(image2);
            gpane.setContent(imgView);
            setGroup(groupMap2);
        }
        if(event.getSource() == floor3){
            imgView.setImage(image3);
            gpane.setContent(imgView);
            setGroup(groupMap3);
        }
    }

    public void setButtonsToWhite(){
        floor3.setStyle("-fx-background-color: white");
        floor2.setStyle("-fx-background-color: white");
        floor1.setStyle("-fx-background-color: white");
        floorG.setStyle("-fx-background-color: white");
        floorL1.setStyle("-fx-background-color: white");
        floorL2.setStyle("-fx-background-color: white");
    }


    public void onClick(int i) {
        if(i == 0) {
            imgView.setImage(image3);
            gpane.setContent(imgView);
            setGroup(groupMap3);
            setButtonsToWhite();
            floor3.setStyle("-fx-background-color: #6ca0fa");
        }
        if(i == 1) {
            imgView.setImage(image2);
            gpane.setContent(imgView);
            setGroup(groupMap2);
            setButtonsToWhite();
            floor2.setStyle("-fx-background-color: #6ca0fa");

        }
        if(i == 2) {
            imgView.setImage(image1);
            gpane.setContent(imgView);
            setGroup(groupMap1);
            setButtonsToWhite();
            floor1.setStyle("-fx-background-color: #6ca0fa");

        }
        if(i == 3) {
            imgView.setImage(image0);
            gpane.setContent(imgView);
            setGroup(groupMap0);
            setButtonsToWhite();
            floorG.setStyle("-fx-background-color: #6ca0fa");

        }
        if(i == 4) {
            imgView.setImage(image00);
            gpane.setContent(imgView);
            setGroup(groupMap00);
            setButtonsToWhite();
            floorL1.setStyle("-fx-background-color: #6ca0fa");


        }
        if(i == 5) {
            imgView.setImage(image01);
            gpane.setContent(imgView);
            setGroup(groupMap01);
            setButtonsToWhite();
            floorL2.setStyle("-fx-background-color: #6ca0fa");
        }

    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }


    public void setGroup(Group g){
        imgAnchor.getChildren().clear();
        imgAnchor.getChildren().add(g);

//        for (Group gps : groups)
//        {
//            if(gps == g){
//                gps.setVisible(true);
//            }
//            else{
//                gps.setVisible(false);
//            }
//        }
    }

    public ArrayList<Group> getFloorGroups(){
        return groups;
    }

    //public Button getNextFloorButton(){return nextFloor;}
    //public Button getPreviousFloorButton(){return previousFloor;}

    public ScrollPane getScrollPane(){return scrollPane;}

    public JFXTextArea getTextArea(){return textDirectionsBox;}

    @Override
    public AnchorPane getSubP() {
        return subControllerPane;
    }

    @Override
    public AnchorPane getDirectionsFxml(){return directionsFxml;}

    public ImageView getImgView() { return imgView;}

    public String getFxml() {
        return "homePage.fxml";
    }

    public Canvas getPathCanvas() {
        return pathCanvas;
    }
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    public ToggleButton getToggleDirections() {
        return toggleDirections;
    }

    public void setToggleDirections(ToggleButton toggleDirections) {
        this.toggleDirections = toggleDirections;
    }

    public String getDirectionsFlag() {
        return directionsFlag;
    }

    public void setDirectionsFlag(String directionsFlag) {
        this.directionsFlag = directionsFlag;
    }


}




