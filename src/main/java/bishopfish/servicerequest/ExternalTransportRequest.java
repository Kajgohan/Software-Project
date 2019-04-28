package bishopfish.servicerequest;

import bishopfish.map.Node;
import bishopfish.utils.SendText;

public class ExternalTransportRequest extends ServiceRequest {


    public ExternalTransportRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("TRNR");
    }

    public void sendRequest() {
        SendText.sendText(this.getReciverNumber(), "Verizon", this.getRequestType(), this.getRequestMessage());
    }

    public void fulfillRequest() {
        this.setFulfilled(true);
    }
}

