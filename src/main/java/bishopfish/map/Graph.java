package bishopfish.map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Graph extends GraphObject {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public static ArrayList getNeighbors(ArrayList<Edge> edges, Node n){

        ArrayList<Node> neighbors = new ArrayList<>();
        for(Edge edge : edges){
            if(edge.getStart().getName().equals(n.getName())){
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }

    public static Double getDistanceFrom(ArrayList<Edge> edges, Node start, Node end){
        for (Edge e: edges){
            if (e.getStart().equals(start) && e.getEnd().equals(end)) {
                return e.getLength();
            }
        }
        return null;
    }

    //GETTERS FOR NODES OF CERTAIN TYPES
    //elevator, bathroom, exits, info, stairs, retail, service

  /*  public ArrayList<Node> listBathrooms(){
        ArrayList<Node> bathrooms = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("BATH") || n.getNodeType().equals("REST")){
                bathrooms.add(n);
            }
        }
        return bathrooms;
    }

    public ArrayList<Node> listElevators(){
        ArrayList<Node> elevators = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("ELEV")){
                elevators.add(n);
            }
        }
        return elevators;
    }

    public ArrayList<Node> listExits(){
        ArrayList<Node> exits = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("EXIT")){
                exits.add(n);
            }
        }
        return exits;
    }

    public ArrayList<Node> listInfoDesks(){
        ArrayList<Node> infoDesks = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("INFO")){
                infoDesks.add(n);
            }
        }
        return infoDesks;
    }

    public ArrayList<Node> listStairs(){
        ArrayList<Node> stairs = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("STAI")){
                stairs.add(n);
            }
        }
        return stairs;
    }

    public ArrayList<Node> listRetail(){
        ArrayList<Node> retail = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("RETL")){
                retail.add(n);
            }
        }
        return retail;
    }

    public ArrayList<Node> listServices(){
        ArrayList<Node> services = new ArrayList<Node>();
        for(Node n: this.getNodes()){
            if(n.getNodeType().equals("SERV")){
                services.add(n);
            }
        }
        return services;
    }
*/
    /*

    void BFS(Node sourceNode, Node destinationNode){
        //go through all the nodes of the given graph and add them4
        LinkedList<Node> queue = new LinkedList<Node>();
        LinkedList<Node> visited = new LinkedList<Node>();
        //to the queue to be searched
        for(int i = 0; i < this.nodes.size(); i++){
            queue.add((Node) this.nodes.get(i));
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
            else if (this.getNeighbors((ArrayList<Edge>) edges, current).contains(destinationNode)){
                visited.add(current);
                visited.add(destinationNode);
                System.out.println(visited);
                System.out.println("Path found.");
            }
            else{
                ListIterator<Node> it = this.getNeighbors((ArrayList<Edge>) edges, current).listIterator();
                //continue through the graph!
                current = it.next();
                //once current is updated, the while loop will restart
            }
        }
    }

    */


    /*
    //dfs function using a stack
    public void DFS(Node startNode){
        //prints a list of the nodes that are returned by the DFS
        System.out.print(startNode.getName() + " ");
        ArrayList<Node> visited = new ArrayList<Node>();
        visited.add(startNode);
        List<Node> nodeNeighbors = this.getNeighbors((ArrayList<Edge>) this.edges, startNode);
        for(int i = 0; i < nodeNeighbors.size(); i++){
            Node current = nodeNeighbors.get(i);
            if(current != null && !(visited.contains(current))){
                DFS(current);
            }
        }
    }

    */
}
