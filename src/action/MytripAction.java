package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.*;
import util.emailValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MytripAction extends ActionSupport {

    private String ticketID;

    private String cardNumber;

    private String travelerPhone;

    public String getTravelerPhone() {
        return travelerPhone;
    }

    public void setTravelerPhone(String travelerPhone) {
        this.travelerPhone = travelerPhone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String cancelTicket() throws Exception{
        TransactionService transactionService = new TransactionService();
        transactionService.UpdateTransactionPrice(ticketID);
        ValidateTicketService validateTicketService = new ValidateTicketService();
        validateTicketService.deleteValidateTicketByTicketID(ticketID);
        TicketService ticketService = new TicketService();
        ticketService.deleteTicketByTicketID(ticketID);
        return SUCCESS;

    }

    public String deleteCard() throws Exception{

        PaymentService paymentService = new PaymentService();
        paymentService.deleteCreditCard(cardNumber);
        return SUCCESS;

    }

    public String deleteTraveler() throws Exception{


        TravelerService travelerService = new TravelerService();
        travelerService.deleteTraveler(travelerPhone);
        return SUCCESS;

    }

    public String updateCard() throws  Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("updateNumber",cardNumber);
        return  SUCCESS;
    }

    public String updateTraveler() throws  Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("updateTraveler",travelerPhone);
        return  SUCCESS;
    }
}
