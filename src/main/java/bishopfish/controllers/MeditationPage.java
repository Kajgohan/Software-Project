package bishopfish.controllers;

import bishopfish.utils.FeatureTypeEntry;
import bishopfish.utils.MindfulnessPlayer;
import bishopfish.utils.NewPlayer;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MeditationPage extends Controller implements Initializable {
    @FXML
    JFXButton playButton;
    @FXML
    JFXButton stopButton;

    NewPlayer player = new NewPlayer("src\\main\\resources\\bishopfish\\FreeMindfulness3MinuteBreathing.wav");

    @Override
    protected boolean hasValidInput(javafx.event.ActionEvent e) {
        return true;
    }

    //MindfulnessPlayer M = new MindfulnessPlayer();
    //Thread th;

    public void onPlayPress(ActionEvent actionEvent) {
      // th = M.play();
        player.reset();
        System.out.println("Running audio");
        player.start();
    }

    public void onStopPress(ActionEvent actionEvent) {
        player.stop();
        System.out.println("Stopping");
        //M.stop(th);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player.setDaemon(true);
    }

//    @Override
//    public void loadFxml(String newPath, FeatureTypeEntry fent) throws IOException {
//        player.stop();
//        super.loadFxml(newPath,fent);
//    }

}
