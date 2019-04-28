package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.EdgeEntry;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

public class AddEdgeController extends Controller {

    @FXML TextField destinationTb;
    @FXML Button addEdgeBtn;
    @FXML TextField sourceTb;
    @FXML CheckBox biDirCb;
    @FXML Button backButton;
    DBBuffer<EdgeEntry> edgeAdd = new DBBuffer<>(DBMI.Edges.value);

    static final int max2 = 10;

    @FXML
    private void buttonClick(ActionEvent e) throws IOException {
        if (e.getSource() == addEdgeBtn) {
            if (!hasValidInput(e)) {
                return;
            }
            edgeAdd.add(new EdgeEntry((destinationTb.getText() + "_" + sourceTb.getText()), sourceTb.getText(), destinationTb.getText()));
            if (biDirCb.isSelected()) {
               // System.out.println("The edge is Bi-directional");
                edgeAdd.add(new EdgeEntry(sourceTb.getText()+"_"+destinationTb.getText(), destinationTb.getText(), sourceTb.getText()));
            }
            showAlert(CONFIRMATION, ((Node) e.getSource()).getScene().getWindow(), "Added Entry", "Edge Has Been Added");

        }
        if(e.getSource() == backButton){
            loadFxml("mapBuilder.fxml");
        }
    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        if (!(sourceTb.getText().length()>=max2 && destinationTb.getText().length() >= max2)) {
            showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Enter Inputs of 10 Characters or Less");
            return false;
        }
        return true;
    }
}
