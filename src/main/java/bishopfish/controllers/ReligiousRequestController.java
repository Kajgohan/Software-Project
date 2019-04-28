package bishopfish.controllers;

import bishopfish.controllers.Controller;
import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.ReligousRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReligiousRequestController extends Controller implements Initializable {

    @FXML
    private JFXTextField staffField;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXTextField otherField;

    @FXML
    private JFXTextField religionField;

    @FXML
    private JFXCheckBox emergencyToggle;

    @FXML
    private JFXComboBox<String> serviceDropdown;

    @FXML
    private ObservableList<String> serviceDropdownList;


    @FXML
    private JFXButton homeButton;


    private DBBuffer<ServiceRequestEntry> dbb;

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*
        if (CSI.getInstance().hasPermission("RLGR")) {
            showAlert(Alert.AlertType.ERROR, religionField.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
//        if ((religionField.getText().equals("")) || (serviceDropdown.getValue().equals("")) || (religionField.getText().equals("")) || (staffField.getText().equals(""))) {
//            showAlert(Alert.AlertType.ERROR, religionField.getScene().getWindow(), "Form Error!", "Please fill all fields!");
//            return false;
//        }
        serviceDropdown.validate();
        staffField.validate();
        religionField.validate();
        if (!serviceDropdown.validate()) {
            serviceDropdown.requestFocus();
            return false;
        }
        if (serviceDropdown.getValue().equals("Other")) {
            if (!otherField.validate()) {
                otherField.requestFocus();
                return false;
            }
        }
        if (!staffField.validate()) {
            staffField.requestFocus();
            return false;
        }
        if (!religionField.validate()) {
            religionField.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceDropdown.setItems(DBCustom.getReligiousServices());
        otherField.setEditable(false);
        otherField.setStyle("-fx-background-color: lightgray;");

        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Choose a Service");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");
        addValidators(serviceDropdown,valid1);
        otherField.getValidators().add(valid2);
        addValidators(staffField,valid3);
        addValidators(religionField,valid4);

    }


    public void submitButton(ActionEvent actionEvent) throws IOException {
        if(!hasValidInput(actionEvent)) return;
       // loadNewScene(actionEvent, "homePage.fxml");
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        String requestType;
        if (!emergencyToggle.getText().equals("")) {
            requestType = ("Emergency: " + emergencyToggle.isSelected() + " " + religionField.getText() + " " + serviceDropdown.getValue() + " Requested by " + staffField.getText());
            System.out.println(emergencyToggle.getText());
        }
        else {
            requestType = (religionField.getText() + " " + serviceDropdown.getValue() + " Requested by " + staffField.getText());
        }
        ReligousRequest r = new ReligousRequest(new Node("Local"), false, staffField.getText(), requestType);
        dbb.add(r.getServiceRequestEntry());
        homeButton(actionEvent);

    }

    public void onChoice(ActionEvent event) {
        if (serviceDropdown.getValue().equals("Other")) {
            otherField.setEditable(true);
            otherField.setStyle("-fx-background-color: white;");
        } else {
            otherField.setEditable(false);
            otherField.setText("");
            otherField.setStyle("-fx-background-color: lightgray;");
            otherField.resetValidation();
        }
    }

}
