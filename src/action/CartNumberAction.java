package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by liweihao on 3/9/17.
 */
public class CartNumberAction extends ActionSupport {

    private String ticketsNumber;

    public String getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(String ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public String cart() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("ticketsNumber", ticketsNumber);

        return SUCCESS;
    }


}
