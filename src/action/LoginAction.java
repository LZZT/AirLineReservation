package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Ticket;
import org.apache.struts2.ServletActionContext;
import service.CustomerService;
import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by QQZhao on 3/4/17.
 */
public class LoginAction extends ActionSupport{

    private String username;
    private String password;
    private CustomerService customerService = new CustomerService();
    private TransactionService transactionService = new TransactionService();

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() throws Exception{

        if (null == username || username.length() < 1){
            this.addActionError("username can not be empty");
            return INPUT;
        }
        else if(!customerService.isCustomerExists(username)){
            this.addActionError("Invalid username!");
            return INPUT;
        }

        if (null == password || password.length() < 6){
            this.addActionError("Invalid password!");
            return INPUT;
        }
        else if(!customerService.isPasswordMatchingUsername(username, password)){
            this.addActionError("Invalid password!");
            return INPUT;
        }
        
        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> sessionMap = actionContext.getSession();
        sessionMap.put("username", username);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
//        List<List<Ticket>> transactionsListSet = transactionService.getTransactionAndTicket(username);
//        session.setAttribute("transactionsListSet",transactionsListSet);
        if (null ==session.getAttribute("leavingFlightObjectSet") && null==session.getAttribute("returningFlightObjectSet"))
            return SUCCESS;
        else
            return NONE;
    }

}
