import bishopfish.db.DBBuffer;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.map.Node;
import bishopfish.servicerequest.GiftRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SRDBTest {
    //confirms link between gift entry and database
    @Test
    void testRequestIntegration(){
        Node testNode = new Node("Test");
        GiftRequest gr = new GiftRequest(testNode, false, "TestReq", "Test Message");
        {
            DBUpdater dbu = new DBUpdater(DBMI.ServiceRequest.value);
            dbu.dropTable();
        }
        {
            DBBuffer<ServiceRequestEntry> dbb = new DBBuffer<>(DBMI.ServiceRequest.value);
            dbb.add(gr.getServiceRequestEntry());
        }
        DBUpdater dbu = new DBUpdater(DBMI.ServiceRequest.value);
        assertEquals(dbu.getHashMap().containsValue(""), false);
    }
}
