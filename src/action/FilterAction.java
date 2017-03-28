package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Airport;
import model.Flight;
import org.apache.struts2.ServletActionContext;
import service.SearchInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by QQZhao on 3/5/17.
 */
public class FilterAction extends ActionSupport {

    private String[] goingDepartureAirportNamesList;
    private String[] goingArrivalAirportNamesList;
    private String[] returningDepartureAirportNamesList;
    private String[] returningArrivalAirportNamesList;

    public String[] getReturningDepartureAirportNamesList() {
        return returningDepartureAirportNamesList;
    }

    public void setReturningDepartureAirportNamesList(String[] returningDepartureAirportNamesList) {
        this.returningDepartureAirportNamesList = returningDepartureAirportNamesList;
    }

    public String[] getReturningArrivalAirportNamesList() {
        return returningArrivalAirportNamesList;
    }

    public void setReturningArrivalAirportNamesList(String[] returningArrivalAirportNamesList) {
        this.returningArrivalAirportNamesList = returningArrivalAirportNamesList;
    }

    public String[] getGoingDepartureAirportNamesList() {
        return goingDepartureAirportNamesList;
    }

    public void setGoingDepartureAirportNamesList(String[] goingDepartureAirportNamesList) {
        this.goingDepartureAirportNamesList = goingDepartureAirportNamesList;
    }

    public String[] getGoingArrivalAirportNamesList() {
        return goingArrivalAirportNamesList;
    }

    public void setGoingArrivalAirportNamesList(String[] goingArrivalAirportNamesList) {
        this.goingArrivalAirportNamesList = goingArrivalAirportNamesList;
    }

    public String filterGoingAirports (){

        if(goingDepartureAirportNamesList == null || goingArrivalAirportNamesList == null){
            this.addActionError("Departure and Arrival Airport Can not be empty!");
            return INPUT;
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();
        List<List<Flight>> validGoingFlightsList = searchInfoService.filterValidFlightsListByAirportList((List<List<Flight>>)session.getAttribute("validGoingFlights"), goingDepartureAirportNamesList, goingArrivalAirportNamesList);
        session.setAttribute("validGoingFlights", validGoingFlightsList);

        List<Airport> goingDepartureAirportsList = searchInfoService.getAirportsByNamesList(goingDepartureAirportNamesList);
        List<Airport> goingArrivalAirportsList = searchInfoService.getAirportsByNamesList(goingArrivalAirportNamesList);

        session.setAttribute("goingDepartureAirportsList", goingDepartureAirportsList);
        session.setAttribute("goingArrivalAirportsList", goingArrivalAirportsList);
        return SUCCESS;
    }


    public String filterReturningAirports (){

        if(returningDepartureAirportNamesList == null || returningArrivalAirportNamesList == null){
            this.addActionError("Departure and Arrival Airport Can not be empty!");
            return INPUT;
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();
        List<List<Flight>> validReturningFlightsList = searchInfoService.filterValidFlightsListByAirportList((List<List<Flight>>)session.getAttribute("validReturningFlights"), returningDepartureAirportNamesList, returningArrivalAirportNamesList);
        session.setAttribute("validReturningFlights", validReturningFlightsList);

        List<Airport> returningDepartureAirportsList = searchInfoService.getAirportsByNamesList(returningDepartureAirportNamesList);
        List<Airport> returningArrivalAirportsList = searchInfoService.getAirportsByNamesList(returningArrivalAirportNamesList);

        session.setAttribute("returningDepartureAirportsList", returningDepartureAirportsList);
        session.setAttribute("returningArrivalAirportsList", returningArrivalAirportsList);
        return SUCCESS;
    }


}
