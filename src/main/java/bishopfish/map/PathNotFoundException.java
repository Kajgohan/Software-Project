package bishopfish.map;

public class PathNotFoundException extends Exception {
    // an exception that will be thrown if AStar cannot find a path4
    // this should just indicate that something is SERIOUSLY wrong
    // mostly used for testing in multi-floor AStar

    //NOT IMPLEMENTED YET
    //WILL JUST THROW AN ERROR MESSAGE
    //COOL
    Node sourceNode;
    Node destinationNode;

    PathNotFoundException(Node sourceNode, Node destinationNode){
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
    }
}
