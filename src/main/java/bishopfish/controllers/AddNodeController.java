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

public class AddNodeController extends Controller implements Initializable {

    @FXML TextField nodeIDTb ;
    @FXML TextField xCoorTb;
    @FXML TextField yCoorTb ;
    @FXML TextField nodeTypeTb;
    @FXML Button addNodeBtn ;
    @FXML Button backButton;
    @FXML TextField floorTb;
    @FXML TextField buildingTb;
    @FXML TextField shortNTb;
    @FXML TextField longNTb;

    DBBuffer<NodeEntry> nodeAdd;

    static final int max1 = 10;
    static final int max2 = 4;
    static final int max3 = 4;
    static final int max4 = 2;
    static final int max5 = 10;
    static final int max6 = 4;
    static final int max7 = 48;
    static final int max8 = 45;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeAdd = new DBBuffer<>(DBMI.Nodes.value);
    }

    @FXML
    private void buttonClick(ActionEvent e) throws IOException {
        //may need to error check the inputs
        if (e.getSource() == addNodeBtn) {
            if (!hasValidInput(e)) {
                return;
            }
           nodeAdd.add(new NodeEntry(nodeIDTb.getText(), xCoorTb.getText(), yCoorTb.getText(), floorTb.getText(), buildingTb.getText(), nodeTypeTb.getText(), longNTb.getText(), shortNTb.getText()));
           showAlert(CONFIRMATION, ((Node) e.getSource()).getScene().getWindow(), "Added Entry", "Node Has Been Added");


        }
    }

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        if (nodeIDTb.getText().length()>max1 || xCoorTb.getText().length()>max2 || yCoorTb.getText().length()>max3 || floorTb.getText().length()>max4 || buildingTb.getText().length()>max5 || nodeTypeTb.getText().length()>max6 || longNTb.getText().length()>max7 || shortNTb.getText().length()>max8){
            showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Follow Character Limits");
            return false;
        }
        return true;
    }

}
