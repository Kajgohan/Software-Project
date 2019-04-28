package bishopfish.servicerequest;

import bishopfish.utils.SendText;

import bishopfish.map.Node;

public class ITRequest extends ServiceRequest {

    //Standard request format. Has ticket number object built in for queue positioning
    public ITRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("ITRR");
        sendRequest();
    }

    public void sendRequest() {
        String subject = this.getRequestType() + ": " + this.getTicketNumber();
        SendMail.sendmail(subject, this.getRequestMessage(), this.getLocationOfRequest().getName(), "it@bwh.org");
    }

    public void fulfillRequest(){
        this.setFulfilled(true);
    }


}
