package bishopfish;

import bishopfish.controllers.Controller;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.utils.SayWeather;
import bishopfish.utils.TextToSpeech;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.aksingh.owmjapis.api.APIException;
import sun.security.pkcs11.Secmod;

import java.io.File;
import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String mainFxml = "controllers/master.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(mainFxml)));
        Parent root = loader.load();
//        Controller controller = loader.getController();
//        Stack<String> backFxmlStack = new Stack<>();
//        controller.setBackFxmlStack(backFxmlStack);
//        controller.setCurFxml(mainFxml);

        JFXDecorator decorator = new JFXDecorator(primaryStage, root);
        decorator.setCustomMaximize(true);
        decorator.setMaximized(true);

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Brigham and Women's Hospital");
        primaryStage.setScene(new Scene(decorator));
        primaryStage.getIcons().add(new Image("bishopfish/Brigham_and_Womens_Hospital_logo.svg.png"));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.timer(mouseEvent);
            }
        });
    }


    public static void main(String[] args) {
        // load in gifts
        DBUpdater giftDbu = new DBUpdater(DBMI.Gifts.value);
        //giftDbu.dropTable();
        if (!giftDbu.tableExists()) {
            giftDbu.createTable();
            giftDbu.fillTableFromCsv(new File("gifts.csv"), false, false);
        }
//        // load in sample
//        DBUpdater sampleDbu = new DBUpdater(DBMI.Sample.value);
//        //sampleDbu.dropTable();
//        if (!sampleDbu.tableExists()) {
//            sampleDbu.createTable();
//            sampleDbu.fillTableFromCsv(new File("sample.csv"), false, false);
//        }

        //initialize the service request table
        DBUpdater srDbu =  new DBUpdater(DBMI.ServiceRequest.value);
        //srDbu.dropTable();
        srDbu.createTable();

        DBUpdater bookingDbu =  new DBUpdater(DBMI.RoomBookingBookings.value);
        //bookingDbu.dropTable();
        bookingDbu.createTable();


        // load in departments
        DBUpdater depDbu = new DBUpdater(DBMI.Departments.value);
        //depDbu.dropTable();
        if (!depDbu.tableExists()) {
            depDbu.createTable();
            depDbu.fillTableFromCsv(new File("departments.csv"), true, false);
        }
        // load in employees
        DBUpdater empDbu = new DBUpdater(DBMI.Employees.value);
        //empDbu.dropTable();
        if (!empDbu.tableExists()) {
            empDbu.createTable();
            empDbu.fillTableFromCsv(new File("employees.csv"), true, false);
        }
        // load in religious services
        DBUpdater religiousDbu = new DBUpdater(DBMI.ReligiousServices.value);
       // religiousDbu.dropTable();
        if (!religiousDbu.tableExists()) {
            religiousDbu.createTable();
            religiousDbu.fillTableFromCsv(new File("religiousServices.csv"), false, false);
        }
        // load in Interpreter request
        DBUpdater interpreterDbu = new DBUpdater(DBMI.InterpreterRequests.value);
       // interpreterDbu.dropTable();
        if (!interpreterDbu.tableExists()) {
            interpreterDbu.createTable();
            interpreterDbu.fillTableFromCsv(new File("interpreterRequest.csv"), false, false);
        }

        //loads in reading request
        DBUpdater readingDbu = new DBUpdater(DBMI.Reading.value);
        //readingDbu.dropTable();
        if (!readingDbu.tableExists()) {
            readingDbu.createTable();
            readingDbu.fillTableFromCsv(new File("books.csv"), false, false);
        }

        DBUpdater floralDbu = new DBUpdater(DBMI.Floral.value);
       // floralDbu.dropTable();
        if (!floralDbu.tableExists()) {
            floralDbu.createTable();
            floralDbu.fillTableFromCsv(new File("floral.csv"), false, false);
        }

        //initialize the rooms table
//        DBUpdater roomsDbu =  new DBUpdater(DBMI.RoomBookingRooms.value);
//        //roomsDbu.dropTable();
//        roomsDbu.createTable();
//        //initialize the booking table

        DBUpdater workstationDbu = new DBUpdater(DBMI.Workstations.value);
        workstationDbu.dropTable();
        if (!workstationDbu.tableExists()) {
            workstationDbu.createTable();
//            workstationDbu.fillTableFromCsv(new File("workstations.csv"), false, false);
        }

//        DBUpdater roomsDbu = new DBUpdater(DBMI.RoomBookingRooms.value);
//        roomsDbu.dropTable();
//        if (!roomsDbu.tableExists()) {
//            roomsDbu.createTable();
//            roomsDbu.fillTableFromCsv(new File("rooms.csv"), false, false);
//        }


        //initialize the emergency table
        DBUpdater emergencyDbu = new DBUpdater(DBMI.Emergency.value);
        emergencyDbu.createTable();


        DBUpdater nodeDbu = new DBUpdater(DBMI.Nodes.value);
        nodeDbu.dropTable();
        if (!nodeDbu.tableExists()) {
            nodeDbu.createTable();
            nodeDbu.fillTableFromCsv(new File("nodes.csv"), true, false);
        }
        // load in edges
        DBUpdater edgeDbu = new DBUpdater(DBMI.Edges.value);
        edgeDbu.dropTable();
        if (!edgeDbu.tableExists()) {
            edgeDbu.createTable();
            edgeDbu.fillTableFromCsv(new File("edges.csv"), true, true);
        }

        // accommodation Request
        DBUpdater accommodationDbu = new DBUpdater(DBMI.Accommodation.value);
        //accommodationDbu.dropTable();
        if (!accommodationDbu.tableExists()) {
            accommodationDbu.createTable();
            accommodationDbu.fillTableFromCsv(new File("accommodation.csv"), false, false);
        }

        DBUpdater employeeLoginDbu = new DBUpdater(DBMI.EmployeeLogins.value);
        //employeeLoginDbu.dropTable();
        if (!employeeLoginDbu.tableExists()) {
            employeeLoginDbu.createTable();
            employeeLoginDbu.fillTableFromCsv(new File("employeeLogins.csv"), false, false);
        }

        // Pet Therapy Request
        DBUpdater petTherapyDbu = new DBUpdater(DBMI.PetTherapy.value);
        //petTherapyDbu.dropTable();
        if (!petTherapyDbu.tableExists()) {
            petTherapyDbu.createTable();
            petTherapyDbu.fillTableFromCsv(new File("pet.csv"), false, false);
        }

        // Transport type Request
        DBUpdater transportTypeDbu = new DBUpdater(DBMI.TransportType.value);
        //transportTypeDbu.dropTable();
        if (!transportTypeDbu.tableExists()) {
            transportTypeDbu.createTable();
            transportTypeDbu.fillTableFromCsv(new File("transport.csv"), false, false);
        }
        // Wheelchair Option Request
        DBUpdater wheelchairOptionDbu = new DBUpdater(DBMI.WheelchairOptions.value);
        //wheelchairOptionDbu.dropTable();
        if (!wheelchairOptionDbu.tableExists()) {
            wheelchairOptionDbu.createTable();
            wheelchairOptionDbu.fillTableFromCsv(new File("wheelchairOptions.csv"), false, false);
        }



        // PermissionDecoder
        DBUpdater permissionDecoderDbu = new DBUpdater(DBMI.PermissionDecoder.value);
        //permissionDecoderDbu.dropTable();
        if (!permissionDecoderDbu.tableExists()) {
            permissionDecoderDbu.createTable();
            permissionDecoderDbu.fillTableFromCsv(new File("permissionDecoder.csv"), false, false);
        }

        // Wheelchair Option Request
        DBUpdater srTypeDbu = new DBUpdater(DBMI.FeatureType.value);
        srTypeDbu.dropTable();
        if (!srTypeDbu.tableExists()) {
            srTypeDbu.createTable();
            srTypeDbu.fillTableFromCsv(new File("featureId.csv"), true, false);
        }


        // load in nodes
        // load in sample
//        DBUpdater sampleDbu = new DBUpdater(DBMI.Sample.value);
//        sampleDbu.dropTable();
//        if (!sampleDbu.tableExists()) {
//            sampleDbu.createTable();
//            sampleDbu.fillTableFromCsv(new File("sample.csv"), false, false);
//        }







        //new ThreadedSpeech("Welcome").run();

        //Platform.runLater(()->{
         //   TextToSpeech.speak("Test");
        //});

        Thread thread = new Thread(){
            public void run(){
//                try {
//                    SayWeather.sayWeather();
//                } catch (APIException e) {
//                    e.printStackTrace();
//                }
            }
        };

        thread.start();
        launch(args);

    }
}