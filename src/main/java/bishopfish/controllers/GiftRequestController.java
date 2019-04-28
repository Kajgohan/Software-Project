package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.servicerequest.GiftRequest;
import bishopfish.servicerequest.SecurityRequest;
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

import java.net.URL;
import java.util.ResourceBundle;

public class GiftRequestController extends Controller implements Initializable {

    @FXML
    public JFXButton sendButton;

    @FXML
    public JFXTextField receiverName;

    @FXML
    public JFXTextField fromName;

    //create the combobox from the FXML so that it can be edited and to run functions on
    @FXML
    private JFXComboBox<String> giftOptionsCb;

    //create the fields that will drop down from the combo box
    @FXML
    private ObservableList<String> giftOptions;

    //initialize the combo box
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        giftOptions = DBCustom.getGiftNames();
//        System.out.println(giftOptions);
//        System.out.println(giftOptionsCb);
        giftOptionsCb.setItems(giftOptions);
        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is required");
        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is required");

        addValidators(receiverName,valid1);
        addValidators(fromName,valid2);
        addValidators(giftOptionsCb,valid3);

    }



    @Override
    protected boolean hasValidInput(ActionEvent e) {
        /*if((getPermissions() == null) ||(!getPermissions().contains("2") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, fromName.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else if ((receiverName.getText().equals("")) || (giftOptionsCb.getValue()==null) || (fromName.getText().equals(""))) {
            showAlert(Alert.AlertType.ERROR, fromName.getScene().getWindow(), "Form Error!", "Please fill all fields!");
            return false;
        }*/
        giftOptionsCb.validate();
        receiverName.validate();
        fromName.validate();
        if (!giftOptionsCb.validate()) {
            giftOptionsCb.requestFocus();
            return false;
        }
        if (!receiverName.validate()) {
            receiverName.requestFocus();
            return false;
        }
        if (!fromName.validate()) {
            fromName.requestFocus();
            return false;
        }
        else return true;
    }

    public void onClick(ActionEvent event) {
        if (event.getSource() == sendButton) {
            if(!hasValidInput(event)) return;
            System.out.println("here");
            String desc = String.format("Gift Request\n%s\nFrom %s\nTo %s\n", giftOptionsCb.getValue(), fromName.getText(), receiverName.getText());
            GiftRequest giftRequest = new GiftRequest(new Node("Local"), false, fromName.getText(), desc);
            DBBuffer<ServiceRequestEntry> dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
            dbb.add(giftRequest.getServiceRequestEntry());
            homeButton(event);
        }
    }

}
