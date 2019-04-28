package bishopfish.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class BFS implements IPathfind{
    Graph graph;
    Node sourceNode;
    Node destinationNode;

    public BFS(Graph graph){
        this.graph = graph;
//        this.sourceNode = sourceNode;
//        this.destinationNode = destinationNode;
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


    //changed all calls to this.nodes from when it was in graph class to
    //graph.getNodes()
    //MUST be tested
    public void run(Graph graph, Node sourceNode, Node destinationNode) throws PathNotFoundException{
        //go through all the nodes of the given graph and add them4
        LinkedList<Node> queue = new LinkedList<Node>();
        LinkedList<Node> visited = new LinkedList<Node>();
        //to the queue to be searched
        for(int i = 0; i < graph.getNodes().size(); i++){
            queue.add((Node) graph.getNodes().get(i));
        }

        //handles the possibility of the start & end node being the same
        if(sourceNode.equals(destinationNode)){
            //add to list of visited, remove from overall queue
            visited.add(sourceNode);
            queue.remove(sourceNode);
            //print the root, as the program is done here
            System.out.println(sourceNode);
        }

        //this may or may not be necessary?
        visited.add(sourceNode);

        //while the queue of nodes to go through is NOT empty
        while(!queue.isEmpty()){
            //remove the next node from the queue to work w it
            Node current = queue.remove();

            // if this node is the goal node, print the path and exit
            if(current.equals(destinationNode)){
                visited.add(current);
                System.out.println(visited);
                System.out.println("Path found.");
            }
            //if the current node's adjacent neighbors contain
            //the goal node - if yes, add it to the path and end search
            else if (graph.getNeighbors((ArrayList<Edge>) graph.getEdges(), current).contains(destinationNode)){
                visited.add(current);
                visited.add(destinationNode);
                System.out.println(visited);
                System.out.println("Path found.");
            }
            else{
                ListIterator<Node> it = graph.getNeighbors((ArrayList<Edge>) graph.getEdges(), current).listIterator();
                //continue through the graph!
                current = it.next();
                //once current is updated, the while loop will restart
            }
        }
        throw new PathNotFoundException(sourceNode, destinationNode);
    }

}
