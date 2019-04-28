package bishopfish.scheduler;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

@Deprecated
public class RoomEntry extends DBEntry {
    public RoomEntry(ArrayList<String> values) {
        super(values);
    }
//    // id set by DBEntry (roomID)
//    private String name;
//    private String nodeID;
//    private String occupancy;
//
//    public RoomEntry(String roomID, String name, String nodeID, String occupancy) {
//        this(new ArrayList<String>() {{ add(roomID); add(name); add(nodeID); add(occupancy); }});
//    }
//
//    // id must be first item in array list
//    public RoomEntry(ArrayList<String> values) {
//        super(values);
//        this.name = this.values.get(1);
//        this.nodeID = this.values.get(2);
//        this.occupancy = this.values.get(3);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getNodeID() {
//        return nodeID;
//    }
//
//    public void setNodeID(String nodeID) {
//        this.nodeID = nodeID;
//    }
//
//    public String getOccupancy() {
//        return occupancy;
//    }
//
//    public void setOccupancy(String occupancy) {
//        this.occupancy = occupancy;
//    }
////
}