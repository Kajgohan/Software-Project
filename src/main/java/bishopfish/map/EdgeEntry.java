package bishopfish.map;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class EdgeEntry extends DBEntry {
    // id set by DBEntry (edgeId)
    private String startNode;
    private String endNode;

    public EdgeEntry(String edgeId, String startNode, String endNode) {
        this(new ArrayList<String>() {{ add(edgeId); add(startNode); add(endNode); }});
    }

    // id must be first item in array list
    public EdgeEntry(ArrayList<String> values) {
        super(values);
        this.startNode = this.values.get(1);
        this.endNode = this.values.get(2);
    }

    public EdgeEntry getReverse() {
        return new EdgeEntry(endNode + "_" + startNode, endNode, startNode);
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

}