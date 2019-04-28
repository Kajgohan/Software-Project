package bishopfish.scheduler;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class WorkstationEntry extends DBEntry {

    private String isOccupied;
    private String workstationID;
    private String location;

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    private String occupancy;
    private String name;


    public WorkstationEntry(String workstationID, String name, String location, String occupancy, String isOccupied) {
        this(new ArrayList<String>() {{ add(workstationID); add(name); add(location); add(occupancy); add(isOccupied); }});
    }

    public WorkstationEntry(ArrayList<String> values) {
        super(values);
        this.workstationID = this.values.get(0);
        this.name = this.values.get(1);
        this.location = this.values.get(2);
        this.occupancy = this.values.get(3);
        this.isOccupied = this.values.get(4);
    }

    public String getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(String isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getWorkstationID() {
        return workstationID;
    }

    public void setWorkstationID(String workstationID) {
        this.workstationID = workstationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
