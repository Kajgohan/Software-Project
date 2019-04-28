package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.scheduler.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class RoomViewController extends Controller implements Initializable {

    Polygon workzoneOne = new Polygon();
    Polygon workzoneTwo = new Polygon();
    Polygon workzoneThree = new Polygon();
    Polygon workzoneFour = new Polygon();
    Polygon workzoneFive = new Polygon();


    Polygon workzoneOneOne = new Polygon();
    Polygon workzoneOneTwo = new Polygon();
    Polygon workzoneOneThree = new Polygon();
    Polygon workzoneOneFour = new Polygon();
    Polygon workzoneTwoOne = new Polygon();
    Polygon workzoneTwoTwo = new Polygon();
    Polygon workzoneThreeOne = new Polygon();
    Polygon workzoneThreeTwo = new Polygon();
    Polygon workzoneThreeThree = new Polygon();
    Polygon workzoneThreeFour = new Polygon();
    Polygon workzoneThreeFive = new Polygon();
    Polygon workzoneThreeSix = new Polygon();
    Polygon workzoneThreeSeven = new Polygon();
    Polygon workzoneFourOne = new Polygon();
    Polygon workzoneFourTwo = new Polygon();
    Polygon workzoneFiveOne = new Polygon();
    Polygon workzoneFiveTwo = new Polygon();
    Polygon workzoneFiveThree = new Polygon();
    Polygon workzoneFiveFour = new Polygon();
    Polygon workzoneFiveFive = new Polygon();
    Polygon workzoneFiveSix = new Polygon();
    Polygon workzoneFiveSeven = new Polygon();


    Color sea = Color.web("0xfe010c", 0.5);
    Color emptyWorkzone = Color.web("0x09F911", 0.5);


    public Group polygonGroup;

    public ImageView polygonView;

    public AnchorPane polygonAnchor;

    private static final Logger LOGGER = Logger.getLogger(RoomViewController.class.getName());


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        this.setCurFxml("rooomView.fxml");
//        this.setBackFxmlStack(new Stack<String>() {{ push("welcomeScreen.fxml"); }});


        //create the zones and rooms as polygons
        //workzone polygons


        //bookable polygons
        Polygon classroomOne = new Polygon();
        Polygon classroomTwo = new Polygon();
        Polygon classroomThree = new Polygon();
        Polygon classroomFour = new Polygon();
        Polygon classroomFive = new Polygon();
        Polygon classroomSix = new Polygon();
        Polygon classroomSeven = new Polygon();
        Polygon classroomEight = new Polygon();
        Polygon classroomNine = new Polygon();
        Polygon classroomTen = new Polygon();
        Polygon auditorium = new Polygon();

        //assign boundaries to the polygons
        classroomOne.getPoints().addAll(new Double[]{
                //coordinate 1
                869.8,624.0,
                //coordinate 2
                1038.2,624.0,
                //coordinate 3
                1038.2,722.8,
                //coordinate 4
                869.8,722.8,


        });

        classroomTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                1105.0,434.3,
                //coordinate 2
                1186.0,404.7,
                //coordinate 3
                1238.8,551.0,
                //coordinate 4
                1158.6,580.3,

        });


        classroomThree.getPoints().addAll(new Double[]{
                //coordinate 1
                1048.0,276.3,
                //coordinate 2
                1150.6,245.5,
                //coordinate 3
                1204.0,396.3,
                //coordinate 4
                1105.0,434.3,

        });

        classroomFour.getPoints().addAll(new Double[]{
                //coordinate 1
                942.0,74.5,
                //coordinate 2
                1014.9,74.5,
                //coordinate 3
                1063.6,205.6,
                //coordinate 4
                1026.7,205.6,
                //coordinate 5
                1026.7,211.7,
                //coordinate 6
                995.2,211.7,
                //coordinate 7
                995.2,205.6,
                //coordinate 8
                942.0,205.6,

        });

        classroomFive.getPoints().addAll(new Double[]{
                //coordinate 1
                871.7,287.3,
                //coordinate 2
                963.7,287.3,
                //coordinate 3
                963.7,274.4,
                //coordinate 4
                993.7,274.4,
                //coordinate 5
                993.7,421.0,
                //coordinate 6
                871.7,421.0,

        });

        classroomSix.getPoints().addAll(new Double[]{
                //coordinate 1
                818.5,74.9,
                //coordinate 2
                941.6,74.9,
                //coordinate 3
                941.6,210.1,
                //coordinate 4
                818.5,210.1,

        });

        classroomSeven.getPoints().addAll(new Double[]{
                //coordinate 1
                781.7,289.2,
                //coordinate 2
                838.3,289.2,
                //coordinate 3
                838.3,270.6,
                //coordinate 4
                863.7,270.6,
                //coordinate 5
                863.7,289.2,
                //coordinate 6
                871.3,289.2,
                //coordinate 7
                871.3,419.9,
                //coordinate 8
                781.7,419.9,


        });

        classroomEight.getPoints().addAll(new Double[]{
                //coordinate 1
                686.3,74.5,
                //coordinate 2
                818.6,74.5,
                //coordinate 3
                818.6,209.8,
                //coordinate 4
                686.3,209.8,

        });

    //this is a classroom without a number, it cannot be booked
        classroomTen.getPoints().addAll(new Double[]{
                //coordinate 1
                695.8,273.2,
                //coordinate 2
                727.7,273.2,
                //coordinate 3
                727.7,289.2,
                //coordinate 4
                780.9,289.2,
                //coordinate 5
                780.9,360.2,
                //coordinate 6
                695.8,360.2,

        });

        //this is actually the Mission Hill Conference Room
        classroomNine.getPoints().addAll(new Double[]{
                //coordinate 1
                869.8,813.9,
                //coordinate 2
                869.8,721.6,
                //coordinate 3
                1037.4,721.6,
                //coordinate 4
                1037.4,813.9,

        });


        workzoneOne.getPoints().addAll(new Double[]{
                //coordinate 1
                354.0,339.6,
                //coordinate 2
                436.0,339.6,
                //coordinate 3
                436.0,368.6,
                //coordinate 4
                578.6,368.6,
                //coordinate 5
                578.6,288.4,
                //coordinate 6
                671.8,288.4,
                //coordinate 7
                671.8,260.7,
                //coordinate 8
                688.5,260.7,
                //coordinate 9
                688.5,365.8,
                //coordinate 10
                726.7,364.8,
                //coordinate 11
                726.7,421.0,
                //coordinate 12
                746.4,421.0,
                //coordinate 13
                746.4,453.7,
                //coordinate 14
                688.3,453.7,
                //coordinate 15
                688.3,646.8,
                //coordinate 16
                507.4,646.8,
                //coordinate 17
                507.4,700.0,
                //coordinate 18
                413.8,700.0,
                //coordinate 19
                413.8,649.5,
                //coordinate 20
                354.0,649.5,

        });

        workzoneTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                391.0, 72.4,
                //coordinate 2
                391.0, 224.0,
                //coordinate 3
                434.8, 224.0,
                //coordinate 4
                434.8,368.4,
                //coordinate 5
                577.6,368.4,
                //coordinate 6
                577.6,288.4,
                //coordinate 7
                671.8,288.4,
                //coordinate 8
                671.8,260.7,
                //coordinate 9
                685.5,260.7,
                //coordinate 10
                685.5,72.4,

        });

        workzoneThree.getPoints().addAll(new Double[]{
                //coordinate 1
                77.7, 72.4,
                //coordinate 2
                77.7, 421.5,
                //coordinate 3
                297.5, 421.5,
                //coordinate 4
                297.5, 367.8,
                //coordinate 5
                354.0, 367.8,
                //coordinate 6
                354.0, 341.4,
                //coordinate 7
                434.8, 341.4,
                //coordinate 8
                434.8, 224.0,
                //coordinate 9
                391.0, 224.0,
                //coordinate 10
                391.0, 72.4,

        });

        workzoneFour.getPoints().addAll(new Double[]{
                //coordinate 1
                77.0, 421.5,
                //coordinate 2
                77.0, 702.0,
                //coordinate 3
                297.5, 702.0,
                //coordinate 4
                297.5, 649.0,
                //coordinate 5
                354.0, 649.0,
                //coordinate 6
                354.0, 421.5,

        });

        workzoneFive.getPoints().addAll(new Double[]{
                //coordinate 1
                507.4,646.76,
                //coordinate 2
                507.4,1005.1,
                //coordinate 3
                892.0,1005.1,
                //coordinate 4
                892.0,816.3,
                //coordinate 5
                861.5,816.3,
                //coordinate 6
                861.5,646.8,
                //coordinate 7
                727.0,646.8,
                //coordinate 8
                727.0,610.3,
                //coordinate 9
                688.3,610.3,
                //coordinate 10
                688.3,646.8,

        });

        auditorium.getPoints().addAll(new Double[]{
                //coordinate 1
                1178.8,572.3,
                //coordinate 2
                1269.6,540.4,
                //coordinate 3
                1335.3,699.0,
                //coordinate 4
                1240.7,824.92,
                //coordinate 5
                1100.0,823.1,
                //coordinate 6
                1105.0,731.5,
                //coordinate 7
                1117.6,731.5,
                //coordinate 8
                1117.6,722.76,
                //coordinate 9
                1177.2,611.0,
                //coordinate 10
                1192.1,606.1,


        });


        workzoneOneOne.getPoints().addAll(new Double[]{
                //coordinate 1
                352.2,601.5,
                //coordinate 2
                352.2,459.8,
                //coordinate 3
                465.8,459.8,
                //coordinate 4
                465.8,601.5,

        });

        workzoneOneTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                494.3,627.0,
                //coordinate 2
                494.3,587.9,
                //coordinate 3
                564.6,587.9,
                //coordinate 4
                564.6,627.0,

        });

        workzoneOneThree.getPoints().addAll(new Double[]{
                //coordinate 1
                587.4,627.0,
                //coordinate 2
                587.4,587.9,
                //coordinate 3
                661.5,587.9,
                //coordinate 4
                661.5,627.0,

        });

        workzoneOneFour.getPoints().addAll(new Double[]{
                //coordinate 1
                601.5,574.6,
                //coordinate 2
                601.5,454.1,
                //coordinate 3
                640.3,454.1,
                //coordinate 4
                640.3,574.6,

        });

        workzoneTwoOne.getPoints().addAll(new Double[]{
                //coordinate 1
                536.5,198.7,
                //coordinate 2
                536.5,103.4,
                //coordinate 3
                555.5,103.4,
                //coordinate 4
                555.5,198.7,

        });

        workzoneTwoTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                560.1,198.7,
                //coordinate 2
                560.1,74.1,
                //coordinate 3
                578.7,74.1,
                //coordinate 4
                578.5,198.7,

        });

        workzoneThreeOne.getPoints().addAll(new Double[]{
                //coordinate 1
                82.0,133.0,
                //coordinate 2
                82.0,89.3,
                //coordinate 3
                101.0,89.3,
                //coordinate 4
                101.0,133.0,

        });

        workzoneThreeTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                82.0,205.2,
                //coordinate 2
                82.0,161.9,
                //coordinate 3
                101.0,161.9,
                //coordinate 4
                101.0,205.2,

        });

        workzoneThreeThree.getPoints().addAll(new Double[]{
                //coordinate 1
                82.0,281.6,
                //coordinate 2
                82.0,228.8,
                //coordinate 3
                101.0,228.8,
                //coordinate 4
                101.0,281.6,

        });

        workzoneThreeFour.getPoints().addAll(new Double[]{
                //coordinate 1
                115.9,131.9,
                //coordinate 2
                115.9,93.5,
                //coordinate 3
                213.9,93.5,
                //coordinate 4
                213.9,131.9,

        });

        workzoneThreeFive.getPoints().addAll(new Double[]{
                //coordinate 1
                115.9,188.5,
                //coordinate 2
                115.9,150.5,
                //coordinate 3
                213.9,150.5,
                //coordinate 4
                213.9,188.5,

        });

        workzoneThreeSix.getPoints().addAll(new Double[]{
                //coordinate 1
                152.3,272.9,
                //coordinate 2
                152.3,216.6,
                //coordinate 3
                229.9,216.6,
                //coordinate 4
                229.9,272.9,

        });

        workzoneThreeSeven.getPoints().addAll(new Double[]{
                //coordinate 1
                272.8,419.5,
                //coordinate 2
                272.8,255.7,
                //coordinate 3
                299.8,255.7,
                //coordinate 4
                299.8,419.5,

        });

        workzoneFourOne.getPoints().addAll(new Double[]{
                //coordinate 1
                99.9,521.7,
                //coordinate 2
                99.9,494.8,
                //coordinate 3
                127.6,494.8,
                //coordinate 4
                127.6,521.7,

        });

        workzoneFourTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                239.4,548.0,
                //coordinate 2
                239.4,512.6,
                //coordinate 3
                266.0,512.6,
                //coordinate 4
                266.0,548.0,

        });

        workzoneFiveOne.getPoints().addAll(new Double[]{
                //coordinate 1
                523.2,803.3,
                //coordinate 2
                523.2,785.8,
                //coordinate 3
                547.5,785.8,
                //coordinate 4
                547.5,803.3,

        });

        workzoneFiveTwo.getPoints().addAll(new Double[]{
                //coordinate 1
                692.7,907.4,
                //coordinate 2
                692.7,847.8,
                //coordinate 3
                716.3,847.8,
                //coordinate 4
                716.3,907.4,

        });

        workzoneFiveThree.getPoints().addAll(new Double[]{
                //coordinate 1
                684.3,991.8,
                //coordinate 2
                684.3,932.5,
                //coordinate 3
                707.5,932.5,
                //coordinate 4
                707.5,991.8,

        });

        workzoneFiveFour.getPoints().addAll(new Double[]{
                //coordinate 1
                743.6,818.5,
                //coordinate 2
                743.6,720.5,
                //coordinate 3
                782.0,720.5,
                //coordinate 4
                782.0,818.5,

        });

        workzoneFiveFive.getPoints().addAll(new Double[]{
                //coordinate 1
                800.6,818.9,
                //coordinate 2
                800.6,720.5,
                //coordinate 3
                839.0,720.5,
                //coordinate 4
                839.0,818.9,

        });

        workzoneFiveSix.getPoints().addAll(new Double[]{
                //coordinate 1
                778.2,718.2,
                //coordinate 2
                778.2,647.9,
                //coordinate 3
                871.7,647.9,
                //coordinate 4
                871.7,718.2,

        });

        workzoneFiveSeven.getPoints().addAll(new Double[]{
                //coordinate 1
                866.4,999.8,
                //coordinate 2
                866.4,844.4,
                //coordinate 3
                892.6,844.4,
                //coordinate 4
                892.6,999.8,

        });



        //setting the default fill color to transparent
        workzoneFive.setFill(emptyWorkzone);
        workzoneFour.setFill(emptyWorkzone);
        workzoneThree.setFill(emptyWorkzone);
        workzoneTwo.setFill(emptyWorkzone);
        workzoneOne.setFill(emptyWorkzone);


        workzoneOneOne.setFill(emptyWorkzone);
        workzoneOneTwo.setFill(emptyWorkzone);
        workzoneOneThree.setFill(emptyWorkzone);
        workzoneOneFour.setFill(emptyWorkzone);
        workzoneTwoOne.setFill(emptyWorkzone);
        workzoneTwoTwo.setFill(emptyWorkzone);
        workzoneThreeOne.setFill(emptyWorkzone);
        workzoneThreeTwo.setFill(emptyWorkzone);
        workzoneThreeThree.setFill(emptyWorkzone);
        workzoneThreeFour.setFill(emptyWorkzone);
        workzoneThreeFive.setFill(emptyWorkzone);
        workzoneThreeSix.setFill(emptyWorkzone);
        workzoneThreeSeven.setFill(emptyWorkzone);
        workzoneFourOne.setFill(emptyWorkzone);
        workzoneFourTwo.setFill(emptyWorkzone);
        workzoneFiveOne.setFill(emptyWorkzone);
        workzoneFiveTwo.setFill(emptyWorkzone);
        workzoneFiveThree.setFill(emptyWorkzone);
        workzoneFiveFour.setFill(emptyWorkzone);
        workzoneFiveFive.setFill(emptyWorkzone);
        workzoneFiveSix.setFill(emptyWorkzone);
        workzoneFiveSeven.setFill(emptyWorkzone);

        classroomOne.setFill(emptyWorkzone);
        classroomTwo.setFill(emptyWorkzone);
        classroomThree.setFill(emptyWorkzone);
        classroomFour.setFill(emptyWorkzone);
        classroomFive.setFill(emptyWorkzone);
        classroomSix.setFill(emptyWorkzone);
        classroomSeven.setFill(emptyWorkzone);
        classroomEight.setFill(emptyWorkzone);
        classroomNine.setFill(emptyWorkzone);
        classroomTen.setFill(emptyWorkzone);
        auditorium.setFill(emptyWorkzone);

        //FLEXIBLE WORKZONES
        workzoneFive.setFill(emptyWorkzone);
        workzoneFour.setFill(emptyWorkzone);
        workzoneThree.setFill(emptyWorkzone);
        workzoneTwo.setFill(emptyWorkzone);
        workzoneOne.setFill(emptyWorkzone);



        //figure out the current time
        long currentUnixTime = System.currentTimeMillis() / 1000;

        //in the database, check the bookings and see if there are any which are currently occurring
        DBBuffer<BookingEntry> dbb = new DBBuffer<>(DBMI.RoomBookingBookings.value);
        HashMap<String, BookingEntry> booingHM = dbb.getBufferHashMap();
        ArrayList<Booking>  bookingsInCurrentTime = new ArrayList<Booking>();

        BookingEntry tmp;
        ArrayList<String> bookedRooms = new ArrayList<String>();

        //go through everything and store bookings which occur in current time
        for (Map.Entry<String, BookingEntry> entry : booingHM.entrySet()) {
            tmp = entry.getValue();
            //if the booking occurs in current time
            if(tmp.isHappening(currentUnixTime)){
                bookingsInCurrentTime.add(new Booking(tmp.getStartDate(), tmp.getEndDate(), tmp.getReserver(), tmp.getRoomID()));
            }

            //add the room to a list of scheduled rooms (if it is a duplicate do not add it)
            if ( ! (bookedRooms.contains(tmp.getRoomID())) ) {
                bookedRooms.add(tmp.getRoomID());
            }
        }






        //loop through all of the rooms booked in the current time and color their respective rooms
        for(int i = 0; i < bookingsInCurrentTime.size(); i++){

            switch (bookingsInCurrentTime.get(i).getRoom()){

                case "CLASS00001":
                    classroomOne.setFill(sea);
                    break;
                case "CLASS00002":
                    classroomTwo.setFill(sea);
                    break;
                case "CLASS00003":
                    classroomThree.setFill(sea);
                    break;
                case "CLASS00004":
                    classroomFour.setFill(sea);
                    break;
                case "CLASS00005":
                    classroomFive.setFill(sea);
                    break;
                case "CLASS00006":
                    classroomSix.setFill(sea);
                    break;
                case "CLASS00007":
                    classroomSeven.setFill(sea);
                    break;
                case "CLASS00008":
                    classroomEight.setFill(sea);
                    break;
                case "FCONF00001":
                    classroomNine.setFill(sea);
                    break;
                case "AUDIT00001":
                    auditorium.setFill(sea);
                    break;
            }

        }


        ArrayList<WorkstationEntry> workstationsOccupied = highlightWorkstations();

//        for(int i = 0; i < workstationsOccupied.size(); i++){
//            switch (workstationsOccupied.get(i).getName()){
//
//                case "Workzone 1":
//                    workzoneOne.setFill(sea);
//                    break;
//                case "Workzone 2":
//                    workzoneTwo.setFill(sea);
//                    break;
//                case "Workzone 3":
//                    workzoneThree.setFill(sea);
//                    break;
//                case "Workzone 4":
//                    workzoneFour.setFill(sea);
//                    break;
//                case "Workzone 5":
//                    workzoneFive.setFill(sea);
//                    break;
//
//            }
//        }


        //PLEASE KEEP THIS CODE HERE
        //resizing and scaling
        polygonGroup.setScaleX(0.66);
        polygonGroup.setScaleY(0.66);

        polygonGroup.setTranslateY( - 12 );
        polygonGroup.setTranslateX( - 14 );

        //here's some stuff that probably isn't useful
        //- (polygonGroup.getBoundsInLocal().getHeight() - polygonGroup.getBoundsInParent().getHeight()) / 1
        //- (polygonGroup.getBoundsInLocal().getWidth() - polygonGroup.getBoundsInParent().getWidth()) / 1

        //adding the polygons to the group
//        polygonGroup.getChildren().add(workzoneFive);
//        polygonGroup.getChildren().add(workzoneFour);
//        polygonGroup.getChildren().add(workzoneThree);
//        polygonGroup.getChildren().add(workzoneTwo);
//        polygonGroup.getChildren().add(workzoneOne);


        polygonGroup.getChildren().add(workzoneOneOne);
        polygonGroup.getChildren().add(workzoneOneTwo);
        polygonGroup.getChildren().add(workzoneOneThree);
        polygonGroup.getChildren().add(workzoneOneFour);
        polygonGroup.getChildren().add(workzoneTwoOne);
        polygonGroup.getChildren().add(workzoneTwoTwo);
        polygonGroup.getChildren().add(workzoneThreeOne);
        polygonGroup.getChildren().add(workzoneThreeTwo);
        polygonGroup.getChildren().add(workzoneThreeThree);
        polygonGroup.getChildren().add(workzoneThreeFour);
        polygonGroup.getChildren().add(workzoneThreeFive);
        polygonGroup.getChildren().add(workzoneThreeSix);
        polygonGroup.getChildren().add(workzoneThreeSeven);
        polygonGroup.getChildren().add(workzoneFourOne);
        polygonGroup.getChildren().add(workzoneFourTwo);
        polygonGroup.getChildren().add(workzoneFiveOne);
        polygonGroup.getChildren().add(workzoneFiveTwo);
        polygonGroup.getChildren().add(workzoneFiveThree);
        polygonGroup.getChildren().add(workzoneFiveFour);
        polygonGroup.getChildren().add(workzoneFiveFive);
        polygonGroup.getChildren().add(workzoneFiveSix);
        polygonGroup.getChildren().add(workzoneFiveSeven);


        polygonGroup.getChildren().add(classroomOne);
        polygonGroup.getChildren().add(classroomTwo);
        polygonGroup.getChildren().add(classroomThree);
        polygonGroup.getChildren().add(classroomFour);
        polygonGroup.getChildren().add(classroomFive);
        polygonGroup.getChildren().add(classroomSix);
        polygonGroup.getChildren().add(classroomSeven);
        polygonGroup.getChildren().add(classroomEight);
        polygonGroup.getChildren().add(classroomNine);
        polygonGroup.getChildren().add(classroomTen);

        polygonGroup.getChildren().add(auditorium);

        //System.out.println("MADE IT TO THE FUNCTION");

        randomOccupy();

        //System.out.println("MADE IT PAST THE FUNCTION");


    }


    protected boolean hasValidInput(ActionEvent event){
        return true;
    }

    public void onRoomListClick(ActionEvent event) throws IOException{
        loadFxml("agendaPopup.fxml");
    }

    public ArrayList<WorkstationEntry> highlightWorkstations(){
        //in the database, check the workstationentries and see if there are any which are currently occupied
        DBBuffer<WorkstationEntry> dbbWork = new DBBuffer<>(DBMI.Workstations.value);
        HashMap<String, WorkstationEntry> workstationHM = dbbWork.getBufferHashMap();
        ArrayList<WorkstationEntry>  workstationsOccupied = new ArrayList<WorkstationEntry>();

        WorkstationEntry tmpWork;  //the current Workstation Entry

        //go through everything and store workstationentries which are occupied
        for (Map.Entry<String, WorkstationEntry> entry : workstationHM.entrySet()) {
            tmpWork = entry.getValue();
            //if the WorkstationEntry has "true" as its isOccupied value
            if(tmpWork.getIsOccupied().equals("true")){
                workstationsOccupied.add(new WorkstationEntry(tmpWork.getId(), tmpWork.getName(), tmpWork.getLocation(), tmpWork.getOccupancy(), tmpWork.getIsOccupied()));
            }

        }

        return workstationsOccupied;
    }

    public void onBookingClick(ActionEvent event) throws IOException{
        loadFxml("bookingPage.fxml");
    }

    public void onFullClick(ActionEvent event) throws IOException{
        loadFxml("agendaPopup.fxml");
    }

    public void onRoomClick(ActionEvent event) throws IOException{
        loadFxml("agendaPopup.fxml");
    }

    public void onDemoClick(ActionEvent event) throws IOException{
        loadFxml("demoOptionsPage.fxml");
    }


    public void randomOccupy(){

        //System.out.println("MADE IT TO THE DATABASE STUFF");
        //in the database, check the workstationentries and see if there are any which are currently occupied
        DBBuffer<WorkstationEntry> dbbWork = new DBBuffer<>(DBMI.Workstations.value);
        HashMap<String, WorkstationEntry> workstationHM = dbbWork.getBufferHashMap();

        //FILLING THE TWO LISTS OF OCCUPIED AND UNOCCUPIED ROOMS
        ArrayList<WorkstationEntry>  workstationsOccupied = new ArrayList<WorkstationEntry>();
        ArrayList<WorkstationEntry> workstationsUnoccupied = new ArrayList<WorkstationEntry>();
        ArrayList<WorkstationEntry> workstationsList = new ArrayList<WorkstationEntry>();

        WorkstationEntry tmpWork;  //the current Workstation Entry

        //go through everything and store workstationentries which are occupied
        for (Map.Entry<String, WorkstationEntry> entry : workstationHM.entrySet()) {
            tmpWork = entry.getValue();
            WorkstationEntry ourWork = new WorkstationEntry(tmpWork.getId(), tmpWork.getName(), tmpWork.getLocation(), tmpWork.getOccupancy(), tmpWork.getIsOccupied());
            //if the WorkstationEntry has "true" as its isOccupied value
            if(tmpWork.getIsOccupied().equals("true")){
                workstationsOccupied.add(ourWork);
            } else {
                //if the workstation has "false" as its isOccupied value
                workstationsUnoccupied.add(ourWork);
            }
            workstationsList.add(ourWork);
        }


        Timer timer = new Timer();


        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Random rand = new Random();

                        //int occupiedPicker = rand.nextInt(1);
                        boolean occupiedPicker; //= false; // = Math.random() < 5;


                        for(WorkstationEntry W : workstationsList){
                            //boolean occupiedPicker = Math.random() < 5;
                            occupiedPicker = (Math.random() < 0.5);
                            //if the occupied picker is 0, mark the room as unoccupied
                            if(occupiedPicker == true){
                                WorkstationEntry toUnocccupy = W;
                                toUnocccupy.setIsOccupied("false");

                                if(!(workstationsUnoccupied.contains(toUnocccupy))){
                                    workstationsUnoccupied.add(toUnocccupy);
                                    workstationsOccupied.remove(toUnocccupy);

                                    unOccupyRoom(toUnocccupy);
                                }



                            } else if(occupiedPicker == false){
                            //if the occupied picker is 1, mark the room as occupied
                            WorkstationEntry toOccupy = W;
                            toOccupy.setIsOccupied("true");

                            if(!(workstationsOccupied.contains(toOccupy))){
                                workstationsOccupied.add(toOccupy);
                                workstationsUnoccupied.remove(toOccupy);

                                occupyRoom(toOccupy);

                            }

                            }

                        }

                        workstationHelper(workstationsOccupied, workstationsUnoccupied);
                        highlightWorkstations();




//                        //UNOCCUPY ROOM
//                        if(workstationsOccupied.size() > 0){
//                            // Obtain a number between 0 and the length of the occupiedList.
//                            int occupiedPicker = rand.nextInt(workstationsOccupied.size() + 1);
//                            //randomly pick a room from the occupiedList and mark it unoccupied
//                            WorkstationEntry toUnocccupy = workstationsOccupied.get(occupiedPicker);
//                            toUnocccupy.setIsOccupied("false");
//                            workstationsUnoccupied.add(toUnocccupy);
//                            workstationsOccupied.remove(occupiedPicker);
//                            unOccupyRoom(toUnocccupy);
//                            //if four workstations are occupied, randomly unoccupy another room
//                            if(workstationsOccupied.size() > 3){
//                                // Obtain a number between 0 and the length of the occupiedList.
//                                int occupiedPicker2 = rand.nextInt(workstationsOccupied.size() + 1);
//                                //randomly pick a room from the occupiedList and mark it unoccupied
//                                WorkstationEntry toUnocccupy2 = workstationsOccupied.get(occupiedPicker2);
//                                toUnocccupy2.setIsOccupied("false");
//                                workstationsUnoccupied.add(toUnocccupy2);
//                                workstationsOccupied.remove(occupiedPicker);
//                                unOccupyRoom(toUnocccupy2);
//
//                            }
//
//                        }
//
//
//                        //OCCUPY ROOM
//                        // Obtain a number between 0 and the length of the occupiedList.
//                        if (workstationsUnoccupied.size() != 0) {
//                            int toOccupyPicker = rand.nextInt(workstationsUnoccupied.size() + 1);
//                            //randomly pick a room from the toOccupyList and mark it occupied
//                            WorkstationEntry toOccupy1 = workstationsUnoccupied.get(toOccupyPicker);
//                            WorkstationEntry toOccupy2 = workstationsUnoccupied.get(toOccupyPicker + 1);
//                            toOccupy1.setIsOccupied("true");
//                            toOccupy2.setIsOccupied("true");
//                            workstationsOccupied.add(toOccupy1);
//                            workstationsOccupied.add(toOccupy2);
//                            workstationsUnoccupied.remove(toOccupyPicker);
//                            workstationsUnoccupied.remove(toOccupyPicker + 1);
//                            occupyRoom(toOccupy1);
//                            occupyRoom(toOccupy2);
//
//                            workstationHelper(workstationsOccupied, workstationsUnoccupied);
//                        } else {
//                            LOGGER.log(Level.SEVERE, "workstationsUnoccupied was empty while marking random workstations occupied");
//
//                        }

                    }
                });
            }
        };
        timer.scheduleAtFixedRate(update,0,20000);


    }

    private void occupyRoom(WorkstationEntry stationToOccupy) {
        ArrayList<String> stationUpdate = new ArrayList<>();

        DBBuffer<WorkstationEntry> dbb = new DBBuffer<>(DBMI.Workstations.value);
        HashMap<String, WorkstationEntry> workstationHM = dbb.getBufferHashMap();

        WorkstationEntry tmp = workstationHM.get(stationToOccupy.getId());

        stationUpdate.add(null);
        stationUpdate.add(null);
        stationUpdate.add(null);
        //change the isOccupied value to true
        stationUpdate.add("true");

        dbb.update(stationToOccupy.getId(), stationUpdate);


    }

    private void unOccupyRoom(WorkstationEntry stationToOccupy) {
        ArrayList<String> stationUpdate = new ArrayList<>();

        DBBuffer<WorkstationEntry> dbb = new DBBuffer<>(DBMI.Workstations.value);
        HashMap<String, WorkstationEntry> workstationHM = dbb.getBufferHashMap();

        //WorkstationEntry tmp = workstationHM.get(stationToOccupy.getId());

        stationUpdate.add(null);
        stationUpdate.add(null);
        stationUpdate.add(null);
        //change the isOccupied value to true
        stationUpdate.add("false");

        dbb.update(stationToOccupy.getId(), stationUpdate);

    }


    private void workstationHelper(ArrayList<WorkstationEntry> workstationsOccupied, ArrayList<WorkstationEntry> workstationsUnoccupied){
        for(int i = 0; i < workstationsOccupied.size(); i++){
            switch (workstationsOccupied.get(i).getName()){

                case "Workzone 11":
                    workzoneOneOne.setFill(sea);
                    break;
                case "Workzone 12":
                    workzoneOneTwo.setFill(sea);
                    break;
                case "Workzone 13":
                    workzoneOneThree.setFill(sea);
                    break;
                case "Workzone 14":
                    workzoneOneFour.setFill(sea);
                    break;
                case "Workzone 21":
                    workzoneTwoOne.setFill(sea);
                    break;
                case "Workzone 22":
                    workzoneTwoTwo.setFill(sea);
                    break;
                case "Workzone 31":
                    workzoneThreeOne.setFill(sea);
                    break;
                case "Workzone 32":
                    workzoneThreeTwo.setFill(sea);
                    break;
                case "Workzone 33":
                    workzoneThreeThree.setFill(sea);
                    break;
                case "Workzone 34":
                    workzoneThreeFour.setFill(sea);
                    break;
                case "Workzone 35":
                    workzoneThreeFive.setFill(sea);
                    break;
                case "Workzone 36":
                    workzoneThreeSix.setFill(sea);
                    break;
                case "Workzone 37":
                    workzoneThreeSeven.setFill(sea);
                    break;
                case "Workzone 41":
                    workzoneFourOne.setFill(sea);
                    break;
                case "Workzone 42":
                    workzoneFourTwo.setFill(sea);
                    break;
                case "Workzone 51":
                    workzoneFiveOne.setFill(sea);
                    break;
                case "Workzone 52":
                    workzoneFiveTwo.setFill(sea);
                    break;
                case "Workzone 53":
                    workzoneFiveThree.setFill(sea);
                    break;
                case "Workzone 54":
                    workzoneFiveFour.setFill(sea);
                    break;
                case "Workzone 55":
                    workzoneFiveFive.setFill(sea);
                    break;
                case "Workzone 56":
                    workzoneFiveSix.setFill(sea);
                    break;
                case "Workzone 57":
                    workzoneFiveSeven.setFill(sea);
                    break;

            }
        }

        for(int i = 0; i < workstationsUnoccupied.size(); i++){
            switch (workstationsUnoccupied.get(i).getName()){

                case "Workzone 11":
                    workzoneOneOne.setFill(emptyWorkzone);
                    break;
                case "Workzone 12":
                    workzoneOneTwo.setFill(emptyWorkzone);
                    break;
                case "Workzone 13":
                    workzoneOneThree.setFill(emptyWorkzone);
                    break;
                case "Workzone 14":
                    workzoneOneFour.setFill(emptyWorkzone);
                    break;
                case "Workzone 21":
                    workzoneTwoOne.setFill(emptyWorkzone);
                    break;
                case "Workzone 22":
                    workzoneTwoTwo.setFill(emptyWorkzone);
                    break;
                case "Workzone 31":
                    workzoneThreeOne.setFill(emptyWorkzone);
                    break;
                case "Workzone 32":
                    workzoneThreeTwo.setFill(emptyWorkzone);
                    break;
                case "Workzone 33":
                    workzoneThreeThree.setFill(emptyWorkzone);
                    break;
                case "Workzone 34":
                    workzoneThreeFour.setFill(emptyWorkzone);
                    break;
                case "Workzone 35":
                    workzoneThreeFive.setFill(emptyWorkzone);
                    break;
                case "Workzone 36":
                    workzoneThreeSix.setFill(emptyWorkzone);
                    break;
                case "Workzone 37":
                    workzoneThreeSeven.setFill(emptyWorkzone);
                    break;
                case "Workzone 41":
                    workzoneFourOne.setFill(emptyWorkzone);
                    break;
                case "Workzone 42":
                    workzoneFourTwo.setFill(emptyWorkzone);
                    break;
                case "Workzone 51":
                    workzoneFiveOne.setFill(emptyWorkzone);
                    break;
                case "Workzone 52":
                    workzoneFiveTwo.setFill(emptyWorkzone);
                    break;
                case "Workzone 53":
                    workzoneFiveThree.setFill(emptyWorkzone);
                    break;
                case "Workzone 54":
                    workzoneFiveFour.setFill(emptyWorkzone);
                    break;
                case "Workzone 55":
                    workzoneFiveFive.setFill(emptyWorkzone);
                    break;
                case "Workzone 56":
                    workzoneFiveSix.setFill(emptyWorkzone);
                    break;
                case "Workzone 57":
                    workzoneFiveSeven.setFill(emptyWorkzone);
                    break;

            }
        }






    }

}
