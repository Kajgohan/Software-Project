package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.scheduler.Booking;
import bishopfish.scheduler.BookingEntry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RoomListViewController extends Controller implements Initializable {

    public JFXButton backBtn;

    public JFXListView roomList;

    public JFXButton viewBookingsBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //in the database, check the bookings
        DBBuffer<BookingEntry> dbb = new DBBuffer<>(DBMI.RoomBookingBookings.value);
        HashMap<String, BookingEntry> booingHM = dbb.getBufferHashMap();
        ArrayList<Booking>  bookingsInCurrentTime = new ArrayList<Booking>();

        BookingEntry tmp;
        ArrayList<String> bookedRooms = new ArrayList<String>();

        //go through everything and store rooms of bookings
        for (Map.Entry<String, BookingEntry> entry : booingHM.entrySet()) {
            tmp = entry.getValue();

            //add the room to a list of scheduled rooms (if it is a duplicate do not add it)
            if ( ! (bookedRooms.contains(tmp.getRoomID())) ) {
                bookedRooms.add(tmp.getRoomID());
            }

        }

        //add all of the rooms to the list view
        for (int i = 0; i < bookedRooms.size(); i++){
            //roomList.getItems().add(bookedRooms.get(i));
            switch (bookedRooms.get(i)){

                case "C1":
                    roomList.getItems().add("Classroom 1");
                    break;
                case "C2":
                    roomList.getItems().add("Classroom 2");
                    break;
                case "C3":
                    roomList.getItems().add("Classroom 3");
                    break;
                case "C4":
                    roomList.getItems().add("Classroom 4");
                    break;
                case "C5":
                    roomList.getItems().add("Classroom 5");
                    break;
                case "C6":
                    roomList.getItems().add("Classroom 6");
                    break;
                case "C7":
                    roomList.getItems().add("Classroom 7");
                    break;
                case "C8":
                    roomList.getItems().add("Classroom 8");
                    break;
                case "A1":
                    roomList.getItems().add("Mission Hill Auditorium");
                    break;
            }


        }


    }

    protected boolean hasValidInput(ActionEvent event){
        return true;
    }

    // NOT ALLOWED. Ann are you using this?
    // TODO fix -jimmy
    @Deprecated
    public void onBookingBtnClick(ActionEvent event) throws IOException {
        loadDBViewer(event, DBMI.RoomBookingBookings.value);

    }
}
