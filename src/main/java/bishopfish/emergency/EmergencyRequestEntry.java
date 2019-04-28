package bishopfish.emergency;
import bishopfish.db.DBEntry;
import java.util.ArrayList;

public class EmergencyRequestEntry extends DBEntry {

    // id set by DBEntry (bookingID)
    private String emergencyType;
    private String nodeID;
    private String emergencyTime;
    private String photoName;


    // id must be first item in array list
    public EmergencyRequestEntry(String id, String emergencyType, String nodeID, String emergencyTime, String photoName) {
        this(new ArrayList<String>() {{ add(id); add(emergencyType); add(nodeID); add(emergencyTime); add(photoName); }});
    }

    public EmergencyRequestEntry(ArrayList<String> values) {
        super(values);
        this.emergencyType = this.values.get(1);
        this.nodeID = this.values.get(2);
        this.emergencyTime = this.values.get(3);
        this.photoName = this.values.get(4);
    }
    public void sendRequest() {

    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getEmergencyTime() {
        return emergencyTime;
    }

    public void setEmergencyTime(String emergencyTime) {
        this.emergencyTime = emergencyTime;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
