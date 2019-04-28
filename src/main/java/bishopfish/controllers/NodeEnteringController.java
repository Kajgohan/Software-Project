package bishopfish.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NodeEnteringController extends Controller implements Initializable {

    @FXML
    JFXTextField xCoordinate;

    @FXML
    JFXTextField yCoordinate;

    @FXML
    JFXTextField floorField;

    @FXML
    JFXTextField buildingField;

    @FXML
    JFXTextField nodeType;

    @FXML
    JFXTextField longName;

    @FXML
    JFXTextField shortName;

    @FXML
    JFXButton editNode;




    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClick(ActionEvent event) throws IOException{

    }
}
