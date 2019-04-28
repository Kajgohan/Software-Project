package bishopfish.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MasterController extends Controller implements Initializable {

    @FXML
    public AnchorPane masterPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CSI.getInstance().setMasterController(this);
        try {
            loadOuterFxml("splashScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    public AnchorPane getMasterPane() {
        return masterPane;
    }

//    public String getFxml() {
//        return "master.fxml";
//    }

}
