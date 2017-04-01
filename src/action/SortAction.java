package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Flight;
import org.apache.struts2.ServletActionContext;
import service.SearchInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by QQZhao on 3/29/17.
 */
public class SortAction extends ActionSupport {

    private String sortGoingBy;
    private String sortReturningBy;

    public String getSortGoingBy() {
        return sortGoingBy;
    }

    public void setSortGoingBy(String sortGoingBy) {
        this.sortGoingBy = sortGoingBy;
    }

    public String getSortReturningBy() {
        return sortReturningBy;
    }

    public void setSortReturningBy(String sortReturningBy) {
        this.sortReturningBy = sortReturningBy;
    }

    public String sortGoing(){

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();
        List<List<Flight>> validGoingFlightsList = (List<List<Flight>>)session.getAttribute("validGoingFlights");

        if(sortGoingBy.equals("price")){

            validGoingFlightsList = searchInfoService.getValidFlightsSortedByPrice(validGoingFlightsList);

        }else if(sortGoingBy.equals("departureTime")){

            validGoingFlightsList = searchInfoService.getValidFlightsSortedByDepartureTime(validGoingFlightsList);

        }else if(sortGoingBy.equals("arrivalTime")){

            validGoingFlightsList = searchInfoService.getValidFlightsSortedByArrivalTime(validGoingFlightsList);

        }else if(sortGoingBy.equals("transitionTime")){

            validGoingFlightsList = searchInfoService.getValidFlightsSortedByTransitionTime(validGoingFlightsList);

        }

        session.setAttribute("validGoingFlights", validGoingFlightsList);
        session.setAttribute("sortGoingBy", sortGoingBy);

        return SUCCESS;
    }


    public String sortReturning(){

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();
        List<List<Flight>> validReturningFlightsList = (List<List<Flight>>)session.getAttribute("validReturningFlights");

        if(sortReturningBy.equals("price")){

            validReturningFlightsList = searchInfoService.getValidFlightsSortedByPrice(validReturningFlightsList);

        }else if(sortReturningBy.equals("departureTime")){

            validReturningFlightsList = searchInfoService.getValidFlightsSortedByDepartureTime(validReturningFlightsList);

        }else if(sortReturningBy.equals("arrivalTime")){

            validReturningFlightsList = searchInfoService.getValidFlightsSortedByArrivalTime(validReturningFlightsList);

        }else if(sortReturningBy.equals("transitionTime")){

            validReturningFlightsList = searchInfoService.getValidFlightsSortedByTransitionTime(validReturningFlightsList);

        }

        session.setAttribute("validReturningFlights", validReturningFlightsList);
        session.setAttribute("sortReturningBy", sortReturningBy);

        return SUCCESS;
    }
}
