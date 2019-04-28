package bishopfish.controllers;

import bishopfish.mbta.Mbta;
import bishopfish.utils.RSSFeed;
import com.rometools.rome.io.FeedException;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class SplashScreenController extends Controller{

    public Label IBITemp;
    public Label IBITrain;
    public TranslateTransition transition;
    public AnchorPane anchorPane;
    public Label news;

    @FXML
    public void initialize() throws IOException, FeedException {
        String newsString = "";
        try {
            RSSFeed R = new RSSFeed();
            newsString = R.rssOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        news.setText(newsString);

        try {
            getWeatherData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getMBTAData();

        Timer updater = new Timer();
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        getMBTAData();
                        try {
                            getWeatherData();
                        } catch (APIException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        updater.scheduleAtFixedRate(update,10000,10000);
//transition = and set on finish in here

        Task marqueeTask = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    transition = TranslateTransitionBuilder.create()
                            .duration(new Duration(40))
                            .node(news)
                            .interpolator(Interpolator.LINEAR)
                            .cycleCount(1)
                            .build();

                    transition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            rerunAnimation();
                        }
                    });

                    rerunAnimation();


                    transition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            rerunAnimation();
                        }
                    });

                    rerunAnimation();
                }
                catch (Exception e){
//                    LOGGER.log(Level.SEVERE, "Marquee failed", e);
                }
                return null;
            }
        };

        Thread marquee = new Thread(marqueeTask);
        //System.out.println("Marquee starting");
        marquee.start();
        try {
            CSI.getInstance().getMementoTimer().cancel();
        } catch (NullPointerException e) {
            //do nothing, timer doesn't exist
        }
    }

    public void getWeatherData() throws APIException {
        // declaring object of "OWM" class
        OWM owm = new OWM("a50ad26122793d64d9911f26cdb749c4");

        // getting current weather data for the "Boston" city
        CurrentWeather cwd = owm.currentWeatherByCityName("Boston");

        //printing city name from the retrieved data
        double fMin = ((((cwd.getMainData().getTempMin())-273.0)*(9/5))+47.0);
        double fMax = ((((cwd.getMainData().getTempMax())-273.0)*(9/5))+47.0);
        String tempText = String.format("Heart of %s\nCurrent temperature: %.2f F \nToday's high: %.2f F",cwd.getCityName(),fMin,fMax);
        IBITemp.setText(tempText);
    }

    public void getMBTAData(){
        Mbta mbta = new Mbta();
        ArrayList<String> stringList = new ArrayList<String>(mbta.getPredictionsBrigham());
        String outputString = "Brigham Circle:\n";
        int counter = 0;
        for(String s: stringList){
            if(counter > 4){

            }
            else if (counter < 4){
                outputString = outputString + s +"\n";
                counter++;
            }
        }
        if(counter == 0){
            IBITrain.setText("Trains Are Not Currently Running");
        }
        else{
            IBITrain.setText(outputString);
        }
    }

    @FXML
    public void loadWelcomeScreen(ActionEvent e, String backPath) throws IOException {
        Object[] rootAndController = loadNewBack("welcomeScreen.fxml", backPath);
        Parent root = (Parent) rootAndController[0];
        WelcomeScreenController controller = (WelcomeScreenController) rootAndController[1];
        //controller.setUsername(username);
//        passPermissions(controller);
        loadWindow(e, root);
        //this.setScene(scenePath);
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }
    public void loadWelcome(MouseEvent mouseEvent) throws IOException {
        //loadNewScenePass(mouseEvent, "welcomeScreen.fxml", "");
        loadOuterFxml("welcomeScreen.fxml");
    }

    private void rerunAnimation() {
        transition.stop();
        // if needed set different text on "node"
        recalculateTransition();
        transition.playFromStart();
    }

    private void recalculateTransition() {
        transition.setToX(news.getBoundsInLocal().getMaxX() * -1 - 100);
        transition.setFromX(anchorPane.widthProperty().get() + 100);

        double distance = anchorPane.widthProperty().get() + 2 * news.getBoundsInLocal().getMaxX();
        transition.setDuration(new Duration(80000));
      //  System.out.println("Animated!");
    }
}
