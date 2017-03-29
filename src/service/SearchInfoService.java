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

    public List<List<Flight>> handleSingleTrip(List<Airport> departureAirportsList, List<Airport> arrivalAirportsList, String departingDate) {

        int weekNum = convertStringDateToWeekInteger(departingDate);

        List<List<Flight>> validFlightsList = new ArrayList<>();
        FlightDAO flightDAO = new FlightDAO();

        for (Airport departureAirport : departureAirportsList) {
            for (Airport arrivalAirport : arrivalAirportsList) {

                List<List<Flight>> nonStopFlightList = flightDAO.getNoneStopFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum);
                if (nonStopFlightList != null) {
                    validFlightsList.addAll(nonStopFlightList);
                }

                List<List<Flight>> oneStopFlightList = flightDAO.getOneStopFlightByAirportsCodeAndDate(departureAirport.getCode(), arrivalAirport.getCode(), weekNum);
                if (oneStopFlightList != null) {
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
    private int convertStringDateToWeekInteger(String dateToBeConverted) {

        Date date = new Date(dateToBeConverted);
        String weekDay = date.toString().split(" ")[0];

        if (weekDay.equals("Mon")) {
            return 1;
        } else if (weekDay.equals("Tue")) {
            return 2;
        } else if (weekDay.equals("Wed")) {
            return 3;
        } else if (weekDay.equals("Thu")) {
            return 4;
        } else if (weekDay.equals("Fri")) {
            return 5;
        } else if (weekDay.equals("Sat")) {
            return 6;
        } else {
            return 7;
        }
    }

    public List<Airport> getAirportsByCityOrAirportCode(String cityOrAirport) {

        List<Airport> airportsList = null;
        AirportDAO airportDAO = new AirportDAO();

        if (cityOrAirport.length() == 3) {

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

    public List<Airport> getAirportsByNamesList(String[] nameList) {
        List<Airport> airportsList = new ArrayList<>();
        AirportDAO airportDAO = new AirportDAO();
        for (String name : nameList) {
            airportsList.add(airportDAO.getAirportByAirportName(name));
        }
        return airportsList;
    }

    public List<Flight> getFlightByFlightNumber(String flightNumber) {
        FlightDAO flightDAO = new FlightDAO();

        List<Flight> flightsList = flightDAO.getFlightByFlightNumber(flightNumber);

        return flightsList;
    }

    public List<List<Flight>> filterValidFlightsListByAirportList(List<List<Flight>> validFlightsList, String[] newDepartureAirportNamesList, String[] newArrivalAirportNamesList) {

        List<List<Flight>> newValidFlightsList = new ArrayList<>();

        for (List<Flight> eachFlightBundle : validFlightsList) {

            String currentFlightBundleDepartureAirportName = eachFlightBundle.get(0).getDepartureAirport().getName();
            String currentFlightBundleArrivalAirportName = eachFlightBundle.get(eachFlightBundle.size() - 1).getArrivalAirport().getName();

            for (String chosenDepartureAirportName : newDepartureAirportNamesList) {

                if (chosenDepartureAirportName.equals(currentFlightBundleDepartureAirportName)) {

                    for (String chosenArrivalAirportName : newArrivalAirportNamesList) {
                        if (chosenArrivalAirportName.equals(currentFlightBundleArrivalAirportName)) {

                            newValidFlightsList.add(eachFlightBundle);

                        }
                    }
                }
            }
        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    public List<Airport> findNearByAirportsInSameCity(Airport initialAirport) {

        String cityName = initialAirport.getCity().getCity();
        return getAirportsByCityOrAirportCode(cityName);

    }

    public List<List<Flight>> filterValidFlightsListByStopTypes(List<List<Flight>> validFlightsList, String[] stopType) {

        if (stopType.length >= 2 || stopType.length == 0) {
            return validFlightsList;
        }

        List<List<Flight>> newValidFlightsList = new ArrayList<>();

        if (stopType[0].equals("noneStop")) {
            for (List<Flight> eachFlightBundle : validFlightsList) {

                if (eachFlightBundle.size() == 1) {
                    newValidFlightsList.add(eachFlightBundle);
                }
            }
        }

        if (stopType[0].equals("oneStop")) {
            for (List<Flight> eachFlightBundle : validFlightsList) {

                if (eachFlightBundle.size() != 1) {
                    newValidFlightsList.add(eachFlightBundle);
                }
            }
        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    public List<Airport> getAirportsByFlight2DList(List<List<Flight>> validFlightsList, String type) {

        if (!type.equals("departure") && !type.equals("arrival")) {
            return null;
        }

        List<Airport> airportsList = new ArrayList<>();
        Airport targetAirport = null;

        for (List<Flight> eachFlightBundle : validFlightsList) {

            if (type.equals("departure")) {
                targetAirport = eachFlightBundle.get(0).getDepartureAirport();
            } else {
                targetAirport = eachFlightBundle.get(eachFlightBundle.size() - 1).getArrivalAirport();
            }

            if (airportsList.size() == 0) {
                airportsList.add(targetAirport);
            } else {
                boolean duplicated = false;
                for (Airport airport : airportsList) {
                    if (airport.getCode().equals(targetAirport.getCode())) {
                        duplicated = true;
                    }
                }
                if (!duplicated) {
                    airportsList.add(targetAirport);
                }
            }

        }

        return airportsList;
    }

    public List<List<Flight>> filterValidFlightsListByTimeRange(List<List<Flight>> validFlightsList, String[] timeRange) {

        if (timeRange.length == 3 || timeRange.length == 0) {
            return validFlightsList;
        }

        List<List<Flight>> newValidFlightsList = new ArrayList<>();
        for (List<Flight> eachFlightBundle : validFlightsList) {

            Time departureTime = eachFlightBundle.get(0).getDepartureTime();
            if (isInSelectedTimeRange(departureTime, timeRange)) {
                newValidFlightsList.add(eachFlightBundle);
            }

        }
        validFlightsList.clear();
        return newValidFlightsList;
    }

    private boolean isInSelectedTimeRange(Time targetTime, String[] timeRange) {

        for (String time : timeRange) {

            if (time.equals("morning") && inBetween(targetTime.toLocalTime(), LocalTime.of(0, 0), LocalTime.of(11, 59))) {
                return true;
            }
            if (time.equals("afternoon") && inBetween(targetTime.toLocalTime(), LocalTime.of(12, 0), LocalTime.of(17, 59))) {
                return true;
            }
            if (time.equals("evening") && inBetween(targetTime.toLocalTime(), LocalTime.of(18, 0), LocalTime.of(23, 59))) {
                return true;
            }
        }
        return false;
    }

    private boolean inBetween(LocalTime targetTime, LocalTime start, LocalTime end) {
        return (targetTime.isAfter(start) || targetTime.equals(start)) && (targetTime.isBefore(end) || targetTime.equals(end));
    }

    public List<List<Flight>> getValidFlightsSortedByPrice(List<List<Flight>> validFlightsList){

        Collections.sort(validFlightsList, new Comparator<List<Flight>>() {
            @Override
            public int compare(List<Flight> o1, List<Flight> o2) {
                Integer o1Price = 0;
                Integer o2Price = 0;

                for(Flight f1 : o1){
                    o1Price += f1.getPrice();
                }

                for(Flight f2 : o2){
                    o2Price += f2.getPrice();
                }

                return o1Price < o2Price? -1 : o1Price == o2Price? 0 : 1;
            }
        });

        return validFlightsList;
    }

    public List<List<Flight>> getValidFlightsSortedByDepartureTime(List<List<Flight>> validFlightsList){
        Collections.sort(validFlightsList, new Comparator<List<Flight>>() {
            @Override
            public int compare(List<Flight> o1, List<Flight> o2) {
                LocalTime o1DepartureTime = o1.get(0).getDepartureTime().toLocalTime();
                LocalTime o2DepartureTime = o2.get(0).getDepartureTime().toLocalTime();

                return o1DepartureTime.isBefore(o2DepartureTime)? -1 : o1DepartureTime.equals(o2DepartureTime)? 0 : 1;
            }
        });

        return validFlightsList;
    }

    public List<List<Flight>> getValidFlightsSortedByArrivalTime(List<List<Flight>> validFlightsList){
        Collections.sort(validFlightsList, new Comparator<List<Flight>>() {
            @Override
            public int compare(List<Flight> o1, List<Flight> o2) {
                LocalTime o1ArrivalTime = o1.get(o1.size() - 1).getArrivalTime().toLocalTime();
                LocalTime o2ArrivalTime = o2.get(o2.size() - 1).getArrivalTime().toLocalTime();

                return o1ArrivalTime.isBefore(o2ArrivalTime)? -1 : o1ArrivalTime.equals(o2ArrivalTime)? 0 : 1;
            }
        });

        return validFlightsList;
    }

    public List<List<Flight>> getValidFlightsSortedByTransitionTime(List<List<Flight>> validFlightsList){

        Collections.sort(validFlightsList, new Comparator<List<Flight>>() {
            @Override
            public int compare(List<Flight> o1, List<Flight> o2) {
                
                int o1Priority = calculatePriorityForEachFlightsBundle(o1);
                int o2Priority = calculatePriorityForEachFlightsBundle(o2);

                return o1Priority < o2Priority? -1 : o1Priority == o2Priority? 0 : 1;
            }
        });

        return validFlightsList;
    }

    private int calculatePriorityForEachFlightsBundle(List<Flight> flights){

        int priority = 0;

        if(flights.size() == 0){
            return priority;
        }

        for(int i = 0; i < flights.size() - 1; i++){

            int hourGap = flights.get(i + 1).getDepartureTime().toLocalTime().getHour() - flights.get(i).getArrivalTime().toLocalTime().getHour();
            int minuteGap = flights.get(i + 1).getDepartureTime().toLocalTime().getMinute() - flights.get(i).getArrivalTime().toLocalTime().getMinute();

            if(hourGap == 0){
                if(minuteGap <= 20){
                    priority += 2;
                }else if(minuteGap > 20 && minuteGap <= 40){
                    priority += 1;
                }
            }else{

                if(hourGap <= 2){
                    priority += 0;
                }else if(hourGap > 2 && hourGap <= 4){
                    priority += 1;
                }else if(hourGap > 4 && hourGap < 8){
                    priority += 2;
                }else{
                    priority += 3;
                }
            }
        }

        return priority;
    }
}
