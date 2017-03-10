package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Payment;
import org.apache.struts2.ServletActionContext;
import service.PaymentService;
import service.TravelerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class TransAction extends ActionSupport {
    private TravelerService travelerService = new TravelerService();
    private PaymentService paymentService = new PaymentService();
    public String TransInfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Payment payment = (Payment)session.getAttribute("payment");
        paymentService.addNewPayment(payment);








        return SUCCESS;
    }

}
