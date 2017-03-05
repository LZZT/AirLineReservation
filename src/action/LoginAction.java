package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by QQZhao on 3/4/17.
 */
public class LoginAction extends ActionSupport{

    private String username;
    private String password;
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

    public String login() throws Exception{

        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> sessionMap = actionContext.getSession();
        sessionMap.put("username", username);

        return SUCCESS;
    }


    public void validateLogin(){

        if (null == username || username.length() < 1){
            this.addActionError("username can not be empty");
        }
        else if(!customerService.isCustomerExists(username)){
            this.addActionError("Invalid username!");
        }

        if (null == password || password.length() < 6){
            this.addActionError("Invalid password!");
        }
        else if(!customerService.isPasswordMatchingUsername(username, password)){
            this.addActionError("Invalid password!");
        }
    }

}
