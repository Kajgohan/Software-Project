package bishopfish.servicerequest;
import bishopfish.map.Node;
import bishopfish.utils.SendText;

import java.awt.*;

public class SecurityRequest extends ServiceRequest {

    public SecurityRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("SECR");
        sendRequest();
    }

    public void sendRequest() {
        SendText.sendText(this.getReciverNumber(), "Verizon", this.getRequestType(), this.getRequestMessage());
    }

    public void fulfillRequest() {
        this.setFulfilled(true);
    }


}
