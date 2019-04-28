package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.servicerequest.*;
import bishopfish.utils.FeatureTypeEntry;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.RequiredFieldValidator;
import bishopfish.utils.PermissionDecoderEntry;
//import bishopfishapi.Emergency;
////import edu.wpi.cs3733d19.teamF.sanitation.SanitationRequest;
////import edu.wpi.cs3733d19.teamF.sanitation.SanitationService;

import elevator_api.InternalTransportRequestApi;
//import employee.model.JobType;
//import imaging.ImagingRequest;
//import imaging.RequestFacade.MedicalImagingRequest;
//import imaging.ImagingRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ChooseServiceRequestController extends Controller implements Initializable {
    //create the node for the kiosk to use in the other methods later in the class
    ThisNode localNode = new ThisNode();

    //create the combobox from the FXML so that it can be edited and to run functions on
    @FXML
    private JFXComboBox<FeatureTypeEntry> serviceRequestField;

    @FXML
    AnchorPane serviceFxml;

    //create the fields that will drop down from the combo box
//    @FXML
//    private ObservableList<String> serviceRequestFieldList = FXCollections.observableArrayList("Security Request", "IT Request",
//            "Gift Request (James Kajon)", "Interpreter Request (Katharine Conroy)", "Consult Request (Remy Allegro)", "Religious Request (Carl Runci)",
//            "Florist Request (Skylar O'Connell)",  "Pet Therapy Request(Orlando Pinel)","Reading Request (Katie Martin)","Accommodation Request (Jack Hogan)",
//            "External Transport Request (Ann Jicha)");

    //initialize the combo box
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBBuffer.OlFromEntryLambda filter = (srtdbe) -> {
            FeatureTypeEntry ent = ((FeatureTypeEntry) srtdbe);
            if (ent.getFeatureType().equals("SR") && ent.getPermissionType().equals("R")) {
                return ent;
            }
            return null;
        };
        DBBuffer dbbFt = new DBBuffer(DBMI.FeatureType.value);
        ObservableList<FeatureTypeEntry> serviceRequestFieldList = dbbFt.getOlOfel(filter);
        dbbFt.close();
        if (CSI.getInstance().hasPermission("ADMF")) {
            final HashMap<String, String> fidToPid = DBCustom.getPermissionsHM();
            //System.out.println(fidToPid);
            //System.out.println(fidToPid.size());
            ObjectFilter of = (o) -> {
                FeatureTypeEntry ent = (FeatureTypeEntry) o;
                // System.out.println(ent);
                // System.out.println(ent.getId());
                return CSI.getInstance().hasPermission(ent.getId()) ? o : null;

//                if (permissions.contains(fidToPid.get(ent.getId()))) {
//                    return o;
//                } else {
//                    return null;
//                }
            };
            serviceRequestFieldList = (ObservableList<FeatureTypeEntry>) getFilteredOL(serviceRequestFieldList, of);
        }
        Collections.sort(serviceRequestFieldList);
        serviceRequestField.setItems(serviceRequestFieldList);
        RequiredFieldValidator valid = new RequiredFieldValidator("Field is Required");
        addValidators(serviceRequestField,valid);

    }

    @Override
    public void secondInit() {
    }


    @Override
    protected boolean hasValidInput(ActionEvent event) {
        if (!serviceRequestField.validate()) {
            //showAlert(ERROR, ((Node) event.getSource()).getScene().getWindow(), "ComboBox Error", "Please Select A Service Request Type");
            serviceRequestField.requestFocus();
            return false;
        }
        return true;
    }



    public void onSubmitClick(ActionEvent event) throws IOException {
        if (!hasValidInput(event)) {
            return;
        }

        switch (serviceRequestField.getValue().getId()) {
            case "ITRR":
            case "SECR":
                loadFxml("genericServiceRequest.fxml", serviceRequestField.getValue());
                break;
            case "GFTR":
                loadFxml("giftRequest.fxml");
                break;
            case "INTR":
                loadFxml("interpreterRequestPage.fxml");
                break;
            case "RLGR":
                loadFxml("religiousRequest.fxml");
                break;
            case "CNSR":
                loadFxml("consultRequest.fxml");
                break;
            case "PETR":
                loadFxml("petTherapyRequest.fxml");
                break;
            case "FLOR":
                loadFxml("floristRequest.fxml");
                break;
            case "REDR":
                loadFxml("readingRequest.fxml");
                break;

            case "TRNR":
                loadFxml("externalTransport.fxml");
                break;
            case "ACMR":
                loadFxml("accommodationRequest.fxml");
                break;
            case "MIRR":
//                  ImagingRequest a = new ImagingRequest();
//                try {
//                    a.run(50,50, 900, 1300, "bishopfish/curie.css", "destNode", "originNode");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                break;
            case "ITNR":
//                InternalTransportRequestApi itr = new InternalTransportRequestApi();
//
//                itr.insertEmployee(new employee.model.Employee(2, "Meridith Grey", JobType.DOCTOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(3, "Derek Shepard", JobType.DOCTOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(4, "Andrew DeLuca", JobType.DOCTOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(5, "Jo Wilson", JobType.DOCTOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(6, "Jane Sloan", JobType.ADMINISTRATOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(7, "Kat Edison", JobType.AV, false, null));
//                itr.insertEmployee(new employee.model.Employee(8, "Sutton Brady", JobType.JANITOR, false, null));
//                itr.insertEmployee(new employee.model.Employee(9, "Jacqueline Carlyel", JobType.GUEST, false, null));
//                itr.insertEmployee(new employee.model.Employee(10, "Richard Hunter", JobType.INTERPRETER, false, null));
//                try {
//                    itr.run(50,50, 900, 1300, "bishopfish/curie.css", "destNode", "originNode");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                break;




        }
    }

}
