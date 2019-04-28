package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.map.Node;
import bishopfish.scheduler.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.twilio.rest.preview.bulkExports.export.Day;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Skin;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.*;

public class AgendaController extends Controller implements Initializable {
//    //Stub nodes (for the 14 rooms)
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
//
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
//    ConferenceRoom all = new ConferenceRoom("all", "All", 0, J, new ArrayList<Booking>());
//
//
//    //Auditorium (treated as a classroom)
//    ConferenceRoom j = new ConferenceRoom("A1","Mission Hill Auditorium",19, J, new ArrayList<Booking>());
//
//
//    // RoomEntry Objects
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
//    RoomEntry allRooms = all.asEntry();


    //boolean keeping track of skin format. true if the Agenda is in a week skin, false if it is in a day one
    boolean skinTrackingBoolean = true;

    public ArrayList<AppointmentImplLocal> sceduledAppointments = new ArrayList<AppointmentImplLocal>();
    public ArrayList<Booking> currentBookings = new ArrayList<>();


    @FXML
    public Agenda agendaBookings;

    @FXML
    public JFXButton roomList;

    @FXML
    public JFXButton submitBtn;

    @FXML
    public ComboBox<Node> sceduledRooms;

    @FXML
    public ComboBox<String> skinSelect;

    @FXML
    public Button nextButton;

    @FXML
    public Button previousButton;

    //@FXML
    //private ObservableList<Room> roomstoView = FXCollections.observableArrayList(a,b,c,d,e,f,g,h,i,j,all);

    @FXML
    private ObservableList<String> skinOptions = FXCollections.observableArrayList("Week View", "Day View");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Node> roomOptionItems = DBCustom.getRooms();
        roomOptionItems.add(new Node("ALL", 0, 0, "ALL", "ALL", "ALL", "ALL", "All"));

        sceduledRooms.setItems(roomOptionItems);


        skinSelect.setItems(skinOptions);


        agendaBookings.setAllowDragging(false);
        agendaBookings.setAllowResize(false);



        //in the database, pull all bookings
        DBBuffer<BookingEntry> dbb = new DBBuffer<>(DBMI.RoomBookingBookings.value);
        HashMap<String, BookingEntry> booingHM = dbb.getBufferHashMap();

        BookingEntry tmp;

        //go through everything and store bookings in the arraylist
        for (Map.Entry<String, BookingEntry> entry : booingHM.entrySet()) {
            tmp = entry.getValue();
            currentBookings.add(new Booking( tmp.getStartDate(), tmp.getEndDate(), tmp.getReserver(), tmp.getRoomID() ));
            sceduledAppointments.add(bookingToAppointment(new Booking(tmp.getStartDate(), tmp.getEndDate(), tmp.getReserver(), tmp.getRoomID())));
        }

        //add all the appointments to the schedule
        agendaBookings.appointments().addAll(sceduledAppointments);


        //need to implement newAppointmentCallbackProperty for scrolling to work according to JFXtras (KEEP THIS HERE JUST IN CASE)
/*        agendaBookings.newAppointmentCallbackProperty().set( (localDateTimeRange) -> {
            return new AppointmentImplLocal()
                    .withStartLocalDateTime(localDateTimeRange.getStartLocalDateTime())
                    .withEndLocalDateTime(localDateTimeRange.getEndLocalDateTime())
                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")); // it is better to have a map of appointment groups to get from
        });*/


    }



    public AppointmentImplLocal bookingToAppointment(Booking currentBooking){
        Agenda.AppointmentImplLocal appointToAdd = new Agenda.AppointmentImplLocal();

        //start time
        //convert from unix time to local date time in order to store it
        appointToAdd.setStartLocalDateTime(millsToLocalDateTime(currentBooking.getStartTime()));

        //end time
        //convert from unix time to local date time in order to store it
        appointToAdd.setEndLocalDateTime(millsToLocalDateTime(currentBooking.getEndTime()));

        //group
        String roomForGroup = currentBooking.getRoom();
        switch (roomForGroup){
            case "CLASS00001":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
                break;
            case "CLASS00002":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group2"));
                break;
            case "CLASS00003":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group3"));
                break;
            case "CLASS00004":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group4"));
                break;
            case "CLASS00005":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group5"));
                break;
            case "CLASS00006":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group6"));
                break;
            case "CLASS00007":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group7"));
                break;
            case "CLASS00008":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group8"));
                break;
            case "FCONF00001":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group9"));
                break;
            case "AUDIT00001":
                appointToAdd.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group10"));
                break;
        }

        //description
        appointToAdd.setDescription(currentBooking.getReserver());


        return appointToAdd;

    }

    public void onSubmitClick(ActionEvent e){
        //check if a room was selected
        if(!hasValidInput(e)){
            return;
        }

        //store the selected room
        Node selectedRoom = sceduledRooms.getValue();



        //if the option selected is all
        if (selectedRoom.getName().equals("ALL")) {
            skinPicker();
            for (int i = 0; i < currentBookings.size(); i++){
                agendaBookings.appointments().addAll( bookingToAppointment( currentBookings.get(i) ) );
            }
            return;
        }

        //clear the current schedule
        agendaBookings.appointments().clear();

        //interaction with appointment list: get a list of exclusively appointments for selected room
        for (Booking currentBooking : currentBookings) {

            if (currentBooking.getRoom().equals(roomNameToID(selectedRoom.getLongName()))) {

                agendaBookings.appointments().addAll(bookingToAppointment(currentBooking));

            }

        }

        //change the skin of the agenda according to selected time
           skinPicker();

        //the agenda will now display only the relevant appointments

    }

    public void skinPicker(){

        Skin daySkin = new AgendaDaySkin(agendaBookings);
        Skin weekSkin = new AgendaWeekSkin(agendaBookings);

        if(skinSelect.getValue().equals("Week View")){
            agendaBookings.setSkin(weekSkin);
            skinTrackingBoolean = true;

        } else if(skinSelect.getValue().equals("Day View")){
            //agendaBookings.setSkin(daySkin);
            agendaBookings.setSkin(new AgendaDaySkin(agendaBookings));
            skinTrackingBoolean = false;
            System.out.println("made it to the day view pick");
        }

    }

//    @FXML @Override
//    public void backButton(ActionEvent event) throws IOException{
//            loadNewScene(event,"roomView.fxml");
//    }



//obtains the group for a room, should only be called on names of members of the observable list, otherwise will return null
    public String roomNameToID(String selectedRoom){

        String correctGroup = null;

        switch (selectedRoom){
            case "Classroom 1":
                correctGroup = "CLASS00001";
                break;
            case "Classroom 2":
                correctGroup = "CLASS00002";
                break;
            case "Classroom 3":
                correctGroup = "CLASS00003";
                break;
            case "Classroom 4":
                correctGroup = "CLASS00004";
                break;
            case "Classroom 5":
                correctGroup = "CLASS00005";
                break;
            case "Classroom 6":
                correctGroup = "CLASS00006";
                break;
            case "Classroom 7":
                correctGroup = "CLASS00007";
                break;
            case "Classroom 8":
                correctGroup = "CLASS00008";
                break;
            case "Classroom 9":
                correctGroup = "FCONF00001";
                break;
            case "Mission Hill Auditorium":
                correctGroup = "AUDIT00001";
                break;
        }

        return correctGroup;

    }



    protected boolean hasValidInput(ActionEvent event) {

        //make sure a room is selected
        if (sceduledRooms.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, submitBtn.getScene().getWindow(),
                    "Form Error!", "Please select a room.");
            return false;
        }

        return true;
    }


    //converts time stored into localDateTime
    public static LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis * 1000 );
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

    public void onRoomsListClick(ActionEvent event) throws IOException {
        loadFxml("roomListView.fxml");
    }

    public void onBackClick(ActionEvent event) throws IOException{
        if(skinTrackingBoolean){

            LocalDateTime newDisplayedLocalDateTime = agendaBookings.getDisplayedLocalDateTime().minus(Period.ofWeeks(1));
            agendaBookings.setDisplayedLocalDateTime(newDisplayedLocalDateTime);

        }else if(!skinTrackingBoolean){

            LocalDateTime newDisplayedLocalDateTime = agendaBookings.getDisplayedLocalDateTime().minus(Period.ofDays(1));
            agendaBookings.setDisplayedLocalDateTime(newDisplayedLocalDateTime);

        }

    }

    public void onForwardClick(ActionEvent event) throws IOException{
        if(skinTrackingBoolean){

            LocalDateTime newDisplayedLocalDateTime = agendaBookings.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
            agendaBookings.setDisplayedLocalDateTime(newDisplayedLocalDateTime);

        }else if(!skinTrackingBoolean){

            LocalDateTime newDisplayedLocalDateTime = agendaBookings.getDisplayedLocalDateTime().plus(Period.ofDays(1));
            agendaBookings.setDisplayedLocalDateTime(newDisplayedLocalDateTime);

        }

    }


}
