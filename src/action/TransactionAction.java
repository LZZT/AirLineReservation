package action;

import com.opensymphony.xwork2.ActionSupport;
import model.*;
import org.apache.struts2.ServletActionContext;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.rmi.server.UID;
import java.util.Date;
import java.util.List;


public class TransactionAction extends ActionSupport {
    private TravelerService travelerService = new TravelerService();
    private PaymentService paymentService = new PaymentService();
    private TransactionService transactionService = new TransactionService();
    private TicketService ticketService = new TicketService();
    public String TransInfo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String departingDate = (String) session.getAttribute("departingDate");
        String returningDate = (String) session.getAttribute("returningDate");
        int leavingPrice = (int) session.getAttribute("leavingPrice");
        int ticketsNumber = Integer.valueOf((String) session.getAttribute("ticketsNumber"));
        int totalprice = 0;
        try {
            int returningPrice = (int) session.getAttribute("returningPrice");
            totalprice = (leavingPrice + returningPrice) * ticketsNumber;
        } catch (Exception ex) {
            totalprice = leavingPrice * ticketsNumber;
        }

        Payment payment = (Payment) session.getAttribute("payment");
        paymentService.addNewPayment(payment);
        List<Traveler> travelerList = (List<Traveler>) session.getAttribute("travelerList");
        List<Flight> leavingFlightObjectSet = (List<Flight>) session.getAttribute("leavingFlightObjectSet");
        for (Flight f:leavingFlightObjectSet){
            System.out.println(f.getFlightNumber());
        }
        int leavingSetSize=leavingFlightObjectSet.size();
        List<Flight> returningFlightObjectSet = (List<Flight>) session.getAttribute("returningFlightObjectSet");
        if (returningFlightObjectSet != null) {
            leavingFlightObjectSet.addAll(returningFlightObjectSet);
        }
        String[] flightdate=new String[leavingFlightObjectSet.size()];
        for (int i=0;i<leavingFlightObjectSet.size();i++){
            if (i<leavingSetSize){
            flightdate[i]=departingDate;}
            else {
                flightdate[i]=returningDate;
            }
        }

            Transactions transactions = new Transactions();
            transactions.setCardnumber(payment.getCardNumber());
            transactions.setUsername((String) session.getAttribute("username"));
            UID uid = new UID();
            transactions.setTransactionID(uid.toString().substring(uid.toString().length() - 10));
            transactions.setPrice(totalprice);
            Date date = new Date();
            transactions.setTransactionDate(String.format("%tF", date));
            transactionService.addNewTransaction(transactions);
            int i = 0;
            for (Traveler t : travelerList) {
                travelerService.registerNewTraveler(t);
                for (Flight flight : leavingFlightObjectSet) {
                    Ticket ticket = new Ticket();
                    ticket.setFlightNumber(flight.getFlightNumber());
                    ticket.setTransactionID(uid.toString().substring(uid.toString().length() - 10));
                    ticket.setFlightDate(flightdate[leavingFlightObjectSet.indexOf(flight)]);
                    ticket.setTicketID(uid.toString().substring(uid.toString().length() - 5) + String.valueOf(i));
                    ticket.setTravellerID(t.getPhone());
                    ticketService.addNewTicket(ticket);
                    i++;
                }
            }
        return SUCCESS;
        }



}


