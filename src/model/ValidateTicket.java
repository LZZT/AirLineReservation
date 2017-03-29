package model;

/**
 * Created by liweihao on 3/26/17.
 */
public class ValidateTicket {
    String flightNumber;
    String flightDate;
    int capacity;
    int totalTicketNumber;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTotalTicketNumber() {
        return totalTicketNumber;
    }

    public void setTotalTicketNumber(int totalTicketNumber) {
        this.totalTicketNumber = totalTicketNumber;
    }
}
