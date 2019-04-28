package bishopfish.servicerequest;

import bishopfish.utils.SendText;
import bishopfish.map.Node;

public class ReligousRequest extends ServiceRequest{

    public ReligousRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("RLGR");
    }

    @Override
    public void sendRequest() {
        SendText.sendText(getReciverNumber(), "Verizon", this.getRequestType(), this.getRequestMessage());

    }

    public void fulfillRequest(){
        System.out.println(this.getRequestType());
    }

}
