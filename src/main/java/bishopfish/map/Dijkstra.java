package bishopfish.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Dijkstra implements IPathfind{
    Graph graph;
    ArrayList<Edge> edges;
    //graph supplies the list of edges

    //changed from Set to Set<Node>
    Set<Node> unsearched;
    Set searched;

    Node sourceNode;
    Node destinationNode;

    public Dijkstra(Graph graph){
        this.graph = graph;
        this.edges = graph.getEdges();
    }

    public Node getClosest(){
        Node closest = null;
        for(Node n: unsearched){
            if(closest == null){
                closest = n;
            }
            else{
                if(n.getDistance() < closest.getDistance()){
                    closest = n;
                }
            }
        }
        return closest;
    }

    //this is repeat code, reorganize it
    public Double getDistanceFrom(Node start, Node end){
        for(Edge e: edges){
            if(e.getStart().equals(start) && e.getEnd().equals(end)){
                return e.getLength();
            }
        }
        return null;
    }

    public ArrayList<Node> getNeighbors(Node n){
        ArrayList<Node> neighbors = new ArrayList<>();
        for(Edge e: edges){
            if(e.getStart().equals(n)){
                neighbors.add(e.getEnd());
            }
        }
        return neighbors;
    }

    public void updateNeighborDistances(Node current){
        ArrayList<Node> neighbors = getNeighbors(current);
        Double distance = current.getDistance();
        for(Node n: neighbors){
            //update distances for nodes that neighbor current
            double temp = getDistanceFrom(current, n);
            if(distance + temp < getDistanceFrom(current, n)){
                n.setPrevious(current);
                n.setDistance(distance + temp);
                unsearched.add(n);
            }
        }
    }

    public void run(Graph graph, Node sourceNode, Node destinationNode) throws PathNotFoundException {
        unsearched = new HashSet();
        searched = new HashSet();

        Node current = sourceNode;
        sourceNode.setDistance(0.0);
        unsearched.add(sourceNode);
        while(!unsearched.isEmpty()){
            //set current to the node with the least distance to start.
            current = getClosest();
            searched.add(current);
            unsearched.remove(current);
            updateNeighborDistances(current);
        }
        if(searched.size() == 0){
            throw new PathNotFoundException(sourceNode, destinationNode);
        }
    }

    //mostly for testing purposes
    public void printPath(Node destination){
        System.out.println("Total distance traveled: " + destination.getDistance());
        Node current = destination;
        Stack path = new Stack();
        path.push(destination);

        //enqueue all path nodes to a stack (so we can easily print in reverse order
        while(current.getPrevious() != null){
            current = current.getPrevious();
            path.push(current);
        }

        //print in correct order
        System.out.println(path.pop());
        while(!path.isEmpty());
    }

}
