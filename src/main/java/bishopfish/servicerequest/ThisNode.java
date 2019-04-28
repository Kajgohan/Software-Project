package bishopfish.servicerequest;

import java.awt.Point;

public class ThisNode {
    //create a specific node for the local kiosk
    Point thisPoint;
    Node thisKiosk;

    //dummy node class to be removed when real node implemented
    public class Node {
        String name;
        double distance;
        Point coord;

        public Node(String name, double distance, Point coord) {
            this.name = name;
            this.distance = distance;
            this.coord = coord;
        }

        public String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        double getDistance() {
            return distance;
        }

        void setDistance(double distance) {
            this.distance = distance;
        }

        Point getCoord() {
            return coord;
        }

        void setCoord(Point coord) {
            this.coord = coord;
        }
    }

    //constructor
    public ThisNode() {
        this.thisPoint = new Point();
        this.thisKiosk = new Node("Local", 0.0, thisPoint);
    }
    //non-test constructor that allows attaching points and nodes
    public ThisNode(Point p, Node n) {
        this.thisPoint = p;
        this.thisKiosk = n;
    }

    //get the node within the bishopfish.servicerequest.ThisNode
    public Node getNode(){
        return thisKiosk;
    }
}
