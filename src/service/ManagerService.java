package service;

import java.util.List;

import com.sun.net.httpserver.Authenticator;
import dao.FlightDAO;
import dao.AirportDAO;
import model.Flight;


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
}
