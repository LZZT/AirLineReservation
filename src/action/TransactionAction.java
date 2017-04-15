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
    private CustomerService customerService = new CustomerService();
    private ValidateTicketService validateTicketService = new ValidateTicketService();
    public String TransInfo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String departingDate = (String) session.getAttribute("departingDate");
        String returningDate = (String) session.getAttribute("returningDate");

        int leavingPrice = (int) session.getAttribute("leavingPrice");
        int ticketsNumber = (Integer) session.getAttribute("ticketsNumber");
        int totalprice = 0;
        try {
            int returningPrice = (int) session.getAttribute("returningPrice");
            totalprice = (leavingPrice + returningPrice) * ticketsNumber;
        } catch (Exception ex) {
            totalprice = leavingPrice * ticketsNumber;
        }

        Payment payment = (Payment) session.getAttribute("payment");
        if (!paymentService.isPaymentExists(payment.getCardNumber()))
            paymentService.addNewPayment(payment);
        List<Traveler> travelerList = (List<Traveler>) session.getAttribute("travelerList");
        List<Flight> leavingFlightObjectSet = (List<Flight>) session.getAttribute("leavingFlightObjectSet");
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
            String transactionID=uid.toString().substring(uid.toString().length() - 10);
            transactions.setTransactionID(transactionID);
            transactions.setPrice(totalprice);
            Date date = new Date();
            transactions.setTransactionDate(String.format("%tF", date));
            transactionService.addNewTransaction(transactions);
            int i = 0;
            for (Traveler t : travelerList) {
                if (!travelerService.isTravelerExists(t.getPhone())){
                    travelerService.registerNewTraveler(t);
                }
                for (Flight flight : leavingFlightObjectSet) {
                    int recordNumber = validateTicketService.getTotalTicketNumber(flight.getFlightNumber(), flightdate[leavingFlightObjectSet.indexOf(flight)]);
                    if (recordNumber == 0) {
                        ValidateTicket validateTicket = new ValidateTicket();
                        validateTicket.setFlightNumber(flight.getFlightNumber());
                        validateTicket.setFlightDate(flightdate[leavingFlightObjectSet.indexOf(flight)]);
                        validateTicket.setCapacity(validateTicketService.getCapacity(flight.getAircraftModel().getModel()));
                        validateTicket.setTotalTicketNumber(1);
                        validateTicketService.recordValidateTicket(validateTicket);
                    } else {
                        validateTicketService.updateValidateTicket(recordNumber + 1, flight.getFlightNumber(), flightdate[leavingFlightObjectSet.indexOf(flight)]);
                    }



                    Ticket ticket = new Ticket();
                    ticket.setFlightNumber(flight.getFlightNumber());
                    ticket.setTransactionID(transactionID);
                    ticket.setFlightDate(flightdate[leavingFlightObjectSet.indexOf(flight)]);
                    ticket.setTicketID(transactionID.substring(2,4)+transactionID.substring(7,9) + String.valueOf(i));
                    ticket.setTravellerID(t.getPhone());
                    ticketService.addNewTicket(ticket);
                    i++;
                }
            }
        return SUCCESS;
        }



}


