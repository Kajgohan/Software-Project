package bishopfish.servicerequest;

import bishopfish.map.Node;
import bishopfish.utils.SendText;

public class ReadingRequest extends ServiceRequest {
    public ReadingRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("REDR");
    }

    @Override
    public void sendRequest() {
        SendText.sendText(getReciverNumber(), "Verizon", this.getRequestType(), this.getRequestMessage());


    }

    public void fulfillRequest(){
        System.out.println(this.getRequestType());
    }
}
