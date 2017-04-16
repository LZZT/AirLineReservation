package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Flight;
import model.Ticket;
import model.Traveler;
import model.ValidateTicket;
import org.apache.struts2.ServletActionContext;
import service.ValidateTicketService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by liweihao on 3/9/17.
 */
public class CartNumberAction extends ActionSupport {

//    private String ticketsNumber;
//
//    public String getTicketsNumber() {
//        return ticketsNumber;
//    }
//
//    public void setTicketsNumber(String ticketsNumber) {
//        this.ticketsNumber = ticketsNumber;
//    }


    private ValidateTicketService validateTicketService=new ValidateTicketService();


    public String cart() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        if(null == session.getAttribute("username") ||
                null == session.getAttribute("departingDate") ||
                null == session.getAttribute("leavingFlightObjectSet") ||
                (null != session.getAttribute("returningDate") && null == session.getAttribute("returningFlightObjectSet"))){
            return "login";
        }

//        session.setAttribute("ticketsNumber", ticketsNumber);
        String departingDate = (String) session.getAttribute("departingDate");
        String returningDate = (String) session.getAttribute("returningDate");
        List<Flight> leavingFlightObjectSet = (List<Flight>) session.getAttribute("leavingFlightObjectSet");
        int leavingSetSize = leavingFlightObjectSet.size();
        List<Flight> returningFlightObjectSet = (List<Flight>) session.getAttribute("returningFlightObjectSet");
        List<Flight> flightObjectSet = (List) ((ArrayList<Flight>) leavingFlightObjectSet).clone();
        if (returningFlightObjectSet != null) {
            flightObjectSet.addAll(returningFlightObjectSet);
        }
        String[] flightdate = new String[flightObjectSet.size()];
        for (int i = 0; i < flightObjectSet.size(); i++) {
            if (i < leavingSetSize) {
                flightdate[i] = departingDate;
            } else {
                flightdate[i] = returningDate;
            }
        }

//        for (int i = 0; i < Integer.valueOf(ticketsNumber); i++) {
//            for (Flight flight : flightObjectSet) {
//                if (!validateTicketService.isAvaliable(flight, flightdate[flightObjectSet.indexOf(flight)])) {
//                    return ERROR;
//                }
//            }
//        }
        return SUCCESS;
    }


}
