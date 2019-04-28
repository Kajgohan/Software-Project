package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.AccommodationRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AccommodationRequestController extends Controller implements Initializable{

    @FXML
    JFXTextField roomNumber;

    @FXML
    JFXTextField referringDoctor;


    @FXML
    JFXComboBox accommodationDropdown;



    private DBBuffer<ServiceRequestEntry> dbb;

    private ObservableList<String> accommodationDropdownList;


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("9") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, roomNumber.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
        }



        else*/
        roomNumber.validate();
        accommodationDropdown.validate();
        referringDoctor.validate();
//        if ((roomNumber.getText().equals("")) || (accommodationDropdown.getValue()==null || (referringDoctor.getText().equals("")))) {
//            showAlert(Alert.AlertType.ERROR, roomNumber.getScene().getWindow(), "Form Error!", "Please fill all fields!");
//            return false;
//        }
        if (!accommodationDropdown.validate()) {
            accommodationDropdown.requestFocus();
            return false;
        }
        if (!roomNumber.validate()) {
            roomNumber.requestFocus();
            return false;
        }
        if (!referringDoctor.validate()) {
            referringDoctor.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accommodationDropdownList = DBCustom.getAccommodationServices();
        accommodationDropdown.setItems(accommodationDropdownList);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        addValidators(roomNumber,valid1);
        addValidators(referringDoctor,valid2);
        addValidators(accommodationDropdown,valid3);


    }

    //public ServiceRequestEntry(String ticketNum, String name, String requestType, String ticketContent)
    public void onClick(ActionEvent event) throws IOException {
        if(!hasValidInput(event)) return;
        AccommodationRequest accommodationRequest = new AccommodationRequest(new Node("Local"), false, roomNumber.getText(), accommodationDropdown.getValue() + " for " + referringDoctor.getText());
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        dbb.add(accommodationRequest.getServiceRequestEntry());
        homeButton(event);
    }

}

