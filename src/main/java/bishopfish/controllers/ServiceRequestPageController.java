package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.servicerequest.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ServiceRequestPageController extends Controller implements Initializable {
    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
