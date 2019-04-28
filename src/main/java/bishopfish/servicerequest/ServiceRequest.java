package bishopfish.servicerequest;

import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import javafx.event.ActionEvent;

import bishopfish.map.Node;

public abstract class ServiceRequest {
    private String requesterName;  //hold the string of the type of request
    private String requestTypeFid;  //hold the string of the type of request
    private String requestMessage;  //hold the string description or message of the request
    private boolean isFulfilled; //see if the request is fulfilled or not
    private Node locationOfRequest; //location of the kiosk for the service request
    private String reciverNumber = "301301370";
    private int ticketNumber;

    //constructor
    public ServiceRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        this.locationOfRequest = locationOfRequest;
        this.isFulfilled = isFulfilled;
        this.requesterName = requesterName;
        this.requestMessage = requestMessage;
        this.ticketNumber = createTicketNumber();
    }

    public ServiceRequest(ServiceRequestEntry requestEntry){
        this.locationOfRequest = new Node(DBCustom.getNodeWithId(requestEntry.getNodeId()));
        this.isFulfilled = requestEntry.getIsFulfilled().equals("true");
        this.requesterName = requestEntry.getRequesterName();
        this.requestMessage = requestEntry.getTicketContent();
        this.ticketNumber = Integer.parseInt(requestEntry.getId());
    }

    public ServiceRequestEntry getServiceRequestEntry() {
        return new ServiceRequestEntry(ticketNumber, requesterName, requestTypeFid, requestMessage, isFulfilled, locationOfRequest);
    }


    //create a random ticket for the request
    int createTicketNumber() {
        return (int) (Math.random() * 1000.0);
    }

    public Node getLocationOfRequest() {
        return locationOfRequest;
    }

    public void setLocationOfRequest(Node locationOfRequest) {
        this.locationOfRequest = locationOfRequest;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequestType() {
        return requestTypeFid;
    }

    public void setRequestType(String requestType) {
        this.requestTypeFid = requestType;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getReciverNumber() {
        return reciverNumber;
    }

    public void setReciverNumber(String reciverNumber) {
        this.reciverNumber = reciverNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public abstract void sendRequest();

    public abstract void fulfillRequest();

}