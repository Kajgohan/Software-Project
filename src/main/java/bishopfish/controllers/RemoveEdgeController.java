package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.EdgeEntry;
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

public class RemoveEdgeController extends Controller implements Initializable {

    @FXML Button remEdgeBtn;
    @FXML TextField edgeIDTb;
    @FXML Button cancelBtn;

    static final int ret = 21;

    DBBuffer<EdgeEntry> edgeRemove = new DBBuffer<>(DBMI.Edges.value);


    @FXML
    private void buttonClick(ActionEvent e) throws IOException {
        if (e.getSource() == remEdgeBtn) {
            if (hasValidInput(e)) {
                return;
            }
            edgeRemove.remove(edgeIDTb.getText());
            showAlert(CONFIRMATION, ((Node) e.getSource()).getScene().getWindow(), "Removed Entry", "Edge Has Been Removed");
        } else if (e.getSource() == cancelBtn) {
            loadFxml("mapBuilder.fxml");
        }
    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        if (edgeIDTb.getText().length() > ret) {
            showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Enter a Key Under 21 Characters");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
