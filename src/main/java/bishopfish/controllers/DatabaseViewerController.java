package bishopfish.controllers;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBEntry;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.scheduler.Booking;
import bishopfish.servicerequest.ServiceRequestEntry;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Deprecated
public class DatabaseViewerController extends Controller implements Initializable {
    @FXML
    private Button rspHome;
    @FXML
    private Label pageTitle;
    @FXML
    private TableView table = new TableView();

    DBMI.DBInfo databaseSelected;

    public DBMI.DBInfo getDatabaseSelect() {
        return databaseSelected;
    }


    private TableColumn<DBEntry, String> newSColumn(String colName, final int colNum) {
        TableColumn<DBEntry, String> column = new TableColumn<>(colName);
        column.setId(colName);
        column.setPrefWidth(150);
        //column.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getValues().get(colNum)));
        column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValues().get(colNum)));
        //column.setCellValueFactory(data -> new ReadOnlyStringWrapper("hi"));
        //column.setCellFactory(factory -> new TableCell<>());
        //this stuff below works!!!
//        column.setEditable(true);
//        column.setCellFactory(TextFieldTableCell.forTableColumn());
//        column.setOnEditCommit(
//            new EventHandler<TableColumn.CellEditEvent<DBEntry, String>>() {
//                @Override public void handle(TableColumn.CellEditEvent<DBEntry, String> t) {
//                    ((DBEntry)t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())).getValues().set(colNum, t.getNewValue());
//                }
//        });


        return column;
    }


    public static LocalDateTime millsToLocalDateTime(String millis) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(millis) * 1000 );
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

    private TableColumn<DBEntry, String> newTColumn(String colName, final int colNum) {
        TableColumn<DBEntry, String> column = new TableColumn<>(colName);
        column.setId(colName);
        column.setPrefWidth(150);
        //column.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getValues().get(colNum)));
        column.setCellValueFactory(data -> new SimpleStringProperty( millsToLocalDateTime( data.getValue().getValues().get(colNum) ).toString() ));



        return column;
    }




    /*class CB extends CheckBoxTableCell<DBEntry, Boolean> {
        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
    }*/


    private TableColumn<DBEntry, Boolean> newBColumn(String colName, final int colNum) {
        TableColumn<DBEntry, Boolean> column = new TableColumn<>(colName);
        column.setId(colName);
        column.setPrefWidth(150);
        column.setEditable(true);
        column.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getValues().get(colNum).equals("true")));
        DBBuffer<DBEntry> dbb = new DBBuffer<>(databaseSelected);
//        column.setCellFactory(new Callback<TableColumn<DBEntry, Boolean>, TableCell<DBEntry, Boolean>>() {
//            @Override
//            public TableCell<DBEntry, Boolean> call(TableColumn<DBEntry, Boolean> p) {
//                CheckBoxTableCell cb = new CheckBoxTableCell<>();
//                cb.focusedProperty().addListener(new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                        System.out.println("12312hiii");
////                        if (newValue) {
////                            getTableView().edit(getIndex(), getTableColumn());
////                        } else {
////                            commitEdit(textField.getText());
////                        }
//                    }
//                });
//                cb.startEdit();
//                return cb;
//            }
//        });
        column.setCellFactory(p -> {
            CheckBox checkBox = new CheckBox();
            TableCell<DBEntry, Boolean> cell = new TableCell<DBEntry, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(item);
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



//        column.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<DBEntry, Boolean>>() {
//                    @Override public void handle(TableColumn.CellEditEvent<DBEntry, Boolean> t) {
//                        DBEntry ent = ((DBEntry)t.getTableView().getItems().get(
//                                t.getTablePosition().getRow()));
//                        ent.getValues().set(colNum, t.getNewValue().toString());
//                        System.out.println("supppp");
//                        DBBuffer<DBEntry> dbb = new DBBuffer<>(databaseSelected.getEntry(), databaseSelected);
//                        dbb.add(ent);
//                    }
//                });

        //column.setEditable(true);
        //column.setCellValueFactory(data -> new ReadOnlyStringWrapper("hi"));
        //column.setCellFactory(factory -> new TableCell<>());


        return column;
    }

//    private void updateEditability(TableCell<DBEntry, String> cell) {
//        if (cell.getTableRow() == null) {
//            cell.setEditable(false);
//        } else {
//            TableRow<DBEntry> row = (TableRow<DBEntry>) cell.getTableRow();
//            if (row.getItem() == null) {
//                cell.setEditable(false);
//            } else {
//                cell.setEditable(row.getItem().isNewRecord());
//            }
//        }
//    }

    public void setDatabaseSelect(DBMI.DBInfo databaseSelect) {
        table.setEditable(true);
        this.databaseSelected = databaseSelect;
        pageTitle.setText(databaseSelected.getTableName());
        ArrayList<String> colNames;
        System.out.println("dbinfo:" + databaseSelected);
        {
            DBUpdater dbu = new DBUpdater(databaseSelected);
            colNames = dbu.getColumnNames();
        }

        //ClassLoader cl = databaseSelected.getEntry().getClassLoader();

        DBBuffer<DBEntry> main = new DBBuffer<>(databaseSelect);

        for (int i = 0; i < colNames.size(); i++) {
            if (databaseSelected.equals(DBMI.ServiceRequest.value) && i == 4) {
                System.out.println("special stuffsss");
                table.getColumns().add(newBColumn(colNames.get(i), i));
            } else if (databaseSelected.equals(DBMI.RoomBookingBookings.value) && (i == 2 || i == 3)) {
                table.getColumns().add(newTColumn(colNames.get(i), i));
            } else {
                table.getColumns().add(newSColumn(colNames.get(i), i));
            }

            //tmp.setCellValueFactory(data -> data.getValue().getValues().get(finalI));

//            tmp.setCellFactory(column -> {
//                return new TableCell<DBEntry, ArrayList<String>>() {
//                    @Override
//                    protected void updateItem(ArrayList<String> item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (item == null || item.get(i) || empty) {
//                            setText(null);
//                            setStyle("");
//                        } else {
//                            // Format date.
//                            setText(myDateFormatter.format(item));
//
//                            // Style all dates in March with a different color.
//                            if (item.getMonth() == Month.MARCH) {
//                                setTextFill(Color.CHOCOLATE);
//                                setStyle("-fx-background-color: yellow");
//                            } else {
//                                setTextFill(Color.BLACK);
//                                setStyle("");
//                            }
//                        }
//                    }
//                };
//            });

        }
//        System.out.println(colNames);
//        tmp = new TableColumn("fg");
//
//        tmp.setCellValueFactory(new PropertyValueFactory<DBEntry, String>("id"));
//        tmp.set


//        tmp.setCellValueFactory(new PropertyValueFactory<DBEntry, String>("id"));
//        ObservableList<Book> data = FXCollections.<DBEntry>observableArrayList(new Book("Test", 1));
//        data.addAll(bookList);
//        ticketDB.setItems(data);


        table.setItems(main.getBufferObservableList());
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }


    @Override
    protected boolean hasValidInput(ActionEvent e) {
        return true;
    }


/*
    class EditingCell extends TableCell<DBEntry, Double> {
        private CheckBoxTableCell cb;

        @Override
        public void startEdit() {
            super.startEdit();

            if (cb == null) {
                createTextField();
            }

            setGraphic(cb);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            //cb.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (cb != null) {
                        cb.setText(getString());
                    }
                    setGraphic(cb);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }

        private void createTextField() {
            cb = new CheckBoxTableCell(getString());
            cb.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            cb.setOnKeyPressed(new EventHandler<KeyEvent>() {

//                @Override
//                public void handle(KeyEvent t) {
//                    if (t.getCode() == KeyCode.ENTER) {
//                        commitEdit(Double.parseDouble(cb.getText()));
//                    } else if (t.getCode() == KeyCode.ESCAPE) {
//                        cancelEdit();
//                    }
//                }
            });
        }

        private Boolean getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
*/

}
