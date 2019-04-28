package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.map.NodeEntry;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

public class EditNodeController extends Controller implements Initializable {

    @FXML Button editNodeBtn;
    @FXML JFXTextField nodeIDTb;
    @FXML Button cancelBtn;
    @FXML JFXTextField xCoorTb;
    @FXML JFXTextField yCoorTb ;
    @FXML JFXTextField nodeTypeTb;
    @FXML JFXTextField floorTb;
    @FXML JFXTextField buildingTb;
    @FXML JFXTextField shortNTb;
    @FXML JFXTextField longNTb;

    static final int max1 = 10;
    static final int max2 = 4;
    static final int max3 = 4;
    static final int max4 = 2;
    static final int max5 = 10;
    static final int max6 = 4;
    static final int max7 = 48;
    static final int max8 = 45;


    DBBuffer<NodeEntry> nodeEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nodeEdit = new DBBuffer<>(DBMI.Nodes.value);
    }

    @FXML
    private void buttonClick(ActionEvent e) throws IOException {
        if (e.getSource() == editNodeBtn) {
            System.out.println("Node Edited: " + nodeIDTb.getText() + ", xCoordinate: " + xCoorTb.getText() + ", yCoordinate: " + yCoorTb.getText() + ", Node Type: " + nodeTypeTb.getText() + ", Floor: " + floorTb.getText() + ", Building:" + buildingTb.getText() + ", Short Name: " + shortNTb.getText() + ", Long Name: " + longNTb.getText());
            ArrayList<String> conUpdate = new ArrayList<>();
            String nodeIDToFind = nodeIDTb.getText();
            NodeEntry fill = nodeEdit.get(nodeIDToFind);
            if(xCoorTb.getText().equals(fill.getxCoord()) || xCoorTb.getText().isEmpty()){
                System.out.println("Here");
                conUpdate.add(null);
            }
            else {
                if(xCoorTb.getText().length()>max1){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An xCoord " + max1 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(xCoorTb.getText());
                }
            }
            if(yCoorTb.getText().equals(fill.getyCoord()) || yCoorTb.getText().isEmpty()){
                conUpdate.add(null);
            }
            else{
                if(yCoorTb.getText().length()>max2){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An yCoord " + max2 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(yCoorTb.getText());
                }
            }
            if(floorTb.getText().equals(fill.getFloor()) || floorTb.getText().isEmpty()) {
                conUpdate.add(null);
            }
            else{
                if(floorTb.getText().length()>max3){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An floor " + max3 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(floorTb.getText());
                }
            }
            if(buildingTb.getText().equals(fill.getBuilding()) || buildingTb.getText().isEmpty()){
                conUpdate.add(null);
            }
            else{
                if(buildingTb.getText().length()>max4){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An building " + max4 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(buildingTb.getText());
                }
            }
            if(nodeTypeTb.getText().equals(fill.getNodeType()) || nodeTypeTb.getText().isEmpty()){
                conUpdate.add(null);
            }
            else{
                if(nodeTypeTb.getText().length()>max5){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Node Type " + max5 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(nodeTypeTb.getText());
                }
            }
            if(longNTb.getText().equals(fill.getLongName()) || longNTb.getText().isEmpty()){
                conUpdate.add(null);
            }
            else{
                if(longNTb.getText().length()>max6){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Long Name " + max6 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(longNTb.getText());
                }
            }
            if(shortNTb.getText().equals(fill.getShortName()) || shortNTb.getText().isEmpty()){
                conUpdate.add(null);
            }
            else{
                if(shortNTb.getText().length()>max7){
                    showAlert(ERROR, ((Node) e.getSource()).getScene().getWindow(), "Input Error", "Please Select An Short Name " + max7 + " Characters or Less");
                    return;
                }
                else {
                    //System.out.println(xCoorTb.getText());
                    conUpdate.add(shortNTb.getText());
                }
            }
            nodeEdit.update(fill.getId(), conUpdate);
            showAlert(CONFIRMATION, ((Node) e.getSource()).getScene().getWindow(), "Edited Entry", "Node Has Been Edited Successfully");
        } else if (e.getSource() == cancelBtn) {
            System.out.println("cancelBtn");
            loadFxml("mapBuilder.fxml");
        }
    }

    @FXML
    private void onGetNodeClick(ActionEvent event){
        nodeEdit = new DBBuffer<>(DBMI.Nodes.value);
        String nodeIDToFind = nodeIDTb.getText();
        NodeEntry fill = nodeEdit.get(nodeIDToFind);
        xCoorTb.setText(fill.getxCoord());
        yCoorTb.setText(fill.getyCoord());
        nodeTypeTb.setText(fill.getNodeType());
        floorTb.setText(fill.getFloor());
        buildingTb.setText(fill.getBuilding());
        shortNTb.setText(fill.getShortName());
        longNTb.setText(fill.getLongName());
    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        // TODO
        return true;
    }


}
