package bishopfish.map;

import java.util.HashMap;

public class Edge extends GraphObject {
    private String name;
    private double length;
    private Node start,end;

    public Edge(String name, Node start, Node end, double length){
        this.name = name;
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public Edge(EdgeEntry e, HashMap<String, Node> nodesHM){
        this.name = e.getId();
        this.start = nodesHM.get(e.getStartNode());
        this.end = nodesHM.get(e.getEndNode());
        if (start == null || end == null) {
            System.out.println("For Edge " + e.getId());
            System.out.println("Start: " + start);
            System.out.println("End: " + end);
        }
            this.length = Node.distanceFrom(start, end);

    }

//    public void setLength(Node start, Node end) {
//        double result;
//
//
//
//        this.length = length;
//    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getName(){
        return  name;
    }

    public double getLength(){
        return length;
    }

    public Node getStart(){
        return start;

    }

    public Node getEnd(){
        return end;
    }

    public boolean equals(Edge e){
        return  e.getName().equals(name);
    }

}
