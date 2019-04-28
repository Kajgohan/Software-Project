package bishopfish.controllers;

import bishopfish.db.DBMI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingPageController extends Controller implements Initializable {
    //    @FXML
//    private Button departmentButton;
    @FXML
    private Button mapBuilder;
    @FXML
    private Button DBViewerButton;
    @FXML
    private Button AddEmpsButton;

    @FXML
    AnchorPane adminFxml;
//    @FXML
////    private Button patientGiftsButton;
//    @FXML
//    private Button bookingsButton;
//    @FXML
//    private Button employeeButton;
//    @FXML
//    private Button roomsButton;
//    @FXML
//    private Button nodesButton;
//    @FXML
//    private Button edgesButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void onClick(ActionEvent e) throws IOException {
//        if (e.getSource() == patientGiftsButton) {
//            loadDBViewer(e, DBMI.Emergency.value);
//        } else if (e.getSource() == employeeButton) {
//            loadDBViewer(e, DBMI.Employees.value);
//        } else if (e.getSource() == roomsButton) {
//            loadDBViewer(e, DBMI.RoomBookingRooms.value);// blank
//        } else if (e.getSource() == bookingsButton) {
//            loadDBViewer(e, DBMI.RoomBookingBookings.value);//blank
//        } else if (e.getSource() == DBViewerButton) {
//            loadDBViewer(e, DBMI.ServiceRequest.value);
//        } else if (e.getSource() == departmentButton) {
//            loadDBViewer(e, DBMI.Departments.value);
//        }
        if (e.getSource() == mapBuilder) {
            loadFxml("mapBuilder.fxml");
        } else if (e.getSource() == DBViewerButton) {
            loadFxml("dropDownDBViewer.fxml");
        } else if (e.getSource() == AddEmpsButton) {
            loadFxml("addEmployee.fxml");
        }
//        } else if (e.getSource() == nodesButton) {
//            loadDBViewer(e, DBMI.Nodes.value);
//        }


    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }
}
