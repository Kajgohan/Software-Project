package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.employees.Department;
import bishopfish.employees.DepartmentEntry;
import bishopfish.employees.Employee;
import bishopfish.employees.EmployeeEntry;
import bishopfish.map.Node;
import bishopfish.servicerequest.ConsultRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
//import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.javafx.collections.ObservableSequentialListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ConsultRequestController extends Controller implements Initializable {

    private DBBuffer<DepartmentEntry> depDBB = new DBBuffer<>(DBMI.Departments.value);
    private DBBuffer<EmployeeEntry> empDBB = new DBBuffer<>(DBMI.Employees.value);
    private ObservableList<DepartmentEntry> allSpecialties = FXCollections.observableArrayList();
    private HashMap<DepartmentEntry, ObservableList<EmployeeEntry>> filteredDoctors = new HashMap<>();
    private DBBuffer<ServiceRequestEntry> dbb;

    @FXML
    private JFXTextField requesterField;

    @FXML
    private JFXComboBox<DepartmentEntry> specialtyChoice;

    @FXML
    private JFXComboBox<EmployeeEntry> doctorChoice;

    @FXML
    private JFXTextArea detailsArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Filter only medical departments
        for (DepartmentEntry d : depDBB.getBufferObservableList()) {
            if (d.getType().equals("Medical")) {
                allSpecialties.add(d);
                filteredDoctors.put(d, FXCollections.emptyObservableList());
            }
        }

        for (EmployeeEntry e : empDBB.getBufferObservableList()) {
            for (DepartmentEntry d : allSpecialties) {
                if (e.getDepartmentID().equals(d.getId())) {
                    ObservableList<EmployeeEntry> temp = FXCollections.observableArrayList(filteredDoctors.get(d));
                    temp.add(e);
                    filteredDoctors.put(d,temp);
                }
            }
        }

        try {
            specialtyChoice.setItems(allSpecialties);
            doctorChoice.setPromptText("Choose a Specialty First");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
//        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
//        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");
//        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is required");
//        addValidators(requesterField,valid1);
//        addValidators(specialtyChoice,valid2);
//        addValidators(doctorChoice,valid3);
//        addValidators(detailsArea,valid4);

    }

    public void btnClick(ActionEvent e) {
        if (!hasValidInput(e)) {
            return;
        }

        dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
        String message = String.format("%s has requested a consult from %s, with the message: %s",requesterField.getText(),doctorChoice.getValue().toString(),detailsArea.getText());
        ConsultRequest req = new ConsultRequest(new Node("Local"),false,requesterField.getText(),message,doctorChoice.getValue());
        dbb.add(req.getServiceRequestEntry());
        homeButton(e);

    }

    public void speciaialtySelect(ActionEvent e) {
        DepartmentEntry selected = specialtyChoice.getValue();
        doctorChoice.setItems(filteredDoctors.get(selected));
        doctorChoice.setPromptText("Choose a Doctor");
    }


    public void onBackClick(ActionEvent event) throws IOException {
        loadFxml("chooseServiceRequest.fxml");
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("4") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, requesterField.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
        requesterField.validate();
        specialtyChoice.validate();
        doctorChoice.validate();
        detailsArea.validate();
        if (!requesterField.validate()) {
            //showAlert(Alert.AlertType.ERROR,requesterField.getScene().getWindow(),"Form Error!","Please enter your name.");
            requesterField.requestFocus();
            return false;
        }
        if (!specialtyChoice.validate()) {
            specialtyChoice.resetValidation();
            return false;
        }
        if (!doctorChoice.validate()) {
            //showAlert(Alert.AlertType.ERROR,requesterField.getScene().getWindow(),"Form Error!","Please choose a doctor.");
            doctorChoice.requestFocus();
            return false;
        }
        if (!detailsArea.validate()) {
            //showAlert(Alert.AlertType.ERROR,requesterField.getScene().getWindow(),"Form Error!","Please fill in details.");
            detailsArea.requestFocus();
            return false;
        }
        return true;
    }
}
