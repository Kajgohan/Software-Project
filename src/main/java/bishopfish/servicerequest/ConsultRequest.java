package bishopfish.servicerequest;
import bishopfish.employees.Employee;
import bishopfish.employees.EmployeeEntry;
import bishopfish.map.Node;
import bishopfish.utils.SendText;

import java.util.ArrayList;

public class ConsultRequest extends ServiceRequest {

    EmployeeEntry recipient;
    String phone[];

    public ConsultRequest(Node locationOfRequest, boolean isFulfilled, String requesterName, String requestMessage, EmployeeEntry recipient) {
        super(locationOfRequest, isFulfilled, requesterName, requestMessage);
        this.recipient = recipient;
        this.phone = recipient.getPhone().split(":");
        this.setReciverNumber(this.phone[0]);
        this.setRequestType("CNSR");
        System.out.println(String.format("Phone: %s Carrier %s",this.phone[0],this.phone[1]));
    }

    @Override
    public void sendRequest() {
        SendText.sendText(getReciverNumber(), this.phone[1], this.getRequestType(), this.getRequestMessage());
    }

    @Override
    public void fulfillRequest() {
        System.out.println(this.getRequestType());
    }


}
