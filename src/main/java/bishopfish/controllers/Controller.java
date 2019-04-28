package bishopfish.controllers;

import bishopfish.db.DBMI;
import bishopfish.utils.FeatureTypeEntry;
import com.jfoenix.controls.*;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.util.Timer;

public abstract class Controller {

    private final boolean DEBUG = false;

//    protected Stack<String> backFxmlStack;
//    protected String curFxml;
//    // _ means admin in permissions
//    protected String permissions;

    //private HomePageController hpController;


    // function to check if inputs match database restrictions and sends meaningful alerts
    protected abstract boolean hasValidInput(ActionEvent e);

//    // create an abstract contoller
//    Controller c = new Controller(){
//        @Override
//        protected boolean hasValidInput(ActionEvent e) {
//            return false;
//        }
//    };


    public void secondInit() {}


//    public void passPermissions(Controller c){
//        System.out.println(this.getClass().toString() + " permissions: " + permissions);
//        c.setPermissions(permissions);
//    }

//    public void passBack(Controller c, String newPath, String backPath) {
//        Stack<String> backStack = this.getBackFxmlHeap();
//        c.setBackFxmlStack(backStack);
//        String backString = backFilter(backPath);
//        if (!backString.isEmpty()) {
//            backFxmlStack.push(backString);
//        }
//        c.setBackFxmlStack(backFxmlStack);
//        c.setCurFxml(newPath);
//    }

//    public void passHomePage(Controller c, HomePageController hpc) {
//        c.setHpController(hpc);
//    }

    @FXML @Deprecated
    public void loadNewScene(ActionEvent e, String scenePath) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource(scenePath));
//        //this line gets the stage
//        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        window.setScene(new Scene(root));
//        window.show();
        System.out.println(this.getClass().getName() + ": loadNewScene" + "\n" + CSI.getInstance());
        loadNewScenePass(e, scenePath, CSI.getInstance().getCurFxml());
    }

    @FXML @Deprecated
    public void loadNewScenePass(Event e, String scenePath, String backPath) throws IOException {
//        Object[] rootAndController = loadNewBack(scenePath, backPath);

        Object[] rootAndController = initLoader(scenePath);
        Parent root = (Parent) rootAndController[0];
        Controller controller = (Controller) rootAndController[1];
//        passPermissions(controller);
//        passBack(controller, scenePath, backPath);
//        passHomePage(controller, hpController);
        controller.secondInit();
        loadWindow(e, root);
        //this.setScene(scenePath);
    }

//    public void setScene(ActionEvent e, String scenePath){
//
//
//    }

    @Deprecated
    public void loadWindow(Event e, Parent root)  {
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root)); // we are making new scenes everytime which is causing crashes
        window.setMaximized(false);
        window.setMaximized(true);
        window.show();
    }

    @FXML
    public Object[] initLoader(String newPath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(newPath)));
        Parent root = loader.load();
        Controller controller = loader.getController();
        return new Object[] { root, controller };
    }

    @FXML @Deprecated
    public Object[] loadNewBack(String newPath, String backPath) throws IOException {
        System.out.println(this.getClass().getName() + " loading with " + newPath + ", " + backPath);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(newPath)));
        //System.out.println("here6784" + loader.getLocation());
        Parent root = loader.load();
        Controller controller = loader.getController();
        //System.out.println(controller);
        CSI.getInstance().addToBackStack(backPath);
        return new Object[] { root, controller };
    }

    @FXML
    public void outerBackButton(Event e) throws IOException {
        if(DEBUG) System.out.println("outerBackButton");
        if(DEBUG) System.out.println(CSI.getInstance());
        String backPage = CSI.getInstance().popBackStack();
        CSI.getInstance().setPermissions("");
        CSI.getInstance().setCurUser(null);
        if (!backPage.isEmpty()) {
            CSI.getInstance().setCurFxml(backPage);
            loadOuterFxml(backPage);
        }
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("outerBackButton");
    }

    // for loading for pages before the homepage
    public void loadOuterFxml(String newPath) throws IOException {
        if(DEBUG) System.out.println("start loadOuterFxml\t" + this.getClass().getName());
        if(DEBUG) System.out.println(CSI.getInstance());
        CSI.getInstance().load(newPath);
        AnchorPane loadingPane = CSI.getInstance().getMasterController().getMasterPane();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource(newPath));
        loadingPane.getChildren().clear();
        newLoadedPane.prefWidthProperty().bind(loadingPane.widthProperty());
        newLoadedPane.prefHeightProperty().bind(loadingPane.heightProperty());
        loadingPane.getChildren().add(newLoadedPane);
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("end loadOuterFxml");
    }

    public void loadFxml(String newPath) throws IOException {
        if(DEBUG) System.out.println("start loadFxml\t" + this.getClass().getName());
        if(DEBUG) System.out.println(CSI.getInstance());
        CSI.getInstance().load(newPath);
        loadFxml(newPath, CSI.getInstance().getHpController().getSubP());
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("end loadFxml");
    }

    public void loadFxml(String newPath, Pane loadingPane) throws IOException {
        if(DEBUG) System.out.println("start newFxml\t" + this.getClass().getName());
        if(DEBUG) System.out.println(CSI.getInstance());
        CSI.getInstance().setMementoActive(true);
        CSI.getInstance().clearAllAstar();
        loadingPane.setMouseTransparent(false);
        loadingPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource(newPath));
        newLoadedPane.prefWidthProperty().bind(loadingPane.widthProperty());
        newLoadedPane.prefHeightProperty().bind(loadingPane.heightProperty());
        loadingPane.getChildren().add(newLoadedPane);
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("end newFxml");
    }

    public void loadFxml(String newPath, FeatureTypeEntry fent) throws IOException {
        CSI.getInstance().setFent(fent);
        loadFxml(newPath);
    }

        @FXML
    public void homeButton(Event e) {
        goHome();
    }

    @FXML
    public void backButton(Event e) throws IOException {
        goBack();
    }

    @FXML
    public void signOutButton(Event e) throws IOException {
        signOut();
    }

    public void goHome() {
        if(DEBUG) System.out.println("START HOME");
        if(DEBUG) System.out.println(CSI.getInstance());
        CSI.getInstance().resetCur();
        CSI.getInstance().resetBackStack();
        CSI.getInstance().getHpController().getSubP().getChildren().clear();
        CSI.getInstance().getHpController().getSubP().setPickOnBounds(false);
        CSI.getInstance().clearAllAstar();
        for(Group g: CSI.getInstance().getHpController().getFloorGroups()){
            g.getChildren().clear();
        }
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("END HOME");
    }

    public void goBack() throws IOException {
        if(DEBUG) System.out.println("START BACK");
        if(DEBUG) System.out.println(CSI.getInstance());
        String backPage = CSI.getInstance().popBackStack();
        if (backPage.equals("homePage.fxml") || backPage.equals("guestHomePage.fxml") || backPage.equals("aStar.fxml")) {
            goHome();
        } else {
            CSI.getInstance().setCurFxml(backPage);
            loadFxml(backPage, CSI.getInstance().getHpController().getSubP());
        }
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("END BACK");
    }

    public void signOut() throws IOException {
        if(DEBUG) System.out.println("START SIGNOUT");
        if(DEBUG) System.out.println(CSI.getInstance());
        CSI.getInstance().setPermissions("");
        CSI.getInstance().setCurUser(null);
        CSI.getInstance().getMementoTimer().cancel();
        CSI.getInstance().setHpController(null);
        CSI.getInstance().resetBackStack();
        CSI.getInstance().setCurFxml("");
        loadOuterFxml("welcomeScreen.fxml");
        if(DEBUG) System.out.println(CSI.getInstance());
        if(DEBUG) System.out.println("END SIGNOUT");
    }

    @FXML @Deprecated
    public Parent loadDBViewer(ActionEvent e, DBMI.DBInfo info) throws IOException {
        Object[] rootAndController = loadNewBack("/bishopfish/controllers/databaseViewer.fxml", CSI.getInstance().getCurFxml() );
        Parent root = (Parent) rootAndController[0];
        DatabaseViewerController controller = (DatabaseViewerController) rootAndController[1];
        System.out.println("here1234" + info);
        controller.setDatabaseSelect(info);
        loadWindow(e, root);
        return root;
    }

    @Deprecated
    protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void timer(MouseEvent e) {
        Timer mementoTimer = CSI.getInstance().getMementoTimer();
        CSI.getInstance().setMementoActive(true);
    }


    public interface ObjectFilter {
        /**
         * takes in object and returns it if should use; otherwise null
         * @param o the value to filter
         * @return the same object or null if value should be ignored
         */
        Object filter(Object o);
    }

    public ObservableList getFilteredOL(ObservableList ol, ObjectFilter filter){
        ObservableList newOl = FXCollections.observableArrayList();
        for (int i = 0; i < ol.size(); i++) {
            Object filterdValue = filter.filter(ol.get(i));
            if (filterdValue != null) {
                newOl.add(filterdValue);
            }
        }
        return newOl;
    }

    public void addValidators(JFXTextField field, ValidatorBase validator) {
        field.getValidators().add(validator);
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                field.validate();
            }
        });
    }

    public void addValidators(JFXComboBox field, ValidatorBase validator) {
        field.getValidators().add(validator);
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                field.validate();
            }
        });
    }

    public void addValidators(JFXTextArea field, ValidatorBase validator) {
        field.getValidators().add(validator);
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                field.validate();
            }
        });
    }

    public void addValidators(JFXTimePicker field, ValidatorBase validator) {
        field.getValidators().add(validator);
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                field.validate();
            }
        });
    }

    public void addValidators(JFXDatePicker field, ValidatorBase validator) {
        field.getValidators().add(validator);
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                field.validate();
            }
        });
    }

//    public void addComboValidators(JFXComboBox field, ValidatorBase validator) {
//        ValidationFacade facade = new ValidationFacade();
//        facade.setControl(field);
//        facade.getValidators().add(validator);
//        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//            if (!newValue) {
//                facade.
//            }
//        });
//    }

//    public String getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(String permissions) {
//        this.permissions = permissions;
//    }

    // returnt he subController pan from the home page
//    public AnchorPane getSubP() {
//        return this.getHpController().getSubControllerPane();
//    }


}