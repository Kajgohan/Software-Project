package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.ExternalTransportRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;


public class ExternalTransportController extends Controller implements Initializable{

    @FXML
    JFXTextField senderName;

    @FXML
    JFXTimePicker departureTime;

    @FXML
    JFXComboBox transportTypeDropdown;

    @FXML
    JFXComboBox wheelchairOptionsDropdown;

    private DBBuffer<ServiceRequestEntry> dbb;

    private ObservableList<String>  transportTypeList;

    private ObservableList<String> wheelchairOptionsList;

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("0") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, senderName.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
        transportTypeDropdown.validate();
        senderName.validate();
        departureTime.validate();
        wheelchairOptionsDropdown.validate();

        if (!transportTypeDropdown.validate()) {
            transportTypeDropdown.requestFocus();
            return false;
        }
        if (!senderName.validate()) {
            senderName.requestFocus();
            return false;
        }
        if (!departureTime.validate()) {
            departureTime.requestFocus();
            return false;
        }
        if (!wheelchairOptionsDropdown.validate()) {
            wheelchairOptionsDropdown.requestFocus();
            return false;
        }

//        if ((senderName.getText().equals("")) || (transportTypeDropdown.getValue() == null) || (departureTime.getValue() == null) || (wheelchairOptionsDropdown.getValue() == null)) {
//            showAlert(Alert.AlertType.ERROR, senderName.getScene().getWindow(), "Form Error!", "Please fill all fields!");
//            return false;
//        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transportTypeList = DBCustom.getTransportRequest();
        transportTypeDropdown.setItems(transportTypeList);
        wheelchairOptionsList = DBCustom.getWheelchairOptionRequest();
        wheelchairOptionsDropdown.setItems(wheelchairOptionsList);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");

        addValidators(senderName,valid1);
        addValidators(departureTime,valid2);
        addValidators(transportTypeDropdown,valid3);
        addValidators(wheelchairOptionsDropdown,valid4);

    }

    //public ServiceRequestEntry(String ticketNum, String name, String requestType, String ticketContent)
    public void onClick(ActionEvent event){
        if(!hasValidInput(event))return;
        ExternalTransportRequest externalRequest = new ExternalTransportRequest(new Node("Local"), false, senderName.getText(), transportTypeDropdown.getValue() + "with assistance type" + wheelchairOptionsDropdown.getValue() + " at " + departureTime.getValue());
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        dbb.add(externalRequest.getServiceRequestEntry());
        homeButton(event);
    }

}