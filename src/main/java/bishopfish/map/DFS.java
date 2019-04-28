package bishopfish.map;

import java.util.ArrayList;
import java.util.List;

public class DFS implements IPathfind {
    Graph graph;
    Node sourceNode;
    Node destinationNode;

    public DFS(Graph graph, Node sourceNode, Node destinationNode){
        this.graph = graph;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(Node sourceNode) {
        this.sourceNode = sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    //must have the same Run parameters as any other pathfinding class
    public void run(Graph graph, Node sourceNode, Node destinationNode) throws PathNotFoundException{
        //prints a list of the nodes that are returned by the DFS
        System.out.print(sourceNode.getName() + " ");
        ArrayList<Node> visited = new ArrayList<Node>();
        visited.add(sourceNode);
        List<Node> nodeNeighbors = graph.getNeighbors((ArrayList<Edge>) graph.getEdges(), sourceNode);
        for(int i = 0; i < nodeNeighbors.size(); i++){
            Node current = nodeNeighbors.get(i);
            if(current != null && !(visited.contains(current))){
                //should work now
                run(graph, current, destinationNode);
                //figure out why this is erroring
            }
        }
        if (visited.size() == 0) {
            throw new PathNotFoundException(sourceNode, destinationNode);
        }
    }

}
