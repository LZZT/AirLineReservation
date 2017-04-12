package action;

import com.opensymphony.xwork2.ActionSupport;
import service.CustomerService;
import util.emailValidation;




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

        return SUCCESS;

    }

    public void validateUserInfo(){

        if (null == username || username.length() < 1){
            this.addActionError("username can not be empty");
        }

        if (!emailValidation.emailValidate(email)){
            this.addActionError("Invalid email address");
        }

        if (reemail == null || !email.equals(reemail)){
            this.addActionError("Email address should be the same!");
        }

        if (!customerService.isCustomerExists(username)){
            this.addActionError("username not exists");
        }
    }
}
