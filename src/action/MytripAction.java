package action;

import com.opensymphony.xwork2.ActionSupport;
import service.CustomerService;
import service.TicketService;
import service.TransactionService;
import service.ValidateTicketService;
import util.emailValidation;


public class MytripAction extends ActionSupport {

    private String ticketID;

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


}
