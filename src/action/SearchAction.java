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

/**
 * Created by QQZhao on 3/5/17.
 */
public class SearchAction extends ActionSupport {

    private String departureCityOrAirport;
    private String arrivalCityOrAirport;
    private String tripType;
    private String departingDate;
    private String returningDate;

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

        if(tripType.equals("singleTrip")){

            List<Airport> departureAirportsList = searchInfoService.getAirportsByCityOrAirportCode(departureCityOrAirport);
            List<Airport> arrivalAirportsList = searchInfoService.getAirportsByCityOrAirportCode(arrivalCityOrAirport);
            session.setAttribute("departureAirportsList", departureAirportsList);
            session.setAttribute("arrivalAirportsList", arrivalAirportsList);

            List<Flight> validFlightsList = searchInfoService.handleSingleTrip(departureAirportsList, arrivalAirportsList, departingDate);
            session.setAttribute("validFlights", validFlightsList);
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

//        try{
//            Date validDate = new Date(returningDate);
//        }catch (Exception ex){
//            this.addActionError("Invalid Returning Date");
//        }


    }
}
