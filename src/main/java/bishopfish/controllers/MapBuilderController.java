package bishopfish.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapBuilderController extends Controller implements Initializable {
    @FXML
    private Button addNodeBtn;
    @FXML
    private Button remNodeBtn;
    @FXML
    private Button editNodeBtn;
    @FXML
    private Button addEdgeBtn;
    @FXML
    private Button remEdgeBtn;

    @FXML
    AnchorPane mapBuilderAnchor;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onHomeClick(ActionEvent event) throws IOException {
        loadFxml("homePage.fxml");
    }

    @FXML
    private void buttonClick(ActionEvent e) throws IOException {

        if (e.getSource() == addNodeBtn) {
            loadFxml("addNode.fxml");
        } else if (e.getSource() == remNodeBtn) {
            loadFxml("removeNode.fxml");
        } else if (e.getSource() == editNodeBtn) {
            loadFxml("editNode.fxml");
        } else if (e.getSource() == addEdgeBtn) {
            loadFxml("addEdge.fxml");
        } else if (e.getSource() == remEdgeBtn) {
            loadFxml("removeEdge.fxml");
        }
    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }



}
