package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Ticket;
import model.Transactions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;
import service.TicketService;
import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liweihao on 3/30/17.
 */
public class MytripAction extends ActionSupport {
    private TransactionService transactionService = new TransactionService();
    public String mytripInfo() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String username= (String) session.getAttribute("username");
//        List<List<Ticket>> transactionsListSet = transactionService.getTransactionAndTicket(username);
//
//        session.setAttribute("transactionsListSet",transactionsListSet);

        return SUCCESS;
    }
}
