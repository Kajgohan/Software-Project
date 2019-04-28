package bishopfish.map;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TextDirections {
    Node sourceNode;
    Node destinationNode;
    ArrayList<Node> allNodes;
    ArrayList<Edge> allEdges;


    public TextDirections(Node sourceNode, Node destinationNode,
                          ArrayList<Node> allNodes, ArrayList<Edge> allEdges){
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.allNodes = allNodes;
        this.allEdges = allEdges;
    }

    public TextDirections(){}


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

    public ArrayList<Node> getAllNodes() {
        return allNodes;
    }

    public void setAllNodes(ArrayList<Node> allNodes) {
        this.allNodes = allNodes;
    }

    public ArrayList<Edge> getAllEdges() {
        return allEdges;
    }

    public void setAllEdges(ArrayList<Edge> allEdges) {
        this.allEdges = allEdges;
    }


    /*

    public double getAngle(Node source, Node destination){
        double destinationX = (double) destination.getXCoord();
        double sourceX = (double) source.getXCoord();
        double destinationY = (double) destination.getYCoord();
        double sourceY = (double) source.getYCoord();
        double xDiff = destinationX - sourceX;
        double yDiff = destinationY - sourceY;
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        if(angle < 0){
            angle = angle + 360;
            return angle;
        }
        else{
            return angle;
        }
    }


    public String returnDirections(Node start, Node end, ArrayList<Node> nodes){
        //actually returns the directions
        String directions = new String();

        //return the path to be given directions for
        Graph graph = new Graph(nodes, allEdges);
        AStar textDirectionsAStar = new AStar(graph);
        ArrayList<Node> path = new ArrayList<>(textDirectionsAStar.getListNodes());

        //
        System.out.println("THIS IS THE PATH: ");
        System.out.println(path);

        //for G
        //[AELEV00S02, AELEV00S01, AHALL00801, AHALL00301, AHALL00201, AHALL00101, ARETL00101]
        //for FEXIT
        //[AELEV00S02, AELEV00S01, AHALL00801, AHALL00301, AHALL00201, AHALL00101, ARETL00101]

        if (path.size() < 2) {
            directions += "You have already arrived at your destination.";
        }

        else {
            Node beginningNode = path.get(0);
            directions += "Begin from " + beginningNode.getName() + ".\n";
            for (int i = 0; i < path.size() - 1; i++){
                //these nodes cannot null or we are going to get a null pointer!
                @NotNull Node firstNode = path.get(i);
                @NotNull Node secondNode = path.get(i+1);

                // used to debug
                // System.out.println(secondNode);
                // System.out.println(secondNode.getNodeType());

                // find elevator or stairs
                if(secondNode.getNodeType().equals("ELEV")){
                    directions += ("Take the elevator from floor " + firstNode.getFloor() + " to floor " + secondNode.getFloor() + ".\n");
                }
                else if(secondNode.getNodeType().equals("STAI")){
                    directions += ("Take the stairs from floor " + firstNode.getFloor() + " to floor " + secondNode.getFloor() + ".\n");
                }
                else if(secondNode.getNodeType().equals("EXIT")){
                    directions += ("Exit to " + secondNode.getLongName() + ".\n");
                }

                //if not at a flight of stairs or an elevator, must figure out if straight or turn
                //create helper fcn to change which direction is now being headed
                else{
                    //not exiting/changing floors? check angle
                    double angle = getAngle(firstNode, secondNode);
                    if(angle >= 0 && angle < 75){
                        directions += ("Turn right from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                    }
                    else if(angle >= 75 && angle < 105){
                        directions += ("Go straight from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                    }
                    else if(angle >= 105 && angle < 195){
                        directions += ("Turn left from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                    }
                    else if(angle >= 195 && angle < 330){
                        directions += ("Go straight from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                    }
                    else{
                        directions += ("Turn right from " + firstNode.getLongName() + " to " + secondNode.getLongName() + ".\n");
                    }
                }
            }
            directions += ("You have arrived at your destination: " + end.getLongName() + ".\n");
        }
        return directions;
    }
    */


}
