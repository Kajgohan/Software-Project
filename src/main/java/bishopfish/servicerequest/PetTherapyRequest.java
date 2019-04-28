package bishopfish.servicerequest;

import bishopfish.map.Node;

public class PetTherapyRequest extends ServiceRequest{
    public PetTherapyRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.setRequestType("PETR");
    }



    @Override
    public void sendRequest() {

    }

    @Override
    public void fulfillRequest() {

    }
}
