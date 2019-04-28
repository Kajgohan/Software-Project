package bishopfish.controllers;

import bishopfish.scheduler.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DemoOptionsController extends Controller implements Initializable {

    @FXML
    private JFXButton changeOccupancy;

    @FXML
    private JFXButton viewRoomList;

    @FXML
    private JFXButton goAdmin;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    //home button
    public void onHomeClick(ActionEvent event) throws IOException {
        loadNewScene(event, "homePage.fxml");
    }

    public void toOccupancy(ActionEvent event) throws IOException {
        loadNewScene(event, "forcedBookingPage.fxml");
    }

    public void toRoomList(ActionEvent event) throws IOException {
        loadNewScene(event, "roomListView.fxml");
    }

    public void toAdmin(ActionEvent event) throws IOException {
        loadNewScene(event, "adminLandingPage.fxml");
    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        //no real input to validate
        return true;
    }

}
