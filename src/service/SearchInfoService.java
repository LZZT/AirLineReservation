package service;

import dao.AirportDAO;
import dao.FlightDAO;
import model.Airport;
import model.Flight;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by QQZhao on 3/5/17.
 */
public class SearchInfoService {

    public List<Flight> handleSingleTrip(List<Airport> departureAirportsList, List<Airport> arrivalAirportsList, String departingDate){

        int weekNum = convertStringDateToWeekInteger(departingDate);

        List<Flight> validFlightsList = new ArrayList<>();
        FlightDAO flightDAO = new FlightDAO();

        for(Airport departureAirport: departureAirportsList){
            for(Airport arrivalAirport: arrivalAirportsList){
                validFlightsList.addAll(flightDAO.getFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum));
            }
        }

        return validFlightsList;
    }

    @SuppressWarnings("deprecation")
    private int convertStringDateToWeekInteger(String dateToBeConverted){

        Date date = new Date(dateToBeConverted);
        String weekDay = date.toString().split(" ")[0];

        if(weekDay.equals("Mon")){
            return 1;
        }else if(weekDay.equals("Tue")){
            return 2;
        }else if(weekDay.equals("Wed")){
            return 3;
        }else if(weekDay.equals("Thu")){
            return 4;
        }else if(weekDay.equals("Fri")){
            return 5;
        }else if(weekDay.equals("Sat")){
            return 6;
        }else{
            return 7;
        }
    }


    public List<Airport> getAirportsByCityOrAirportCode(String cityOrAirport){

        List<Airport> airportsList = null;
        AirportDAO airportDAO = new AirportDAO();

        if(cityOrAirport.length() == 3){

            airportsList = new ArrayList<>();
            Airport airport = airportDAO.getAirportByCode(cityOrAirport);
            airportsList.add(airport);

        }else{

            String[] locationInformationList = cityOrAirport.split(",");
            String targetCityName = locationInformationList[0];
            airportsList = airportDAO.getAirportsByCityName(targetCityName);

        }
        return airportsList;
    }

    private List<Airport> getAirportsByNamesList(List<String> nameList){
        List<Airport> airportsList = new ArrayList<>();
        AirportDAO airportDAO = new AirportDAO();
        for(String name : nameList){
            airportsList.add(airportDAO.getAirportByAirportName(name));
        }
        return airportsList;
    }

}
