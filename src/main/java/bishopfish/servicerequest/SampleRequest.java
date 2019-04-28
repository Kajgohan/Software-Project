package bishopfish.servicerequest;

import bishopfish.map.Node;
import bishopfish.utils.SendText;
@Deprecated
public class SampleRequest extends  ServiceRequest{
    public SampleRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("Sample Request");
    }

    @Override
    public void sendRequest() {
        SendText.sendText(getReciverNumber(), "Verizon", this.getRequestType(), this.getRequestMessage());


    }

    public void fulfillRequest(){
        System.out.println(this.getRequestType());
    }

}