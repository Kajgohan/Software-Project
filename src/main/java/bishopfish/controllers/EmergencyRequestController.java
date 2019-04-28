package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.emergency.EmergencyRequestEntry;
import bishopfish.servicerequest.SendMail;
import bishopfish.servicerequest.ServiceRequestEntry;
import bishopfish.servicerequest.ThisNode;
import bishopfish.utils.TakePicture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Array;
@Deprecated
public class EmergencyRequestController extends Controller {
    Random rand = new Random();
    //make the local node for the kiosk

    ThisNode localNode = new ThisNode();
    TakePicture userPic = new TakePicture();
//    @FXML
//    private Button homeButton;
    @FXML
    private Text alertText;

    @FXML
    public void initialize(){
        alertText.setVisible(false);
    }

    private DBBuffer<EmergencyRequestEntry> dbb;

//    @FXML
//    public void onHomeClick(ActionEvent event) throws IOException {
//        loadFxml("homePage.fxml");
//    }

    public String randID(){

        int num = rand.nextInt(100000);
        return String.valueOf(num);
    }

    public void addToDatabase(String emergencyType){
        //code type, nodeID, emergencyTime, photoName
        String[] photoInfo = TakePicture.capture();
        EmergencyRequestEntry newRequest = new EmergencyRequestEntry(randID(), emergencyType, "Local", photoInfo[1], photoInfo[0]);
        dbb = new DBBuffer<>(DBMI.Emergency.value);
        dbb.add(newRequest);
    }

    //send an email alerting for a code blue using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodeBlueClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Clear nearby hallways");
        alertText.setFill(Color.web("#305da5"));
        alertText.setVisible(true);
        SendMail.sendmail("CODE BLUE", "Code Blue. Clear nearby hallways.", localNode.getNode().getName(), "security@bwh.org");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code Blue");
    }

    //send an email alerting for a code pink using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodePinkClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Infant abduction");
        alertText.setFill(Color.PINK);
        alertText.setVisible(true);
        SendMail.sendmail("CODE PINK", "Code Pink. Infant abduction.", localNode.getNode().getName(), "security@bwh.org");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code Pink");
    }

    //send an email alerting for a code white using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodeWhiteClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Violent person in the building");
        alertText.setFill(Color.web("#f1f1f1"));
        alertText.setVisible(true);
        SendMail.sendmail("CODE WHITE", "Code White. Violent Person in the building.", localNode.getNode().getName(), "security@bwh.org");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code White");
    }

    //send an email alerting for a code red using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodeRedClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Fire at Brigham and Women's Hospital");
        alertText.setFill(Color.web("#e72626"));
        alertText.setVisible(true);
        SendMail.sendmail("CODE RED", "Code Red. Fire at Brigham and Women's Hospital.", localNode.getNode().getName(), "firedepartment@localfirestation.gov");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code Red");
    }

    //send an email alerting for a code amber using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodeAmberClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Missing Child");
        alertText.setFill(Color.ORANGE);
        alertText.setVisible(true);
        SendMail.sendmail("CODE AMBER", "Code Amber. Missing Child.", localNode.getNode().getName(), "security@bwh.org");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code Amber");
    }

    //send an email alerting for a code brown using the bishopfish.servicerequest.SendMail class and sendmail method
    @FXML
    public void onCodeBrownClick(ActionEvent event) throws IOException {
        userPic.capture();
        alertText.setText("Cleanup Needed");
        alertText.setFill(Color.SIENNA);
        alertText.setVisible(true);
        SendMail.sendmail("CODE BROWN", "Code Brown. Cleanup Needed.", localNode.getNode().getName(), "buildingservices@bwh.org");
        showAlert(Alert.AlertType.CONFIRMATION, ((Node)event.getSource()).getScene().getWindow(), "Confirmation", "Emergency Alert Sent!");
        addToDatabase("Code Brown");
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }

}
