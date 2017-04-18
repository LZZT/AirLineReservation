package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Customer;
import model.Flight;
import model.Payment;
import model.Traveler;

import org.apache.struts2.ServletActionContext;
import service.CustomerService;
import service.TravelerService;
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
    private CustomerService customerService = new CustomerService();

    private String phone;
    private String lastname;
    private String firstname;
    private String gender;
    private String dob;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
                if (t.getLastname().length() != 0) {
                    travelersList.add(t);
                }
            }
        }
        String username = (String) session.getAttribute("username");
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customerService.getCustomer(username));
        for (Traveler traveler : travelersList) {
            traveler.setCustomerSet(customerSet);
        }
        int ticketsNumber = travelersList.size();
        session.setAttribute("travelerList", travelersList);
        session.setAttribute("ticketsNumber", ticketsNumber);
        String departingDate = (String) session.getAttribute("departingDate");
        String returningDate = (String) session.getAttribute("returningDate");
        List<Flight> leavingFlightObjectSet = (List<Flight>) session.getAttribute("leavingFlightObjectSet");
//        int leavingSetSize = leavingFlightObjectSet.size();
        List<Flight> returningFlightObjectSet = (List<Flight>) session.getAttribute("returningFlightObjectSet");
//        List<Flight> flightObjectSet = (List) ((ArrayList<Flight>) leavingFlightObjectSet).clone();
//        if (returningFlightObjectSet != null) {
//            flightObjectSet.addAll(returningFlightObjectSet);
//        }


        for (Flight flight : leavingFlightObjectSet) {
            int totalTicketNumber = validateTicketService.getTotalTicketNumber(flight.getFlightNumber(), departingDate);
            int remain = validateTicketService.getCapacity(flight.getAircraftModel().getModel()) - totalTicketNumber;
            if (remain < ticketsNumber) {
                return ERROR;
            }

        }

        if (returningFlightObjectSet != null) {
            for (Flight flight : returningFlightObjectSet) {
                int totalTicketNumber = validateTicketService.getTotalTicketNumber(flight.getFlightNumber(), returningDate);
                int remain = validateTicketService.getCapacity(flight.getAircraftModel().getModel()) - totalTicketNumber;
                if (remain < ticketsNumber) {
                    return ERROR;
                }

            }
        }


//        String[] flightdate = new String[flightObjectSet.size()];
//        for (int i = 0; i < flightObjectSet.size(); i++) {
//            if (i < leavingSetSize) {
//                flightdate[i] = departingDate;
//            } else {
//                flightdate[i] = returningDate;
//            }
//        }
//        for (int i = 0; i < Integer.valueOf(ticketsNumber); i++) {
//            for (Flight flight : flightObjectSet) {
//                if (!validateTicketService.isAvaliable(flight, flightdate[flightObjectSet.indexOf(flight)])) {
//                    return ERROR;
//                }
//            }
//        }

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


    public String updateTraveler() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String phonenumber = (String) session.getAttribute("updateTraveler");
        TravelerService travelerService = new TravelerService();
        Traveler traveler = travelerService.getTraveler(phonenumber);
        traveler.setLastname(lastname);
        traveler.setFirstname(firstname);
        traveler.setEmail(email);
        traveler.setDob(dob);
        traveler.setGender(gender);
        String username = (String) session.getAttribute("username");
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customerService.getCustomer(username));
        travelerService.updateTravelerInfo(traveler);
        return SUCCESS;
    }

    public String addTraveler() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        TravelerService travelerService = new TravelerService();
        Traveler traveler = new Traveler();
        traveler.setPhone(phone);
        traveler.setLastname(lastname);
        traveler.setFirstname(firstname);
        traveler.setEmail(email);
        traveler.setDob(dob);
        traveler.setGender(gender);
        String username = (String) session.getAttribute("username");
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(customerService.getCustomer(username));
        traveler.setCustomerSet(customerSet);
        travelerService.addTraveler(traveler);
        return SUCCESS;
    }
}
