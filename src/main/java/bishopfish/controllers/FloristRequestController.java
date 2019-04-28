package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.FloristRequest;
import bishopfish.servicerequest.ServiceRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
//import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FloristRequestController extends Controller implements Initializable{

    @FXML
    JFXTextField senderName;

    @FXML
    JFXTextField receiverName;

    @FXML
    JFXComboBox floralDropdown;

    private DBBuffer<ServiceRequestEntry> dbb;

    private ObservableList<String> floralDropdownList;

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("6") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, senderName.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
        floralDropdown.validate();
        senderName.validate();
        receiverName.validate();

        if (!floralDropdown.validate()) {
            floralDropdown.requestFocus();
            return false;
        }
        if (!senderName.validate()) {
            senderName.requestFocus();
            return false;
        }
        if (!receiverName.validate()) {
            receiverName.requestFocus();
            return false;
        }
//        if ((senderName.getText().equals("")) || (floralDropdown.getValue().equals("")) || (receiverName.getText().equals(""))) {
//            showAlert(Alert.AlertType.ERROR, senderName.getScene().getWindow(), "Form Error!", "Please fill all fields!");
//            return false;
//        }
        else return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        floralDropdownList = DBCustom.getFloralRequest();
        floralDropdown.setItems(floralDropdownList);
//        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
//        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
//        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
//
//
//        addValidators(senderName,valid1);
//        addValidators(receiverName,valid2);
//        addValidators(floralDropdown,valid3);

    }

    public void onClick(ActionEvent event)throws IOException {
        if(!hasValidInput(event)) return;
        FloristRequest floralRequest = new FloristRequest(new Node("Local"), false, senderName.getText(), floralDropdown.getValue() + " for " + receiverName.getText());
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        dbb.add(floralRequest.getServiceRequestEntry());
        homeButton(event);
    }

}

