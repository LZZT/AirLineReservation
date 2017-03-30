package model;

/**
 * Created by liweihao on 3/24/17.
 */
public class Ticket {
    String ticketID;
    String flightNumber;
    String flightDate;
    String travellerID;
    String transactionID;


    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getTravellerID() {
        return travellerID;
    }

    public void setTravellerID(String travellerID) {
        this.travellerID = travellerID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
