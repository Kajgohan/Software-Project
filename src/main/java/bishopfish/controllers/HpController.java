package bishopfish.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import net.kurobako.gesturefx.GesturePane;

import java.util.ArrayList;

public interface HpController {
    // return the pane to load new pages on
    AnchorPane getSubP();
    // return the pane of the written directions
    ScrollPane getScrollPane();
    // return the pane of the aStar menu
    AnchorPane getDirectionsFxml();
    // return the fxml path that this home page controller uses
    String getFxml();
    //return the toggleButton
    ToggleButton getToggleDirections();


    // methoods needing in both for aStar
    void onClick(int i);
    GesturePane getGpane();
    Image getImage01();
    Image getImage00();
    Image getImage0();
    Image getImage1();
    Image getImage2();
    Image getImage3();
    ArrayList<Group> getFloorGroups();
    JFXTextArea getTextArea();
    Canvas getPathCanvas();


    //for both guest and home page
    void toggleTextDirections(ActionEvent event);

    //for astar/pathfinding
    public void setDirectionsFlag(String directionsFlag);
    public String getDirectionsFlag();

}
