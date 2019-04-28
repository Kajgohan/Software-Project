package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBEntry;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.servicerequest.ServiceRequestEntry;
import bishopfish.utils.PermissionDecoderEntry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DropDownDBViewer extends Controller implements Initializable {

    @FXML
    JFXComboBox DBComboBox;

    @FXML
    TableView DBTableView;


//    @FXML
//    private  void itemMembers(ActionEvent event) throws IOException{
//
//        Node node;
//        node = (Node)FXMLLoader.load(getClass().getResource("/mapBuilder.fxml"));
//        DBAPane.getChildren().setAll(node);
//    }

    DBMI.DBInfo databaseSelected;

    private ObservableList<DBMI> DBDropDownList = FXCollections.observableArrayList();

    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return false;
    }

    public void initialize(URL location, ResourceBundle resources) {
        DBDropDownList.addAll(DBMI.values());
        Collections.sort(DBDropDownList);
        DBComboBox.setItems(DBDropDownList);
    }


    public ObservableList<ServiceRequestEntry> getPermissibleObservableList(String permissions){
//        DBBuffer dbb = new DBBuffer(DBMI.PermissionDecoder.value);
//        HashMap<String, String> decoderHm = new HashMap<>();
//        for (HashMap.Entry<String, PermissionDecoderEntry> entry : ((HashMap<String, PermissionDecoderEntry>) dbb.getBufferHashMap()).entrySet()) {
//            decoderHm.put(entry.getValue().getFeatureId(), entry.getKey());
//        }

        ObservableList<ServiceRequestEntry> observableSRList = FXCollections.observableArrayList();






//
//
//        for (HashMap.Entry<String, ServiceRequestEntry> entry : tmpMap.entrySet()) {
//
//            if ()
//                observableSRList.add(entry.getValue());
//        }
//
//
//
//        ObservableList<ServiceRequestEntry> observableSRList = FXCollections.observableArrayList();
//
//
//        if (dbu.getTableName().equals(DBMI.ServiceRequest.value.getTableName())) {
//            HashMap<String, ServiceRequestEntry> tmpMap = (HashMap<String, ServiceRequestEntry>) bufferMap;
//            observableSRList = FXCollections.observableArrayList();
//
//        }
        return observableSRList;
    }




    public void loadFxml (ActionEvent e) throws IOException {
        System.out.println(((DBMI) DBComboBox.getValue()).value);
        //Pane newLoadedPane = FXMLLoader.load(getClass().getResource("mapBuilder.fxml"));
        setDatabaseSelect(((DBMI) DBComboBox.getValue()).value);
    }

    private TableColumn<DBEntry, String> newSColumn(String colName, final int colNum) {
        TableColumn<DBEntry, String> column = new TableColumn<>(colName);
        column.setId(colName);
        column.setPrefWidth(150);
        column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValues().get(colNum)));
        return column;
    }


    private TableColumn<DBEntry, Boolean> newBColumn(String colName, final int colNum) {
        TableColumn<DBEntry, Boolean> column = new TableColumn<>(colName);
        column.setId(colName);
        column.setPrefWidth(150);
        column.setEditable(true);
        //column.setOnEditStart(data -> new SimpleBooleanProperty(data.getValue().getValues().get(colNum).equals("true")));
        column.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getValues().get(colNum).equals("true")));
        DBBuffer<DBEntry> dbb = new DBBuffer<>(databaseSelected);

        column.setCellFactory(p -> {
            CheckBox checkBox = new CheckBox();
            TableCell<DBEntry, Boolean> cell = new TableCell<DBEntry, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(item);
                        checkBox.disarm();
                        setGraphic(checkBox);
                    }
                }
            };
            checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                System.out.println("2321hsder");
                DBEntry ent = (DBEntry)cell.getTableRow().getItem();
                ent.getValues().set(colNum, isSelected.toString());

                ArrayList<String> al = new ArrayList<>();
                for (String s : ent.getValues()) {
                    al.add(s);
                }
                al.remove(0);
                dbb.update(ent.getId(), al);
            });
            cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            cell.setAlignment(Pos.CENTER);
            return cell ;
        });

        return column;
    }


    public void setDatabaseSelect(DBMI.DBInfo databaseSelect) {
        DBTableView.setEditable(true);
        DBTableView.getColumns().clear();
        DBTableView.getItems().clear();
        this.databaseSelected = databaseSelect;
        //pageTitle.setText(databaseSelected.getTableName());
        ArrayList<String> colNames;
        System.out.println("dbinfo:" + databaseSelected);
        DBUpdater dbu = new DBUpdater(databaseSelected);
        colNames = dbu.getColumnNames();
        dbu.close();

        //ClassLoader cl = databaseSelected.getEntry().getClassLoader();

        DBBuffer<DBEntry> main = new DBBuffer<>(databaseSelect);


        for (int i = 0; i < colNames.size(); i++) {
            if (databaseSelected.equals(DBMI.ServiceRequest.value) && i == 4) {
                System.out.println("special stuffsss");
                DBTableView.getColumns().add(newBColumn(colNames.get(i), i));
            } else {
                DBTableView.getColumns().add(newSColumn(colNames.get(i), i));
            }
        }
        DBTableView.setItems(main.getBufferObservableList());   // not in loop right ??? !!!
    }

}
