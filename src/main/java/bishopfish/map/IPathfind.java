package bishopfish.map;

public interface IPathfind {
    void run(Graph graph, Node start, Node end) throws PathNotFoundException;
}
