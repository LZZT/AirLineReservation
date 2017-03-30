package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.CustomerDAO;
import model.Customer;
import org.apache.struts2.ServletActionContext;
import service.CustomerService;
import util.emailValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterAction extends ActionSupport {

    private String username;
    private String password;
    private String repassword;
    private String email;
    private CustomerService customerService = new CustomerService();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String register() {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setContactEmail(email);
        customer.setcBonus(0);
        customerService.registerNewCustomer(customer);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("customer",customer);
        session.setAttribute("username", username);

        return SUCCESS;
    }

    public void validateRegister(){

        if (null == username || username.length() < 1){
            this.addActionError("username can not be empty");
        }

        if (null == password || password.length() < 6){
            this.addActionError("password length must be longer than 6");

        }else if (! password.equals(repassword)){
            this.addActionError("passworld not same as re-password");
        }

        if (!emailValidation.emailValidate(email)){
            this.addActionError("Invalid email address");
        }

        if (customerService.isCustomerExists(username)){
            this.addActionError("username already exists");
        }
    }


}
