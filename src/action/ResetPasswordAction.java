package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Customer;
import org.apache.struts2.ServletActionContext;
import service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by tonggezhu on 3/29/17.
 */
public class ResetPasswordAction extends ActionSupport {

    private String username;
    private String email;
    private String reemail;
    private CustomerService customerService = new CustomerService();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReemail() {
        return reemail;
    }

    public void setReemail(String reemail) {
        this.reemail = reemail;
    }

    public String UserInfo() throws Exception{

//        Customer customer = new Customer();
//        customer.setUsername(username);
//        customer.setContactEmail(email);
//        customer.setPassword("");
//        customer.setcBonus(0);
//
//
//        HttpServletRequest request = ServletActionContext.getRequest();
//
//        HttpSession session = request.getSession();
//        session.setAttribute("username", username);
        return SUCCESS;

    }

    public void validateUserInfo(){

        if (null == username || username.length() < 1){
            this.addActionError("username can not be empty");
        }

        if (!email.contains("@") || !email.split("@")[1].contains(".")){
            this.addActionError("Invalid email address");
        }

        if (!reemail.contains("@") || !reemail.split("@")[1].contains(".")){
            this.addActionError("Invalid email address");
        }

        if (!email.equals(reemail)){
            this.addActionError("Email address should be the same!");
        }

        if (!customerService.isCustomerExists(username)){
            this.addActionError("username not exists");
        }
    }
}
