package action;

import com.opensymphony.xwork2.ActionSupport;
import service.*;
import util.emailValidation;


public class MytripAction extends ActionSupport {

    private String ticketID;

    private String cardNumber;

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
        paymentService.deleteCardbyCardNumber(cardNumber);
        return SUCCESS;

    }


}
