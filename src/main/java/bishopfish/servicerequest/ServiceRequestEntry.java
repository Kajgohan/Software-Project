package bishopfish.servicerequest;

import bishopfish.db.DBEntry;
import bishopfish.map.Node;

import java.util.ArrayList;

public class ServiceRequestEntry extends DBEntry {
    // id set by DBEntry (ticketNum)
    private String requesterName;
    private String requestTypeFid;
    private String ticketContent;
    private String isFulfilled;
    private String nodeId;    // destination


    public ServiceRequestEntry(Integer ticketNum, String name, String requestType, String ticketContent, Boolean isFullfilled, Node node) {
        this(new ArrayList<String>() {{ add(ticketNum.toString()); add(name); add(requestType); add(ticketContent); add(isFullfilled.toString()); add(node.getName()); }});
    }

    // id must be first item in array list
    public ServiceRequestEntry(ArrayList<String> values) {
        super(values);
        this.requesterName = this.values.get(1);
        this.requestTypeFid = this.values.get(2);
        this.ticketContent = this.values.get(3);
        this.isFulfilled = this.values.get(4);
        this.nodeId = this.values.get(5);
    }

    public String getName() {
        return requesterName;
    }

    public void setName(String name) {
        this.requesterName = name;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequestTypeFid() {
        return requestTypeFid;
    }

    public void setRequestTypeFid(String requestTypeFid) {
        this.requestTypeFid = requestTypeFid;
    }

    public String getTicketContent() {
        return ticketContent;
    }

    public void setTicketContent(String ticketContent) {
        this.ticketContent = ticketContent;
    }

    public String getIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(String isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
