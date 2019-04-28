package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.NodeEntry;
import bishopfish.scheduler.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Deprecated
public class ForcedBookingController extends Controller implements Initializable {
    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//    public JFXButton unBookButton;
//    //Stub nodes (for the workstations)
//
//    Node K = new Node("k");
//    Node L = new Node("l");
//    Node M = new Node("m");
//    Node N = new Node("N");
//    Node O = new Node("O");
//
//    //instantiation of rooms to book
//
//    //Workzones (cannot be reserved but can eventually be marked as occupied)
//    Workstation k = new Workstation("W1", "Workzone 1", K, new ArrayList<Booking>(), false);
//    Workstation l = new Workstation("W2", "Workzone 2", L, new ArrayList<Booking>(), false);
//    Workstation m = new Workstation("W3", "Workzone 3", M, new ArrayList<Booking>(), false);
//    Workstation n = new Workstation("W4", "Workzone 4", N, new ArrayList<Booking>(), false);
//    Workstation o = new Workstation("W5", "Workzone 5", O, new ArrayList<Booking>(), false);
//
//    // RoomEntry Objects
//    RoomEntry workZone1 = k.asEntry();
//    RoomEntry workZone2 = l.asEntry();
//    RoomEntry workZone3 = m.asEntry();
//    RoomEntry workZone4 = n.asEntry();
//    RoomEntry workZone5 = o.asEntry();
//
//
//    @FXML
//    private JFXButton bookButton;
//
//    @FXML
//    private JFXButton seeRooms;
//
//    @FXML
//    private ComboBox<Room> roomList;
//
//    @FXML
//    private ObservableList<Room> rooms = FXCollections.observableArrayList(k, l, m, n, o);
//
////    DBBuffer<NodeEntry> workstationEdit;
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        roomList.setItems(rooms);
//
//    }
//
//    //home button
//    public void onHomeClick(ActionEvent event) throws IOException {
//        loadFxml("homePage.fxml");
//    }
//
//    //goes to bishopfish.map with highlighted rooms
//    public void onShowRoomsClick(ActionEvent event) throws IOException{
//        loadFxml("roomView.fxml");
//    }
//
//    //when book is pressed
//    public void bookBtnCode(ActionEvent e) {
//
//
//        //variables to store information from the buttons
//        Room selectedRoom = null;
//
//
//        //check if everything necessary has been selected
//        if (!hasValidInput(e)) {
//            return;
//        }
//
//
//        //also check to see if everything filled out
//        if (e.getSource() == bookButton) {
//
//            //store the room they wish to book
//            selectedRoom = roomList.getValue();
//
//        }
//
//        occupyRoom(selectedRoom);
//
//
//    }
//
//    //when unbook is pressed
//    public void unBookBtnCode(ActionEvent e) {
//
//        //variables to store information from the buttons
//        Room selectedRoom = null;
//
//
//        //check if everything necessary has been selected
//        if (!hasValidInput(e)) {
//            return;
//        }
//
//
//        //also check to see if everything filled out
//        if (e.getSource() == unBookButton) {
//
//            //store the room they wish to book
//            selectedRoom = roomList.getValue();
//
//        }
//
//        unOccupyRoom(selectedRoom);
//
//    }
//
//
//    /**
//     * @param stationToOccupy workstation to mark as occupied
//     */
//    private void occupyRoom(Room stationToOccupy) {
//        ArrayList<String> stationUpdate = new ArrayList<>();
//
//        DBBuffer<WorkstationEntry> dbb = new DBBuffer<>(DBMI.Workstations.value);
//        HashMap<String, WorkstationEntry> workstationHM = dbb.getBufferHashMap();
//
//        WorkstationEntry tmp = workstationHM.get(stationToOccupy.getId());
//
//        stationUpdate.add(null);
//        stationUpdate.add(null);
//        stationUpdate.add(null);
//        //change the isOccupied value to true
//        stationUpdate.add("true");
//
//        dbb.update(stationToOccupy.getId(), stationUpdate);
//
//
//        showAlert(Alert.AlertType.CONFIRMATION, bookButton.getScene().getWindow(),
//                "Complete", "The workstation has been marked occupied!");
//
//    }
//
//    private void unOccupyRoom(Room stationToOccupy) {
//        ArrayList<String> stationUpdate = new ArrayList<>();
//
//        DBBuffer<WorkstationEntry> dbb = new DBBuffer<>(DBMI.Workstations.value);
//        HashMap<String, WorkstationEntry> workstationHM = dbb.getBufferHashMap();
//
//        //WorkstationEntry tmp = workstationHM.get(stationToOccupy.getId());
//
//        stationUpdate.add(null);
//        stationUpdate.add(null);
//        stationUpdate.add(null);
//        //change the isOccupied value to true
//        stationUpdate.add("false");
//
//        dbb.update(stationToOccupy.getId(), stationUpdate);
//
//        showAlert(Alert.AlertType.CONFIRMATION, bookButton.getScene().getWindow(),
//                "Complete", "The workstation has been marked unoccupied!");
//
//    }
//
//
//    @Override
//    protected boolean hasValidInput(ActionEvent e) {
//
//        //make sure a room is selected
//        if (roomList.getValue() == null) {
//            showAlert(Alert.AlertType.ERROR, bookButton.getScene().getWindow(),
//                    "Form Error!", "Please enter a room");
//            return false;
//        }
//
//        return true;
//    }
//

}
