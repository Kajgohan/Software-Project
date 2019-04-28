package bishopfish.scheduler;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class BookingEntry extends DBEntry {
    // id set by DBEntry (bookingID)
    private String roomID;
    private String startDate;
    private String endDate;
    private String reserver;


    // id must be first item in array list
    public BookingEntry(String bookingID, String roomID, String startDate, String endDate, String reserver) {
        this(new ArrayList<String>() {{ add(bookingID); add(roomID); add(startDate); add(endDate); add(reserver); }});
    }


    public BookingEntry(ArrayList<String> values) {
        super(values);
        this.roomID = this.values.get(1);
        this.startDate = this.values.get(2);
        this.endDate = this.values.get(3);
        this.reserver = this.values.get(4);
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReserver() {
        return reserver;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    public boolean isHappening(long currentTime){
        if(currentTime >= Long.parseLong(this.startDate) && currentTime <= Long.parseLong(this.endDate)){
            return true;
        }else {
            return false;
        }
    }

}
