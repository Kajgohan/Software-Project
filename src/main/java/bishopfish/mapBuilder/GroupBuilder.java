package bishopfish.mapBuilder;

import bishopfish.map.Edge;
import bishopfish.map.Graph;
import bishopfish.map.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class GroupBuilder {

    public ArrayList<String> floorSequence;
    public ArrayList<Group> allGroups;
    public ArrayList<Node> listNodes1;
    public Boolean edges;

    public GroupBuilder(ArrayList<Group> allGroups, ArrayList<Node> listNodes1){
       this.allGroups = allGroups;
       this.listNodes1 = listNodes1;
       floorSequence = new ArrayList<>();
    }

    public void drawMapForViewOnly(){
        int counter = 0;
        while(counter < listNodes1.size()){
            counter = floorHandler(counter);
        }
    }

    private int groupIndex(String floor){
        if (floor.equals("3")){
            return 0;
        }
        else if(floor.equals("2")){
            return 1;
        }
        else if(floor.equals("1")){
            return 2;
        }
        else if(floor.equals("G")){
            return 3;
        }
        else if(floor.equals("L1")){
            return 4;
        }
        else{
            return 5;
        }
    }

    private Integer floorHandler(Integer count){
        Path path = new Path();
        String firstFloor = listNodes1.get(count).getFloor();
        MoveTo moveTo = new MoveTo((float) listNodes1.get(count).getXCoord()/5, (float) listNodes1.get(count).getYCoord()/5);
        path.getElements().add(moveTo);
        Circle circleStart = makeCircle(listNodes1.get(count).getXCoord()/5,listNodes1.get(count).getYCoord()/5, true);
        allGroups.get(groupIndex(listNodes1.get(count).getFloor())).getChildren().addAll(circleStart);
        floorSequence.add(listNodes1.get(count).getFloor());
        count+=1;

        //editing here
        String floor = listNodes1.get(count).getFloor();
        if(floor == "4"){
            floor = "2";
        }

        //while(count < listNodes1.size() && floor.equals(firstFloor))
        //while(count < listNodes1.size() && listNodes1.get(count).getFloor().equals(firstFloor))
        while(count < listNodes1.size() && floor.equals(firstFloor)) {
            System.out.println("Called");
            Circle circle = makeCircle(listNodes1.get(count).getXCoord()/5,listNodes1.get(count).getYCoord()/5, false);

            allGroups.get(groupIndex(floor)).getChildren().addAll(circle);
            //allGroups.get(groupIndex(floor)).getChildren().addAll(circle);
            LineTo lineTo = new LineTo(listNodes1.get(count).getXCoord()/5, listNodes1.get(count).getYCoord()/5);
            path.getElements().add(lineTo);
            count+=1;
        }
        allGroups.get(groupIndex(firstFloor)).getChildren().addAll(path);
        return count;
    }

    private Circle makeCircle(int xCord, int yCord, Boolean start){
        Circle circle = new Circle(2);
        circle.setCenterX(xCord);
        circle.setCenterY(yCord);
        if(start) {
            circle.setFill(Color.RED);
        }
        return circle;
    }

}
