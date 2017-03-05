package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by QQZhao on 3/4/17.
 */

public class LogoutAction extends ActionSupport{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String logout(){

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        session.removeAttribute("username");

        return SUCCESS;
    }


}
