package bishopfish.map;

import bishopfish.map.GraphObject;
import bishopfish.map.NodeEntry;
import javafx.scene.shape.Circle;
import org.jetbrains.annotations.NotNull;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.abs;

public class Node extends GraphObject implements Comparable {
    //this idName
    private String name;
    private Double distance = Double.MAX_VALUE;
    private Point coord;
    private Double heuristic;
    private Node previous = null;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;
    private Circle circle;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, int x, int y) {
        this(name);
        coord = new Point(x, y);

    }

    public Node(String name, int x, int y, String floor, String building, String nodeType, String longName, String shortName) {
        this.name = name;
        this.coord = new Point(x, y);
        this.distance = Double.MAX_VALUE;
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;
    }

    public Node(NodeEntry e) {
        this.name = e.getId();
        coord = new Point(Integer.parseInt(e.getxCoord()), Integer.parseInt(e.getyCoord()));
        this.floor = e.getFloor();
        this.distance = Double.MAX_VALUE;

        //added nodeType & longName
        this.building = e.getBuilding();
        this.nodeType = e.getNodeType();
        this.shortName = e.getShortName();
        this.longName = e.getLongName();
        this.shortName = e.getShortName();
        this.building = e.getBuilding();
    }

//    public NodeEntry getEntry() {
//        return new NodeEntry(name, ((Integer) coord.x).toString(), ((Integer) coord.x).toString(), floor, building, nodeType, longName, shortName);
//    }


    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getXCoord() {
        return this.coord.x;
    }

    public int getYCoord() {
        return this.coord.y;
    }

    @Override
    public String toString() {
        return shortName;
    }

    public Double getDistance() {
        return distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public String getName() {
        return name;
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }

    public void setPrevious(Node n) {
        previous = n;
    }

    public void setDistance(Double d) {
        distance = d;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (((Node) o).getName() == null) return false;
        if (name == null) return false;
        return name.equals(((Node) o).getName());
    }

    public void setHeuristic(Node destination) {
        this.heuristic = Node.distanceFrom(this, destination);
    }



    public static Double distanceFrom(Node n1, Node n2) {
        return abs(Math.sqrt((n1.coord.x - n2.coord.x) * (n1.coord.x - n2.coord.x) + (n1.coord.y - n2.coord.y) * (n1.coord.y - n2.coord.y)));
        //added abs for absolute value
    }

    public static void printPath(Node destination) {
        System.out.println("Total distance traveled: " + destination.getDistance());
        Node current = destination;
        Stack path = new Stack();
        path.push(destination);

        //puts all nodes of the path into the stack
        while (current.getPrevious() != null) {
            current = current.getPrevious();
            path.push(current);
        }
        // inverses the stack

        int i = 0;
        do {
            System.out.println(++i + ":" + path.pop());
        }
        while (!path.isEmpty());
    }
    public static ArrayList<Node> getPath(Node destination) {
        //System.out.println("Total distance traveled: " + destination.getDistance());
        Node current = destination;
        Stack path = new Stack();
        path.push(destination);
        ArrayList<Node> nodePath = new ArrayList<Node>();

        //puts all nodes of the path into the stack
        while (current.getPrevious() != null) {
            current = current.getPrevious();
            path.push(current);
        }
        // inverses the stack

        int i = 0;
        do {
            nodePath.add((Node)path.pop());
        }
        while (!path.isEmpty());
        return nodePath;
    }
    @Override
    public int compareTo(Object o) {
        return ((Double)(heuristic + distance)).compareTo((((Node)o).heuristic + ((Node)o).distance));
    }

    public Point getCoord() {
        return coord;
    }

    public String getBuilding() {
        return building;
    }

    public String getShortName() {
        return shortName;
    }
}