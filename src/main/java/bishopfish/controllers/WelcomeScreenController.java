package bishopfish.controllers;

import bishopfish.db.DBCustom;
import bishopfish.mbta.Mbta;
import bishopfish.utils.EncryptPassword;
import bishopfishapi.Emergency;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreenController<thread> extends Controller{

    public Label lblCity;

    @FXML
    JFXButton loginBtn;

    @FXML
    JFXButton callEmergencyButton;

    @FXML
    JFXTextField usernameTf;

    @FXML
    JFXPasswordField passwordTf;
    @FXML
    public void initialize(){

//        loginBtn.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                timer(mouseEvent);
//            }
//        });
//        try {
//            getWeatherData();
//        } catch (APIException e) {
//            e.printStackTrace();
//        }

//        try {
//            SayWeather.sayWeather();
//        } catch (APIException e) {
//            e.printStackTrace();
//        }

//        getMBTAData();
//
//        Timer updater = new Timer();
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        getMBTAData();
//                        try {
//                            getWeatherData();
//                        } catch (APIException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        };
//        updater.scheduleAtFixedRate(update,10000,10000);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (CSI.getInstance().isMementoActive()) {
                            CSI.getInstance().setMementoCountdown(30);
                            CSI.getInstance().setMementoActive(false);
                        }
                      //  System.out.println(CSI.getInstance().getMementoCountdown());
                        CSI.getInstance().setMementoCountdown(CSI.getInstance().getMementoCountdown() - 1);
                        if (CSI.getInstance().getMementoCountdown() < 0) {
                            CSI.getInstance().setMementoCountdown(30);
                            try {
                                loadOuterFxml("splashScreen.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //mementoTimer.cancel();
                        }
                        CSI.getInstance().setMementoCountdown(CSI.getInstance().getMementoCountdown());
                    }
                });
            }
        };
        CSI.getInstance().setMementoTimer(new Timer(), task);
    }

//    public void getWeatherData() throws APIException {
//        // declaring object of "OWM" class
//        OWM owm = new OWM("a50ad26122793d64d9911f26cdb749c4");
//
//        // getting current weather data for the "Boston" city
//        CurrentWeather cwd = owm.currentWeatherByCityName("Boston");
//
//        //printing city name from the retrieved data
//        double fMin = ((((cwd.getMainData().getTempMin())-273.0)*(9/5))+32.0);
//        double fMax = ((((cwd.getMainData().getTempMax())-273.0)*(9/5))+32.0);
//        String tempText = String.format("Heart of %s\nCurrent temperature: %.2f F \nToday's high: %.2f F",cwd.getCityName(),fMin,fMax);
//        lblTemp.setText(tempText);
//    }

//    public void getMBTAData(){
//        Mbta mbta = new Mbta();
//        // TODO fix so that it doesn't spam errors if our api key runs out
//        //ArrayList<String> stringList = mbta.getPredictionsBrigham();
//        ArrayList<String> stringList = new ArrayList<>();
//
//        String outputString = "";
//        int counter = 0;
//        for(String s: stringList){
//            if(counter > 4){
//
//            }
//            else if (counter < 4){
//                outputString = outputString + s +"\n";
//                counter++;
//            }
//        }
//        if(counter == 0){
//            lblTrain.setText("Trains Are Not Currently Running");
//        }
//        else{
//        lblTrain.setText(outputString);
//        }
//    }

    @FXML
    public void buttonClick(ActionEvent event) throws IOException {

        if (event.getSource() == callEmergencyButton) {
            if (!CSI.getInstance().getPermissions().isEmpty() || CSI.getInstance().getCurUser() != null) {
                System.out.println("SOMETHING WENT REALLY WRONG WITH PERMISSIONS WHEN LOADING GUEST");
                System.out.println("SOMETHING WENT REALLY WRONG WITH PERMISSIONS WHEN LOADING GUEST");
                System.out.println("SOMETHING WENT REALLY WRONG WITH PERMISSIONS WHEN LOADING GUEST");
            }
//            loadOuterFxml("emergencyRequestPage.fxml");
            Emergency.setSender("Bloodorangebishopfish");
            Emergency.setSenderPassword("Software1");
            Emergency.setRecipient("Bloodorangebishopfish@gmail.com");

            Emergency e = new Emergency();
            try {
                e.run(50,50, 900, 1300, "bishopfish/curie.css", "destNode", "originNode");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (!hasValidInput(event)) {
           return;
        }
        if (event.getSource() == loginBtn || event.getSource() == passwordTf || event.getSource() == usernameTf) {
            System.out.println(EncryptPassword.generateSecurePassword(passwordTf.getText(),EncryptPassword.getSalt(usernameTf.getText())));

            String username = usernameTf.getText();
            String password = passwordTf.getText();

            if (EncryptPassword.verifyEmployee(username, password)) {
                CSI.getInstance().setCurUser(username);
                String permissions = DBCustom.getPermissions(CSI.getInstance().getCurUser());
//                if (!permissions.isEmpty()) {
                    CSI.getInstance().setPermissions(permissions);
                    CSI.getInstance().getMementoTimer().cancel();
                    loadOuterFxml("homePage.fxml");
                    //loadNewScene(actionEvent, "homePage.fxml");
//                }
           }
           else {
               showAlert(Alert.AlertType.ERROR, usernameTf.getScene().getWindow(), "Password Error!", "Please enter a correct username and password!");

           }
        }
    }



    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }
}
