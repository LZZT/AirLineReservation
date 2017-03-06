package service;

import dao.AirportDAO;
import model.Airport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QQZhao on 3/5/17.
 */
public class AirportService {

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


    public List<Airport> getAirportsByNamesList(List<String> nameList){
        List<Airport> airportsList = new ArrayList<>();
        AirportDAO airportDAO = new AirportDAO();
        for(String name : nameList){
            airportsList.add(airportDAO.getAirportByAirportName(name));
        }
        return airportsList;
    }

}
