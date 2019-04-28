package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.InterpreterRequest;
import bishopfish.servicerequest.PetTherapyRequest;
import bishopfish.servicerequest.ReadingRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadingRequestController extends Controller implements Initializable {

    @FXML
    JFXComboBox<String> bookDropdown;

    @FXML
    JFXTextField patientRoom;

    @FXML
    JFXDatePicker datePick;

    @FXML
    JFXTimePicker timePick;

    @FXML
    public JFXButton homeButton;

    @FXML
    JFXButton backBtn;

    @FXML
    public JFXButton requestBtn;


    @FXML
    private ObservableList<String> bookFieldDropdownList;

    private DBBuffer<ServiceRequestEntry> dbb;


    public ReadingRequestController() throws UnsupportedEncodingException, FileNotFoundException {
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*
        if((getPermissions() == null) ||(!getPermissions().contains("8") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, patientRoom.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
//        if ((patientRoom.getText().equals("")) || (bookDropdown.getValue() == null || (timePick.getValue() == null|| (datePick.getValue() == null)))){
//            showAlert(Alert.AlertType.ERROR, patientRoom.getScene().getWindow(), "Form Error!", "Please fill in all fields");
//            return false;
//        }
        bookDropdown.validate();
        patientRoom.validate();
        datePick.validate();
        timePick.validate();
        if (!bookDropdown.validate()) {
            bookDropdown.validate();
            return false;
        }
        if (!patientRoom.validate()) {
            patientRoom.validate();
            return false;
        }
        if (!datePick.validate()) {
            datePick.validate();
            return false;
        }
        if (!timePick.validate()) {
            timePick.validate();
            return false;
        }
        return true;
    }

    public void initialize(URL location, ResourceBundle resources) {
        bookFieldDropdownList = DBCustom.getReadingRequest();
        bookDropdown.setItems(bookFieldDropdownList);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");

        addValidators(bookDropdown,valid1);
        addValidators(patientRoom,valid2);
        addValidators(datePick,valid3);
        addValidators(timePick,valid4);
    }

    public void buttonClick(ActionEvent event) throws IOException {
        if(!hasValidInput(event)) return;
        ReadingRequest readingRequest = new ReadingRequest(new Node("Local"), false, patientRoom.getText(), "Requested " + bookDropdown.getValue() + " for: " + patientRoom.getText() + " on: " + datePick.getValue() + " at: " + timePick.getValue());
        // System.out.println("Requested " + speciesDropdown.getValue() + " for: " + patientTf + " on: " + datePick.getValue() + " at: " + timePick.getValue());
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        dbb.add(readingRequest.getServiceRequestEntry());
        homeButton(event);
        //loadNewScene(event, "homePage.fxml");

    }

}


