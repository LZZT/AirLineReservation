package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Flight;
import org.apache.struts2.ServletActionContext;
import service.SearchInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by QQZhao on 3/9/17.
 */
public class CartAction extends ActionSupport{

    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


    @Override
    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        SearchInfoService searchInfoService = new SearchInfoService();
        String validIndex = index.split("/")[0];
        List<Flight> chosenReturningFlight = ((List<List<Flight>>)session.getAttribute("validReturningFlights")).get(Integer.valueOf(validIndex));
        session.setAttribute("returningFlightObjectSet", chosenReturningFlight);
        return SUCCESS;

    }
}
