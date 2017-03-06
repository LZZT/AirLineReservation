package model;


import java.sql.Time;

/**
 * Created by QQZhao on 3/5/17.
 */
public class Flight {

    private String flightNumber;
    private Time departureTime;
    private Time arrivalTime;
    private String daysOperated;
    private int price;

    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraftModel;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDaysOperated() {
        return daysOperated;
    }

    public void setDaysOperated(String daysOperated) {
        this.daysOperated = daysOperated;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Aircraft getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(Aircraft aircraftModel) {
        this.aircraftModel = aircraftModel;
    }
}
