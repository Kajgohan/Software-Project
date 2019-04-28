package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.NodeEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

public class RemoveNodeController extends Controller implements Initializable {

    @FXML TextField nodeIDTb;
    @FXML Button remNodeBtn;
    @FXML Button cancelBtn;

    static final int rem = 10;

    DBBuffer<NodeEntry> nodeRemove = new DBBuffer<>(DBMI.Nodes.value);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void buttonClick(ActionEvent e) throws IOException {
        if (e.getSource() == remNodeBtn) {
            hasValidInput(e);
            nodeRemove.remove(nodeIDTb.getText());
            showAlert(CONFIRMATION, ((Node) e.getSource()).getScene().getWindow(), "Removed Entry", "Node Has Been Removed");
        } else if (e.getSource() == cancelBtn) {
            loadFxml("mapBuilder.fxml");
        }
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        if(nodeIDTb.getText().length()> rem) {
            showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select A key 10 Characters or Less");
            return false;
        }
        return true;
    }


}
