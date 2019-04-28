package bishopfish.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServiceRequestLandingPageController extends Controller implements Initializable {
    @FXML
    private Button toEmergencyPage;
    @FXML
    private Button toServiceRequestPage;
    @FXML
    private Button home;
    @FXML
    private Button back;

    public void emergencyButtonClick(ActionEvent event) throws IOException {
        loadFxml("emergencyRequestPage.fxml");
    }

    public void serviceButtonClick(ActionEvent event) throws IOException {
        //loadFxml(getSubP(), empty);
    }

    public void viewRequests(ActionEvent event) throws IOException{
        loadFxml("databaseViewer.fxml");
    }
    //need to give it the main home page
    public void homeButtonClick(ActionEvent event) throws IOException {
        loadFxml("requestLandingPage.fxml");
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
