package service;

import dao.AirportDAO;
import dao.FlightDAO;
import model.Aircraft;
import model.Airport;
import model.Flight;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by QQZhao on 3/5/17.
 */
public class SearchInfoService {

    public List<List<Flight>> handleSingleTrip(List<Airport> departureAirportsList, List<Airport> arrivalAirportsList, String departingDate){

        int weekNum = convertStringDateToWeekInteger(departingDate);

        List<List<Flight>> validFlightsList = new ArrayList<>();
        FlightDAO flightDAO = new FlightDAO();

        for(Airport departureAirport: departureAirportsList){
            for(Airport arrivalAirport: arrivalAirportsList){

                List<List<Flight>> nonStopFlightList = flightDAO.getNoneStopFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum);
                if(nonStopFlightList != null){
                    validFlightsList.addAll(nonStopFlightList);
                }

                List<List<Flight>> oneStopFlightList = flightDAO.getOneStopFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum);
                if(oneStopFlightList != null){
                    validFlightsList.addAll(oneStopFlightList);
                }

////                Two stop not done yet
//                List<List<Flight>> twoStopFlightList = flightDAO.getTwoStopFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum);
//                if(oneStopFlightList != null){
//                    validFlightsList.addAll(twoStopFlightList);
//                }

////                Example of Data Structure of validFlightsList
//                Flight a = new Flight();
//                a.setFlightNumber("a");
//                Flight b = new Flight();
//                b.setFlightNumber("b");
//                Flight c = new Flight();
//                c.setFlightNumber("c");
//                Flight d = new Flight();
//                d.setFlightNumber("d");
//                ArrayList[] x = {new ArrayList<Flight>(Arrays.asList(a)), new ArrayList<Flight>(Arrays.asList(b)), new ArrayList<Flight>(Arrays.asList(new Flight[]{c, d}))};
//                validFlightsList = Arrays.asList(x);

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

        } else {

            String[] locationInformationList = cityOrAirport.split(",");
            String targetCityName = locationInformationList[0];
            airportsList = airportDAO.getAirportsByCityName(targetCityName);

        }
        return airportsList;
    }

    public List<Airport> getAirportsByNamesList(String[] nameList){
        List<Airport> airportsList = new ArrayList<>();
        AirportDAO airportDAO = new AirportDAO();
        for(String name : nameList){
            airportsList.add(airportDAO.getAirportByAirportName(name));
        }
        return airportsList;
    }


    public List<Flight> getFlightByFlightNumber(String flightNumber) {
        FlightDAO flightDAO = new FlightDAO();

        List<Flight> flightsList = flightDAO.getFlightByFlightNumber(flightNumber);

        return flightsList;
    }


    public List<List<Flight>> filterValidFlightsListByAirportList(List<List<Flight>> validFlightsList, String[] newDepartureAirportNamesList, String[] newArrivalAirportNamesList){

        List<List<Flight>> newValidFlightsList = new ArrayList<>();

        for(List<Flight> eachFlightBundle : validFlightsList){

            String currentFlightBundleDepartureAirportName = eachFlightBundle.get(0).getDepartureAirport().getName();
            String currentFlightBundleArrivalAirportName = eachFlightBundle.get(eachFlightBundle.size() - 1).getArrivalAirport().getName();

            for(String chosenDepartureAirportName : newDepartureAirportNamesList){

                if(chosenDepartureAirportName.equals(currentFlightBundleDepartureAirportName)){

                    for(String chosenArrivalAirportName : newArrivalAirportNamesList){
                        if(chosenArrivalAirportName.equals(currentFlightBundleArrivalAirportName)){

                            newValidFlightsList.add(eachFlightBundle);

                        }
                    }
                }
            }
        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    public List<Airport> findNearByAirportsInSameCity(Airport initialAirport){

        String cityName = initialAirport.getCity().getCity();
        return getAirportsByCityOrAirportCode(cityName);

    }


    public List<List<Flight>> filterValidFlightsListByStopTypes(List<List<Flight>> validFlightsList, String[] stopType){

        if(stopType.length >= 2 || stopType.length == 0){
            return validFlightsList;
        }

        List<List<Flight>> newValidFlightsList = new ArrayList<>();

        if(stopType[0].equals("noneStop")){
            for(List<Flight> eachFlightBundle : validFlightsList){

                if(eachFlightBundle.size() == 1){
                    newValidFlightsList.add(eachFlightBundle);
                }
            }
        }

        if(stopType[0].equals("oneStop")){
            for(List<Flight> eachFlightBundle : validFlightsList){

                if(eachFlightBundle.size() != 1){
                    newValidFlightsList.add(eachFlightBundle);
                }
            }
        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    public List<Airport> getAirportsByFlight2DList(List<List<Flight>> validFlightsList, String type){

        if(!type.equals("departure") && !type.equals("arrival")){
            return null;
        }

        List<Airport> airportsList = new ArrayList<>();
        Airport targetAirport = null;

        for(List<Flight> eachFlightBundle : validFlightsList){

            if(type.equals("departure")){
                targetAirport = eachFlightBundle.get(0).getDepartureAirport();
            }else{
                targetAirport = eachFlightBundle.get(eachFlightBundle.size() - 1).getArrivalAirport();
            }

            if(airportsList.size() == 0){
                airportsList.add(targetAirport);
            }else{
                boolean duplicated = false;
                for(Airport airport : airportsList){
                    if(airport.getCode().equals(targetAirport.getCode())){
                        duplicated = true;
                    }
                }
                if(!duplicated){
                    airportsList.add(targetAirport);
                }
            }

        }

        return airportsList;
    }

    public List<List<Flight>> filterValidFlightsListByTimeRange(List<List<Flight>> validFlightsList, String[] timeRange){

        if(timeRange.length == 3 || timeRange.length == 0){
            return validFlightsList;
        }

        List<List<Flight>> newValidFlightsList = new ArrayList<>();
        for(List<Flight> eachFlightBundle : validFlightsList){

            Time departureTime = eachFlightBundle.get(0).getDepartureTime();
            if(isInSelectedTimeRange(departureTime, timeRange)){
                newValidFlightsList.add(eachFlightBundle);
            }

        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    private boolean isInSelectedTimeRange(Time targetTime, String[] timeRange){

        for(String time : timeRange){

            if(time.equals("morning") && inBetween(targetTime.toLocalTime(), LocalTime.of(0, 0), LocalTime.of(11, 59))){
                return true;
            }
            if(time.equals("afternoon") && inBetween(targetTime.toLocalTime(), LocalTime.of(12, 0), LocalTime.of(17, 59))){
                return true;
            }
            if(time.equals("evening") && inBetween(targetTime.toLocalTime(), LocalTime.of(18, 0), LocalTime.of(23, 59))){
                return true;
            }
        }
        return false;
    }

    private boolean inBetween(LocalTime targetTime, LocalTime start, LocalTime end){
        return (targetTime.isAfter(start) || targetTime.equals(start)) && (targetTime.isBefore(end) || targetTime.equals(end));
    }

}
