package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.employees.DepartmentEntry;
import bishopfish.employees.Employee;
import bishopfish.employees.EmployeeEntry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployeeController extends Controller implements Initializable {
    private DBBuffer<DepartmentEntry> depDBB = new DBBuffer<>(DBMI.Departments.value);
    private DBBuffer<EmployeeEntry> empDBB = new DBBuffer<>(DBMI.Employees.value);
    private ObservableList<DepartmentEntry> allDepartments = FXCollections.observableArrayList(depDBB.getBufferObservableList());
    ObservableList<String> acceptedCarriers = FXCollections.observableArrayList("Verizon","AT&T","T-Mobile");

    @FXML
    JFXComboBox<DepartmentEntry> departmentChoice;

    @FXML
    JFXComboBox<String> carrierChoice;

    @FXML
    JFXTextField nameText;

    @FXML
    JFXTextField emailText;

    @FXML
    JFXTextField phoneNumber;

    @FXML
    JFXButton submitButton;

    public void btnClick(ActionEvent e) {
        if (!hasValidInput(e)) {
            return;
        }
        ArrayList<EmployeeEntry> emps = new ArrayList<>(empDBB.getBufferObservableList());
        int newID = emps.size() + 1;
        EmployeeEntry tempEmp = new EmployeeEntry(((Integer) newID).toString(),nameText.getText(),departmentChoice.getValue().getId(),emailText.getText(),String.format("%s:%s",phoneNumber.getText(),carrierChoice.getValue()));
        emps.add(tempEmp);
        homeButton(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carrierChoice.setItems(acceptedCarriers);
        departmentChoice.setItems(allDepartments);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid5 = new RequiredFieldValidator("Field is required");
        RegexValidator valid6 = new RegexValidator("Must be a Phone Number");
        valid6.setRegexPattern("\\d{10}");
        addValidators(nameText,valid1);
        addValidators(phoneNumber,valid2);
        addValidators(phoneNumber,valid6);
        addValidators(emailText,valid3);
        addValidators(departmentChoice,valid4);
        addValidators(carrierChoice,valid5);
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        nameText.validate();
        departmentChoice.validate();
        emailText.validate();
        phoneNumber.validate();
        carrierChoice.validate();
        if(!nameText.validate()) {
            //showAlert(Alert.AlertType.ERROR,nameText.getScene().getWindow(),"Form Error!","Please enter a name.");
            nameText.requestFocus();
            return false;
        }
        if(!departmentChoice.validate()) {
            //showAlert(Alert.AlertType.ERROR,nameText.getScene().getWindow(),"Form Error!","Please enter a name.");
            departmentChoice.requestFocus();
            return false;
        }
        if(!emailText.validate()) {
            //showAlert(Alert.AlertType.ERROR,nameText.getScene().getWindow(),"Form Error!","Please enter a name.");
            emailText.requestFocus();
            return false;
        }
        if(!phoneNumber.validate()) {
            //showAlert(Alert.AlertType.ERROR,nameText.getScene().getWindow(),"Form Error!","Please enter a name.");
            phoneNumber.requestFocus();
            return false;
        }
        if(!carrierChoice.validate()) {
            //showAlert(Alert.AlertType.ERROR,nameText.getScene().getWindow(),"Form Error!","Please enter a name.");
            carrierChoice.requestFocus();
            return false;
        }
        return true;
    }
}
