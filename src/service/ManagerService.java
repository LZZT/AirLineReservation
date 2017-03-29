package service;

import java.util.List;

import com.sun.net.httpserver.Authenticator;
import dao.AircraftDAO;
import dao.AirlineDAO;
import dao.FlightDAO;
import dao.AirportDAO;
import model.Aircraft;
import model.Airline;
import model.Flight;
import model.Airport;


/**
 * Created by yenchanghsieh on 3/8/17.
 */
public class ManagerService {
    public boolean insertFlight(Flight flight) {
        FlightDAO flightDAO = new FlightDAO();

        List<Flight> flightsList = flightDAO.getFlightByFlightNumber(flight.getFlightNumber());
        if(flightsList.size() != 0)
            return false;

        flightDAO.saveFlight(flight);

        return true;
    }

    public boolean deleteFlight(String flightNumber) {
        FlightDAO flightDAO = new FlightDAO();
        flightDAO.deleteFlight(flightNumber);

        return true;
    }

    public boolean updateFlight(Flight flight) {
        FlightDAO flightDAO = new FlightDAO();

        flightDAO.updateFlight(flight);

        return true;
    }

    public Aircraft getAircraftByModel(String model) {
        AircraftDAO aircraftDAO = new AircraftDAO();

        Aircraft aircraft = aircraftDAO.getAircraftByModel(model);

        return aircraft;
    }

    public Airline getAirlineByName(String name) {
        AirlineDAO airlineDAO = new AirlineDAO();

        Airline airline = airlineDAO.getAirlineByName(name);

        return airline;
    }

    public List<String> getAirportsCode() {
        AirportDAO airportDAO = new AirportDAO();

        List<String> airports = airportDAO.getAirportsCode();

        return airports;
    }

    public List<String> getAirlinesName() {
        AirlineDAO airlineDAO = new AirlineDAO();

        List<String> airlines = airlineDAO.getAirlinesName();

        return airlines;
    }
}
