package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.InterpreterRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

public class InterpreterRequestController extends Controller implements Initializable {

    @FXML
    public JFXButton homeButton;

    @FXML
    public JFXButton submitButton;

    @FXML
    JFXComboBox<String> languageDropdown;

    //create the name field from the FXML to run functions on
    @FXML
    private JFXTextField otherField;

    @FXML
    private ObservableList<String> languageFieldList;

    private DBBuffer<ServiceRequestEntry> dbb;


    public InterpreterRequestController() throws UnsupportedEncodingException, FileNotFoundException {
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("3") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, otherField.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
//        if ((languageDropdown.getValue() == null) && otherField.getText().equals("")){
//            showAlert(Alert.AlertType.ERROR, otherField.getScene().getWindow(), "Form Error!", "Please fill with a language!");
//            return false;
//        }
        if (!languageDropdown.validate()) {
            languageDropdown.requestFocus();
            return false;
        }
        if (languageDropdown.getValue().equals("Other")) {
            if (!otherField.validate()) {
                otherField.requestFocus();
                return false;
            }
        }
        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languageFieldList = DBCustom.getInterpreterRequest();
        languageDropdown.setItems(languageFieldList);
        otherField.setEditable(false);

        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is Required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Choose a language");

        addValidators(languageDropdown,valid1);
        otherField.getValidators().add(valid2);
    }

    public void onClick(ActionEvent event) throws IOException {
        if (!hasValidInput(event)) return;
        InterpreterRequest interpreterRequest = new InterpreterRequest(new Node("Local"), false, "Kit", "Interpreter for " + languageDropdown.getValue() + otherField.getText());
        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        dbb.add(interpreterRequest.getServiceRequestEntry());
        homeButton(event);

    }

    public void onChoice(ActionEvent event) {
        if (languageDropdown.getValue().equals("Other")) {
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


