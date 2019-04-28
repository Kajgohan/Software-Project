package bishopfish.servicerequest;

import bishopfish.map.Node;
import bishopfish.utils.SendText;

public class GiftRequest extends ServiceRequest {

    //Standard request format. Has ticket number object built in for queue positioning
    public GiftRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("GFTR");
    }

    @Override
    public void sendRequest() {
        SendText.sendText("301301370","Verizon", this.getRequestType(),this.getRequestMessage());

    }

    //send mail to it@bwh.org containing ticket details. Derived from standard request handler. Ticket number is randomized
    public void fulfillRequest(){

    }

}
