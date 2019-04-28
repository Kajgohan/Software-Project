package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.scheduler.*;
import com.jfoenix.controls.*;
//import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


public class BookingController extends Controller implements Initializable {

    //Stub nodes (for the 14 rooms)
//    Node A = new Node("a");
//    Node B = new Node("b");
//    Node C = new Node("c");
//    Node D = new Node("d");
//    Node E  = new Node("e");
//    Node F = new Node("f");
//    Node G = new Node("g");
//    Node H = new Node("h");
//    Node I = new Node("i");
//    Node J = new Node("j");
//    Node K = new Node("k");
//    Node L = new Node("l");
//    Node M = new Node("m");
//    Node N = new Node("N");
//    Node O = new Node("O");
//
//    //instantiation of rooms to book
//    //Classrooms
//    ConferenceRoom a = new ConferenceRoom("C1","Classroom 1",22, A, new ArrayList<Booking>());
//    ConferenceRoom b = new ConferenceRoom("C2","Classroom 2",17, B, new ArrayList<Booking>());
//    ConferenceRoom c = new ConferenceRoom("C3","Classroom 3",17, C, new ArrayList<Booking>());
//    ConferenceRoom d = new ConferenceRoom("C4","Classroom 4",19, D, new ArrayList<Booking>());
//    ConferenceRoom e = new ConferenceRoom("C5","Classroom 5",19, E, new ArrayList<Booking>());
//    ConferenceRoom f = new ConferenceRoom("C6","Classroom 6",19, F, new ArrayList<Booking>());
//    ConferenceRoom g = new ConferenceRoom("C7","Classroom 7",19, G, new ArrayList<Booking>());
//    ConferenceRoom h = new ConferenceRoom("C8","Classroom 8",19, H, new ArrayList<Booking>());
//    ConferenceRoom i = new ConferenceRoom("C9","Classroom 9",19, I, new ArrayList<Booking>());

//
//    //Auditorium (treated as a classroom)
//    ConferenceRoom j = new ConferenceRoom("A1","Mission Hill Auditorium",19, J, new ArrayList<Booking>());
//
//    //Workzones (cannot be reserved but can eventually be marked as occupied)
//    Workstation k = new Workstation("W1","Workzone 1",K, new ArrayList<Booking>(),false);
//    Workstation l = new Workstation("W2","Workzone 2",L, new ArrayList<Booking>(), false);
//    Workstation m = new Workstation("W3","Workzone 3",M, new ArrayList<Booking>(), false);
//    Workstation n = new Workstation("W4","Workzone 4",N, new ArrayList<Booking>(), false);
//    Workstation o = new Workstation("W5","Workzone 5",O, new ArrayList<Booking>(), false);

    // RoomEntry Objects
//    RoomEntry conferenceRoom1 = a.asEntry();
//    RoomEntry conferenceRoom2 = b.asEntry();
//    RoomEntry conferenceRoom3 = c.asEntry();
//    RoomEntry conferenceRoom4 = d.asEntry();
//    RoomEntry conferenceRoom5 = e.asEntry();
//    RoomEntry conferenceRoom6 = f.asEntry();
//    RoomEntry conferenceRoom7 = g.asEntry();
//    RoomEntry conferenceRoom8 = h.asEntry();
//    RoomEntry conferenceRoom9 = i.asEntry();
//
//    RoomEntry auditoriumRoom = j.asEntry();
//
//    RoomEntry workZone1 = k.asEntry();
//    RoomEntry workZone2 = l.asEntry();
//    RoomEntry workZone3 = m.asEntry();
//    RoomEntry workZone4 = n.asEntry();
//    RoomEntry workZone5 = o.asEntry();

    private DBBuffer<BookingEntry> bookingTable;

    @FXML
    private JFXDatePicker dateOptions;

    @FXML
    private JFXButton bookButton;

    @FXML
    private JFXButton forceOccupancyButton;

    @FXML
    private JFXButton seeRooms;

    @FXML
    private JFXTimePicker startText;

    @FXML
    private JFXTimePicker endText;

    @FXML
    private JFXTextField reserverText;

    @FXML
    private JFXComboBox<Node> roomList;

    //FOR MULTI-DAY
    @FXML
    private JFXDatePicker dayTwoPicker;

//    @FXML
//    private ObservableList<Room> rooms = FXCollections.observableArrayList(a,b,c,d,e,f,g,h,i,j);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        roomList.setItems(DBCustom.getRooms());
        //System.out.println("Initalize ran");

        try {
            bookingTable = new DBBuffer<>(DBMI.RoomBookingBookings.value);
            System.out.println(bookingTable);
//            roomTable = new DBBuffer<>(DBMI.RoomBookingRooms.value);
//            System.out.println(roomTable);
        }catch (Exception e){
            e.printStackTrace();
        }

//        RequiredFieldValidator valid1 = new RequiredFieldValidator("Field is Required");
//        RequiredFieldValidator valid2 = new RequiredFieldValidator("Field is Required");
//        RequiredFieldValidator valid3 = new RequiredFieldValidator("Field is Required");
//        RequiredFieldValidator valid4 = new RequiredFieldValidator("Field is Required");
//        RequiredFieldValidator valid5 = new RequiredFieldValidator("Field is Required");
//        RequiredFieldValidator valid6 = new RequiredFieldValidator("Field is Required");
//
//
//        addValidators(dateOptions,valid1);
//        addValidators(reserverText,valid2);
//        addValidators(roomList,valid3);
//        addValidators(startText,valid4);
//        addValidators(endText,valid5);
//        addValidators(dayTwoPicker,valid6);
    }


//    @FXML @Override
//    public void backButton(ActionEvent event) throws IOException{
//        loadFxml(getSubP,"roomView.fxml");
//    }

//    //home button
//    public void onHomeClick(ActionEvent event) throws IOException {
//        loadFxml(getSubP, "homePage.fxml");
//    }

    //goes to bishopfish.map with highlighted rooms
    public void onShowRoomsClick(ActionEvent event) throws IOException{
        loadFxml("roomView.fxml");
    }

    public void onForcedButton(ActionEvent event) throws IOException{
        loadFxml("forcedBookingPage.fxml");
    }
    //when book is pressed
    public void btnCode(ActionEvent e) {
       // System.out.println(startText.getValue());

        //variables to store information from the buttons
        Node selectedRoom;
        String startTime = "";
        String endTime = "";
        String reserverName = "";

        LocalDate dateFromButton;
        LocalDate secondDateFromButton;
        GregorianCalendar dateGregorian1; //stores date from the button as a GregorianCalendar object
        GregorianCalendar dateGregorian2;
        GregorianCalendar secondDateGregorian1;
        GregorianCalendar secondDateGregorian2;

        if(!hasValidInput(e)){
            return;
        }

        //splits in order to check if the times are before each other
        String split1 = startText.getValue().toString().substring(0, 2);
        String split2 = startText.getValue().toString().substring(3, 5);
        String split3 = endText.getValue().toString().substring(0, 2);
        String split4 = endText.getValue().toString().substring(3, 5);

        int startHour, startMin, endHour,endMin;
        startHour = Integer.parseInt(split1);
        startMin = Integer.parseInt(split2);
        endHour = Integer.parseInt(split3);
        endMin = Integer.parseInt(split4);

        //also check to see if everything filled out
        if (e.getSource() == bookButton) {

            //date value stored as a GregorianCalendar value
            dateFromButton = dateOptions.getValue();
            ZonedDateTime zonedDateTime = dateFromButton.atStartOfDay(ZoneId.systemDefault());
            dateGregorian1 = GregorianCalendar.from(zonedDateTime);
            dateGregorian2 = GregorianCalendar.from(zonedDateTime);


            //optional end date value stored as a GregorianCalendar value
            secondDateFromButton = dayTwoPicker.getValue();
            ZonedDateTime secondZonedDateTime = secondDateFromButton.atStartOfDay(ZoneId.systemDefault());
            secondDateGregorian1 = GregorianCalendar.from(secondZonedDateTime);
            secondDateGregorian2 = GregorianCalendar.from(secondZonedDateTime);

            //name stored
            reserverName = reserverText.getText();

            //store the room they wish to book
            selectedRoom = roomList.getValue();


            //startTime stored
            startTime = startText.getValue().toString();

            //endTime stored
            endTime = endText.getValue().toString();

            GregorianCalendar startDate = dateGregorian1;
            GregorianCalendar endDate;
//            if(dayTwoPicker.getValue() != null){
//                //if a second day is specified, that is the end date
               endDate = secondDateGregorian2;
//
//            } else {
//                //if a second day is not specified, then the initial day is the end date
//                 endDate = dateGregorian2;
//            }

            startDate.add((GregorianCalendar.HOUR), startHour);
            startDate.add((GregorianCalendar.MINUTE), startMin);
            long unixTimeStart = startDate.getTimeInMillis() / 1000;
            startDate.setTimeInMillis(unixTimeStart * 1000);

            endDate.add((GregorianCalendar.HOUR), endHour);
            endDate.add((GregorianCalendar.MINUTE),endMin);
            long unixTimeEnd = endDate.getTimeInMillis() / 1000;
            endDate.setTimeInMillis(unixTimeEnd * 1000);

            //this is an input check which ensures the times occur in chronological order, it is here because it is done in unix time after the values are stored
            if(endDate.before(startDate)||unixTimeEnd==unixTimeStart){
                showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                        "Form Error!", "Please make sure your end time is after your start time");
                return;
            }

            //make a booking object
            Booking potentialBooking = new Booking(((Long)unixTimeStart).toString(), ((Long)unixTimeEnd).toString(), reserverName, selectedRoom.getName());

            //call isBooked
            makeBooking(potentialBooking);

        }

    }


    /**
     *
     * @param potentialBooking potential booking
     *
     */
    private void makeBooking(Booking potentialBooking){


        DBBuffer<BookingEntry> dbb = new DBBuffer<>(DBMI.RoomBookingBookings.value);
        HashMap<String, BookingEntry> booingHM = dbb.getBufferHashMap();

        //an arraylist of other bookings (not the booking currently being made)
        ArrayList<Booking> otherBookings = new ArrayList<Booking>();

        BookingEntry tmp;
        for (Map.Entry<String, BookingEntry> entry : booingHM.entrySet()) {
            tmp = entry.getValue();
            otherBookings.add(new Booking(tmp.getStartDate(), tmp.getEndDate(), tmp.getReserver(), tmp.getRoomID()));
        }


            //search for existing bookings with the same room as the potential booking
        for(int i = 0; i < otherBookings.size(); i++){
            //if the other booking has the same room as the potential booing, store it as a sameDateBooking
            if (otherBookings.get(i).getRoom().equals(potentialBooking.getRoom())) {
                if(otherBookings.get(i).doesConflict(potentialBooking)) {
                    //the booking cannot be made
                    showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                            "Form Error!", "Your booking conflicts with another booking. Please select " +
                                    "another room or a different time.");
                    return;
                }
            }
            //no conflicts, push new booing to the database
        }

        bookingTable.addR(potentialBooking.asEntry());

        showAlert(Alert.AlertType.CONFIRMATION, reserverText.getScene().getWindow(),
                "Complete", "Your room has been booked!");

    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        //setPermissions("_+");
        //select the date
        /*if((getPermissions() == null) ||(!getPermissions().contains("1") && !getPermissions().contains("+"))){
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(), "Permissions Error!", "You do not have permission to make this request.");
            return false;
        }

        else*/
        dateOptions.validate();
        reserverText.validate();
        startText.validate();
        endText.validate();
        roomList.validate();
        dayTwoPicker.validate();
        /*
        if (dateOptions.getValue() == null) {
            //System.out.println("start of IF");
            System.out.println(dateOptions.getValue());
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                    "Form Error!", "Please enter the date");
            return false;
        }

        //select the reserver
        if (reserverText.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                    "Form Error!", "Please enter your name");
            return false;
        }

        //select the startTime
        if (startText.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                    "Form Error!", "Please enter a start time");
            return false;
        }

        //select the endTime
        if (endText.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                    "Form Error!", "Please enter an end time");
            return false;
        }

        //make sure a room is selected
        if (roomList.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, reserverText.getScene().getWindow(),
                    "Form Error!", "Please enter a room");
            return false;
        }
        */
        if (!dateOptions.validate()) {
            dateOptions.requestFocus();
            return false;
        }
        if (!dayTwoPicker.validate()) {
            dayTwoPicker.requestFocus();
            return false;
        }
        if (!reserverText.validate()) {
            reserverText.requestFocus();
            return false;
        }
        if (!roomList.validate()) {
            roomList.requestFocus();
            return false;
        }
        if (!startText.validate()) {
            startText.requestFocus();
            return false;
        }
        if (!endText.validate()) {
            endText.requestFocus();
            return false;
        }
        //checks to ensure startTime and Endtime occur chronologically and that there are no conflicting bookings occur in other functions
        else return true;
    }

    public void dateChoice( ActionEvent e) {
        dayTwoPicker.setValue(dateOptions.getValue());
        dayTwoPicker.setEditable(true);
    }

}


