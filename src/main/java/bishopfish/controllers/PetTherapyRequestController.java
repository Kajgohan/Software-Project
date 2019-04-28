package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.FloristRequest;
import bishopfish.servicerequest.PetTherapyRequest;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class PetTherapyRequestController extends Controller implements Initializable {
    @FXML
    JFXComboBox<String> speciesDropdown;

    @FXML
    JFXTextField patientTf;

    @FXML
    JFXDatePicker datePick;

    @FXML
    JFXTimePicker timePick;

    @FXML
    JFXButton homeBtn;

    @FXML
    JFXButton backBtn;

    @FXML
    JFXButton requestBtn;

    private ObservableList<String> speciesDropDownlist;
    private DBBuffer<ServiceRequestEntry> dbb;

    public PetTherapyRequestController() throws UnsupportedEncodingException, FileNotFoundException{

    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("7") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, patientTf.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
//        if ((patientTf.getText().equals("")) || (speciesDropdown.getValue() == null || (timePick.getValue() == null|| (datePick.getValue() == null)))){
//            showAlert(Alert.AlertType.ERROR, patientTf.getScene().getWindow(), "Form Error!", "Please fill in all fields");
//            return false;
//        }
        speciesDropdown.validate();
        patientTf.validate();
        datePick.validate();
        timePick.validate();
        if (!speciesDropdown.validate()) {
            speciesDropdown.requestFocus();
            return false;
        }
        if (!patientTf.validate()) {
            patientTf.requestFocus();
            return false;
        }
        if (!datePick.validate()) {
            datePick.requestFocus();
            return false;
        }
        if (!timePick.validate()) {
            timePick.requestFocus();
            return false;
        }
        return true;
    }

    public void initialize(URL location, ResourceBundle resources) {
        speciesDropDownlist = DBCustom.getPetTherapyRequest();
        speciesDropdown.setItems(speciesDropDownlist);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");

        addValidators(speciesDropdown,valid1);
        addValidators(patientTf,valid2);
        addValidators(datePick,valid3);
        addValidators(timePick,valid4);
    }

    public void buttonClick(ActionEvent event) throws IOException {
        //System.out.println(getPermissions());
        if(!hasValidInput(event)) return;
            PetTherapyRequest petTherapyRequest = new PetTherapyRequest(new Node("Local"), false, patientTf.getText(), "Requested " + speciesDropdown.getValue() + " for: " + patientTf.getText() + " on: " + datePick.getValue() + " at: " + timePick.getValue());
           // System.out.println("Requested " + speciesDropdown.getValue() + " for: " + patientTf + " on: " + datePick.getValue() + " at: " + timePick.getValue());
            dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
            dbb.add(petTherapyRequest.getServiceRequestEntry());
            homeButton(event);
            //loadNewScene(event, "homePage.fxml");

    }



}
