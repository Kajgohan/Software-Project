package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.servicerequest.ITRequest;
import bishopfish.servicerequest.SecurityRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import bishopfish.servicerequest.ThisNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class GenericServiceRequestController extends Controller implements Initializable {

    //create the node for the kiosk to use in the other methods later in the class
    bishopfish.map.Node localNode = new bishopfish.map.Node("Local");

    @FXML
    private Label serviceTypeL;

    //create the name field from the FXML to run functions on
    @FXML
    private TextField nameField;

    //create the ticket description from the FXML to run functions on
    @FXML
    private TextArea ticketDescription;

    private DBBuffer<ServiceRequestEntry> dbb;

    @Override
    protected boolean hasValidInput(ActionEvent event) {
        /*if((getPermissions() == null) || (
                ((!getPermissions().contains("B") && srType.equals("IT Request")) ||
                 (!getPermissions().contains("A") && srType.equals("Security Requests"))
                ) && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
        }
        else*/ if (nameField.getText().isEmpty()) {
            showAlert(ERROR, ((Node) event.getSource()).getScene().getWindow(), "Name Error", "Please Input A Name");
            return false;
        } else if (ticketDescription.getText().isEmpty()) {
            showAlert(ERROR, ((Node) event.getSource()).getScene().getWindow(), "Content Error", "Please Write a Ticket Description");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.serviceTypeL.setText(CSI.getInstance().getFent().getFeatureName());
        try {
            dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSubmitClick(ActionEvent event) {
        if (!hasValidInput(event)) {
            return;
        }
        switch (CSI.getInstance().getFent().getId()) {
            case "ITRR":
                //make a new itrequest
                ITRequest itRequest = new ITRequest(localNode, false, nameField.getText(), ticketDescription.getText());
//                itRequest.fulfillRequest(); //fulfill the request using the new itrequest made above from the fields typed in by the user and send the email
                dbb.add(itRequest.getServiceRequestEntry());
                homeButton(event);
                break;
            case "SECR":
                //make a new securityrequest using the text from the fields that the user typed in
                SecurityRequest securityRequest = new SecurityRequest(localNode, false, nameField.getText(), ticketDescription.getText());
//                securityRequest.fulfillRequest(); //fulfill the request by using the new securityrequest and send the email
                dbb.add(securityRequest.getServiceRequestEntry());
                homeButton(event);
                break;

        }
    }
}
