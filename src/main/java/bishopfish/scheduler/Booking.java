package bishopfish.scheduler;

public class Booking {
    private long startTime;
    private long endTime;
    private String reserver;
    private String room;

    public Booking(long startTime, long endTime, String reserver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reserver = reserver;
    }

    public Booking(String startTime, String endTime, String reserver, String room) {
        this.startTime = Long.parseLong(startTime);
        this.endTime = Long.parseLong(endTime);
        this.reserver = reserver;
        this.room = room;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setReserver(String reserver) {
        this.reserver = reserver;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getReserver() {
        return reserver;
    }

    public String getRoom() {
        return room;
    }

    //returns true if two bookings conflict
    public boolean doesConflict(Booking anotherBooking) {
        //if the event starts before existing event and ends after existing event
        if ((anotherBooking.startTime < (this.endTime) )&& (anotherBooking.startTime > (this.startTime))) {
            return true;
        }
        //if an existing event ends before the endtime of this event and after the start time of this event
        if ((anotherBooking.endTime < (this.endTime)) && (anotherBooking.endTime > (this.startTime))) {
            return true;
        }
        //when one booking is fully enclosed in another
        if ((this.startTime <(anotherBooking.endTime)) && (this.startTime > (anotherBooking.startTime))) {
            return true;
        }
        //when one booking is fully enclosed in another
        if ((this.endTime < (anotherBooking.endTime)) && (this.endTime > (anotherBooking.startTime))) {
            return true;
        }
        //if an existing event and this event have the same startTime and endTime
        else if((anotherBooking.endTime == (this.endTime)) && (anotherBooking.startTime == (this.startTime))){
            return true;
        }
        //if an existing event and this event have the same startTime but different endTimes
        else if(anotherBooking.startTime == (this.startTime)){
            return true;
        }
        //if an existing event and this event have the same endTime but different startTimes
        else if(anotherBooking.endTime == (this.endTime)){
            return true;
        }
        else {
            return false;
        }
    }


    public BookingEntry asEntry(){
        BookingEntry book = new BookingEntry("", this.room,((Long) startTime).toString(),((Long) endTime).toString(),reserver);
        return book;
    }

}
