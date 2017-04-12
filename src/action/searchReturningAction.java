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
 * Created by QQZhao on 3/9/17.
 */
public class searchReturningAction extends ActionSupport{

    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String findInfo() throws Exception {

        try {

            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();

            SearchInfoService searchInfoService = new SearchInfoService();
            String validIndex = index.split("/")[0];
            List<Flight> chosenGoingFlight = ((List<List<Flight>>) session.getAttribute("validGoingFlights")).get(Integer.valueOf(validIndex));

            session.setAttribute("leavingFlightObjectSet", chosenGoingFlight);
            int price = 0;
            for (Flight f : chosenGoingFlight) {
                price += f.getPrice();
            }
            session.setAttribute("leavingPrice", price);


            if (null == session.getAttribute("returningDate")) {

                if(null != session.getAttribute("username")){
                    return "JumpToCart";
                }else{
                    session.setAttribute("goToCartDirectly", true);
                    return "login";
                }
            }

            Airport returningDepartureAirport = chosenGoingFlight.get(chosenGoingFlight.size() - 1).getArrivalAirport();
            Airport returningArrivalAirport = chosenGoingFlight.get(0).getDepartureAirport();

            List<Airport> returningDepartureAirportsList = searchInfoService.findNearByAirportsInSameCity(returningDepartureAirport);
            List<Airport> returningArrivalAirportsList = searchInfoService.findNearByAirportsInSameCity(returningArrivalAirport);

            session.setAttribute("returningDepartureAirportsList", returningDepartureAirportsList);
            session.setAttribute("returningArrivalAirportsList", returningArrivalAirportsList);

            String returningDate = (String) session.getAttribute("returningDate");
            List<List<Flight>> validReturningFlightsList = searchInfoService.handleSingleTrip(returningDepartureAirportsList, returningArrivalAirportsList, returningDate);

            if (validReturningFlightsList.size() == 0) {
                return INPUT;
            }

            session.setAttribute("validReturningFlights", validReturningFlightsList);

            return SUCCESS;

        }catch (Exception ex){
            return "GeneralError";
        }
    }

}
