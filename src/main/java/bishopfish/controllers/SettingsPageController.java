package bishopfish.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPageController extends Controller implements Initializable {

    @FXML
    ComboBox<String> algorithmBox;

    @FXML
    private ObservableList<String> algorithmList = FXCollections.observableArrayList("AStar", "BFS", "DFS", "Dijkstra's");

    //this may not be right
    @Override
    protected boolean hasValidInput(ActionEvent e) {
        showAlert(Alert.AlertType.ERROR, algorithmBox.getScene().getWindow(), "Form Error!", "Please make a selection.");
        if(algorithmBox.getValue().equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        algorithmBox.setItems(algorithmList);
    }

    public void submitButton(ActionEvent event) throws IOException{
        String pathfindingChoice = algorithmBox.getValue();
        CSI.getInstance().getHpController().setDirectionsFlag(pathfindingChoice);
        goHome();
    }
}
