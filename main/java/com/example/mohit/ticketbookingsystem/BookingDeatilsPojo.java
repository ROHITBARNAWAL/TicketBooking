package com.example.mohit.ticketbookingsystem;

public class BookingDeatilsPojo {
private String name;
    private String fromLocation;
    private String toLocation;
    private String travellingDate;

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getTravellingDate() {
        return travellingDate;
    }

    public void setTravellingDate(String travellingDate) {
        this.travellingDate = travellingDate;
    }
}
