package action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;

import java.util.List;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Airport;
import model.Flight;
import service.ManagerService;
import service.SearchInfoService;

/**
 * Created by yenchanghsieh on 3/8/17.
 */

public class ManagerAction extends  ActionSupport {
    private String flightNumber;
    private String departTime;
    private String arriTime;
    private String[] daysOperated;
    private String departAirport;
    private String arriAirport;
    private String airline;
    private String aircraftModel;
    private int price;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriTime() {
        return arriTime;
    }

    public void setArriTime(String arriTime) {
        this.arriTime = arriTime;
    }

    public String[] getDaysOperated() {
        return daysOperated;
    }

    public void setDaysOperated(String[] daysOperated) {
        this.daysOperated = daysOperated;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(String departAirport) {
        this.departAirport = departAirport;
    }

    public String getArriAirport() {
        return arriAirport;
    }

    public void setArriAirport(String arriAirport) {
        this.arriAirport = arriAirport;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String insertFlight() throws Exception {
        ManagerService managerService = new ManagerService();
        SearchInfoService searchInfoService = new SearchInfoService();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        List<Airport> departureAirportsList = searchInfoService.getAirportsByCityOrAirportCode(departAirport);
        List<Airport> arrivalAirportsList = searchInfoService.getAirportsByCityOrAirportCode(arriAirport);

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(new Time(formatter.parse(departTime).getTime()));
        flight.setArrivalTime(new Time(formatter.parse(arriTime).getTime()));
        flight.setAircraftModel(managerService.getAircraftByModel(aircraftModel));
        flight.setAirline(managerService.getAirlineByName(airline));
        flight.setDepartureAirport(departureAirportsList.get(0));
        flight.setArrivalAirport(arrivalAirportsList.get(0));
        flight.setPrice(price);

        String operated = "";
        for(String day : daysOperated) {
            if(day.equals("Mon")){
                operated += "1";
            } else if(day.equals("Tue")) {
                operated += "2";
            } else if(day.equals("Wed")) {
                operated += "3";
            } else if(day.equals("Thu")) {
                operated += "4";
            } else if(day.equals("Fri")) {
                operated += "5";
            } else if(day.equals("Sat")) {
                operated += "6";
            } else{
                operated += "7";
            }
        }
        flight.setDaysOperated(operated);

        if(managerService.insertFlight(flight))
            return SUCCESS;

        return ERROR;
    }

    public String updateFlight() throws  Exception {
        ManagerService managerService = new ManagerService();
        SearchInfoService searchInfoService = new SearchInfoService();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        List<Airport> departureAirportsList = searchInfoService.getAirportsByCityOrAirportCode(departAirport);
        List<Airport> arrivalAirportsList = searchInfoService.getAirportsByCityOrAirportCode(arriAirport);

        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setDepartureTime(new Time(formatter.parse(departTime).getTime()));
        flight.setArrivalTime(new Time(formatter.parse(arriTime).getTime()));
        flight.setAircraftModel(managerService.getAircraftByModel(aircraftModel));
        flight.setAirline(managerService.getAirlineByName(airline));
        flight.setDepartureAirport(departureAirportsList.get(0));
        flight.setArrivalAirport(arrivalAirportsList.get(0));
        flight.setPrice(price);

        String operated = "";
        for(String day : daysOperated) {
            if(day.equals("Mon")){
                operated += "1";
            } else if(day.equals("Tue")){
                operated += "2";
            } else if(day.equals("Wed")){
                operated += "3";
            } else if(day.equals("Thu")){
                operated += "4";
            } else if(day.equals("Fri")){
                operated += "5";
            } else if(day.equals("Sat")){
                operated += "6";
            } else {
                operated += "7";
            }
        }
        flight.setDaysOperated(operated);

        if(managerService.updateFlight(flight))
            return SUCCESS;

        return ERROR;
    }

    public String deleteFlight() throws Exception {
        ManagerService managerService = new ManagerService();

        managerService.deleteFlight(flightNumber);

        return SUCCESS;
    }
}