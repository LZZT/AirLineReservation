package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Airport;
import model.Flight;
import org.apache.struts2.ServletActionContext;
import service.SearchInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by QQZhao on 3/5/17.
 */
public class searchGoingAction extends ActionSupport {

    private String flightNumber;
    private String departureCityOrAirport;
    private String arrivalCityOrAirport;
    private String tripType;
    private String departingDate;
    private String returningDate;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCityOrAirport() {
        return departureCityOrAirport;
    }

    public void setDepartureCityOrAirport(String departureCityOrAirport) {
        this.departureCityOrAirport = departureCityOrAirport;
    }

    public String getArrivalCityOrAirport() {
        return arrivalCityOrAirport;
    }

    public void setArrivalCityOrAirport(String arrivalCityOrAirport) {
        this.arrivalCityOrAirport = arrivalCityOrAirport;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getDepartingDate() {
        return departingDate;
    }

    public void setDepartingDate(String departingDate) {
        this.departingDate = departingDate;
    }

    public String getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(String returningDate) {
        this.returningDate = returningDate;
    }

    public String findInfo() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();

        List<Airport> goingDepartureAirportsList = searchInfoService.getAirportsByCityOrAirportCode(departureCityOrAirport);
        List<Airport> goingArrivalAirportsList = searchInfoService.getAirportsByCityOrAirportCode(arrivalCityOrAirport);
        session.setAttribute("goingDepartureAirportsList", goingDepartureAirportsList);
        session.setAttribute("goingArrivalAirportsList", goingArrivalAirportsList);

        List<List<Flight>> validGoingFlightsList = searchInfoService.handleSingleTrip(goingDepartureAirportsList, goingArrivalAirportsList, departingDate);
        if(validGoingFlightsList.size() == 0){
            return INPUT;
        }
        session.setAttribute("validGoingFlights", validGoingFlightsList);

        if(tripType.equals("singleTrip")){

            try{
                session.removeAttribute("returningDate");
            }catch (Exception ex){}

            try{
                session.removeAttribute("returningDepartureAirportsList");
            }catch (Exception ex){}

            try{
                session.removeAttribute("returningArrivalAirportsList");
            }catch (Exception ex){}

            try{
                session.removeAttribute("validReturningFlights");
            }catch (Exception ex){}

            try{
                session.removeAttribute("returningFlightObjectSet");
            }catch (Exception ex){}

        }

        if(tripType.equals("roundTrip")){

            session.setAttribute("returningDate", returningDate);

        }
        return SUCCESS;
    }

    @SuppressWarnings("deprecation")
    public void validateFindInfo() {

        if(departureCityOrAirport.length() < 3){
            this.addActionError("Departure City/Airport Information Too short");
        }
        if(arrivalCityOrAirport.length() < 3){
            this.addActionError("Arrival City/Airport Information Too short");
        }

        try{
            Date validDate = new Date(departingDate);
        }catch (Exception ex){
            this.addActionError("Invalid Departing Date");
        }

        if(tripType.equals("roundTrip")){
            try{
                Date validDate = new Date(returningDate);
            }catch (Exception ex){
                this.addActionError("Invalid Returning Date");
            }
        }
    }

    public String searchFlightByFlightNumber() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();

        List<Flight> validFlightsList = searchInfoService.getFlightByFlightNumber(flightNumber);
        List<List<Flight>>  validFlightsLists = new ArrayList<>();
        validFlightsLists.add(validFlightsList);
        session.setAttribute("validGoingFlights", validFlightsLists);

        return SUCCESS;
    }
}
