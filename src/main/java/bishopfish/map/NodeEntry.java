package bishopfish.map;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class NodeEntry extends DBEntry {
    // id set by DBEntry (nodeId)
    private String xCoord;
    private String yCoord;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;

    public NodeEntry(String nodeId, String xCoord, String yCoord, String floor, String building, String nodeType, String longName, String shortName) {
        this(new ArrayList<String>() {{ add(nodeId); add(xCoord); add(yCoord); add(floor); add(building); add(nodeType); add(longName); add(shortName); }});
    }

    // id must be first item in array list
    public NodeEntry(ArrayList<String> values) {
        super(values);
        this.xCoord = this.values.get(1);
        this.yCoord = this.values.get(2);
        this.floor = this.values.get(3);
        this.building = this.values.get(4);
        this.nodeType = this.values.get(5);
        this.longName = this.values.get(6);
        this.shortName = this.values.get(7);
    }

    public String getxCoord() {
        return xCoord;
    }

    public void setxCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public String getyCoord() {
        return yCoord;
    }

    public void setyCoord(String yCoord) {
        this.yCoord = yCoord;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}