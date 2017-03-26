package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Flight;
import model.Payment;
import model.Transactions;
import model.Traveler;
import model.Ticket;
import org.apache.struts2.ServletActionContext;
import service.PaymentService;
import service.TicketService;
import service.TransactionService;
import service.TravelerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.util.Date;
import java.util.List;


public class TransactionAction extends ActionSupport {
    private TravelerService travelerService = new TravelerService();
    private PaymentService paymentService = new PaymentService();
    private TransactionService transactionService = new TransactionService();
    private TicketService ticketService = new TicketService();
    public String TransInfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String departingDate=(String )session.getAttribute("departingDate");
        String returningDate=(String )session.getAttribute("returningDate");
        int leavingPrice=(int)session.getAttribute("leavingPrice");
        int ticketsNumber=Integer.valueOf((String)session.getAttribute("ticketsNumber"));
        int totalprice=0;
        try{
            int returningPrice=(int)session.getAttribute("returningPrice");
            totalprice=(leavingPrice+returningPrice)*ticketsNumber;
        }
        catch (Exception ex){
            totalprice=leavingPrice*ticketsNumber;
        }


        Payment payment = (Payment)session.getAttribute("payment");
        paymentService.addNewPayment(payment);
        List<Traveler> travelerList=( List<Traveler>)session.getAttribute("travelerList");

        List<Flight> leavingFlightObjectSet=(List<Flight>)session.getAttribute("leavingFlightObjectSet");
//        int totalprice=0;
        Transactions transactions = new Transactions();
        transactions.setCardnumber(payment.getCardNumber());
        transactions.setUsername((String) session.getAttribute("username"));
        UID uid=new UID();
        System.out.println(uid.toString());
        transactions.setTransactionID(uid.toString().substring(uid.toString().length()-10));
        transactions.setPrice(totalprice);
        Date date=new Date();
        transactions.setTransactionDate(String.format("%tF",date));
        transactionService.addNewTransaction(transactions);

        int i=0;
        for(Traveler t : travelerList){
            travelerService.registerNewTraveler(t);

            for (Flight flight:leavingFlightObjectSet){


//                totalprice+=flight.getPrice();
                Ticket ticket = new Ticket();
                ticket.setFlightNumber(flight.getFlightNumber());
                ticket.setTransactionID(uid.toString().substring(uid.toString().length()-10));
                ticket.setFlightDate(departingDate);
                ticket.setTicketID(uid.toString().substring(uid.toString().length()-5)+String.valueOf(i));
                ticket.setTravellerID(t.getPhone());
                ticketService.addNewTicket(ticket);
                i++;
            }
            try{
                List<Flight> returningFlightObjectSet=(List<Flight>)session.getAttribute("returningFlightObjectSet");
                for (Flight flight:returningFlightObjectSet){
//                    totalprice+=flight.getPrice();
                    Ticket ticket = new Ticket();
                    ticket.setFlightNumber(flight.getFlightNumber());
                    ticket.setTransactionID(uid.toString().substring(uid.toString().length()-10));
                    ticket.setFlightDate(returningDate);
                    ticket.setTicketID(uid.toString().substring(uid.toString().length()-5)+String.valueOf(i));
                    ticket.setTravellerID(t.getPhone());
                    ticketService.addNewTicket(ticket);
                    i++;
                }
            }catch (Exception ex){}
        }







        return SUCCESS;
    }

}


