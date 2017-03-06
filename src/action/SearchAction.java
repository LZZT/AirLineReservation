package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Airport;
import model.City;
import org.apache.struts2.ServletActionContext;
import service.AirportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by QQZhao on 3/5/17.
 */
public class SearchAction extends ActionSupport {

    private String departureCityOrAirport;
    private String arrivalCityOrAirport;

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

    public String findAirports() throws Exception {


        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        AirportService airportService = new AirportService();
        List<Airport> departureAirportsList = airportService.getAirportsByCityOrAirportCode(departureCityOrAirport);
        List<Airport> arrivalAirportsList = airportService.getAirportsByCityOrAirportCode(arrivalCityOrAirport);

        session.setAttribute("departureAirportsList", departureAirportsList);
        session.setAttribute("arrivalAirportsList", arrivalAirportsList);

        return SUCCESS;
    }

    public void validateFindAirports() {

        if(departureCityOrAirport.length() < 3){
            this.addActionError("Departure City/Airport Information Too short");
        }
        if(arrivalCityOrAirport.length() < 3){
            this.addActionError("Arrival City/Airport Information Too short");
        }

    }
}
