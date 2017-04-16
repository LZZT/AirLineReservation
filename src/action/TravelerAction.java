package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Flight;
import model.Traveler;

import org.apache.struts2.ServletActionContext;
import service.ValidateTicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by liweihao on 3/9/17.
 */
public class TravelerAction extends ActionSupport {

    private ArrayList<Traveler> travelerSet;
    private String[] travelerHistory;
    private List<Traveler> travelersList;
    private int rowindex;
    private ValidateTicketService validateTicketService = new ValidateTicketService();


    public ArrayList<Traveler> getTravelerSet() {
        return travelerSet;
    }

    public void setTravelerSet(ArrayList<Traveler> travelerSet) {
        this.travelerSet = travelerSet;
    }

    public String[] getTravelerHistory() {
        return travelerHistory;
    }

    public void setTravelerHistory(String[] travelerHistory) {
        this.travelerHistory = travelerHistory;
    }

    public List<Traveler> getTravelersList() {
        return travelersList;
    }

    public void setTravelersList(List<Traveler> travelersList) {
        this.travelersList = travelersList;
    }

    public String add() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        travelersList = new ArrayList<>();

        List<Traveler> tList = (List<Traveler>) session.getAttribute("TravelersHistoryList");

        if (null != travelerHistory && travelerHistory.length > 0) {
            for (String s : travelerHistory) {
                String indexNumber = s.split("\"")[0];
                travelersList.add(tList.get(Integer.valueOf(indexNumber)));
            }
        }


        if (null != travelerSet && travelerSet.size() > 0) {


            for (Traveler t : travelerSet) {
                travelersList.add(t);
            }
        }
        int ticketsNumber =travelersList.size();
        session.setAttribute("travelerList", travelersList);
        session.setAttribute("ticketsNumber",ticketsNumber);
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
                for (int i = 0; i < Integer.valueOf(ticketsNumber); i++) {
            for (Flight flight : flightObjectSet) {
                if (!validateTicketService.isAvaliable(flight, flightdate[flightObjectSet.indexOf(flight)])) {
                    return ERROR;
                }
            }
        }

        return SUCCESS;
    }


    public String delete() {

        try {
            travelerSet = getTravelerSet();
            travelerSet.remove(getRowindex());// removes the passenger object from the list based on the row index.

            short sno = 1;

// S.No is rearranged when a passenger row is deleted.

            for (Traveler t : travelerSet) {
                t.setRowIndex(sno++);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;

//    }
    }

    public int getRowindex() {
        return rowindex;
    }

    public void setRowindex(int rowindex) {
        this.rowindex = rowindex;
    }
}
