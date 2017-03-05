package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.TestUserDAO;
import model.TestUser;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by QQZhao on 2/28/17.
 */
public class TestAction extends ActionSupport{

    private String primaryKey;
    private String username;
    private String password;
    private String returnedUsername;
    private String returnedPassword;
    private String returnedPrimaryKey;

    public String getReturnedUsername() {
        return returnedUsername;
    }

    public void setReturnedUsername(String returnedUsername) {
        this.returnedUsername = returnedUsername;
    }

    public String getReturnedPassword() {
        return returnedPassword;
    }

    public void setReturnedPassword(String returnedPassword) {
        this.returnedPassword = returnedPassword;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUsername() { return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReturnedPrimaryKey() {
        return returnedPrimaryKey;
    }

    public void setReturnedPrimaryKey(String returnedPrimaryKey) {
        this.returnedPrimaryKey = returnedPrimaryKey;
    }

    public String saveAndGetTestUserBack(){

        TestUser testUser = new TestUser();
        testUser.setUsername(username);
        testUser.setPassword(password);
        testUser.setPrimaryKey(Integer.valueOf(primaryKey));


        TestUserDAO testUserDAO = new TestUserDAO();

        testUserDAO.saveTestUser(testUser);

        TestUser returnedTestUser = testUserDAO.getTestUser(Integer.valueOf(primaryKey));
        returnedPrimaryKey = String.valueOf(returnedTestUser.getPrimaryKey());
        returnedUsername = returnedTestUser.getUsername();
        returnedPassword = returnedTestUser.getPassword();

        return SUCCESS;
    }

    public String deleteTestUser(){
        TestUserDAO testUserDAO = new TestUserDAO();
        System.out.println("======================" + primaryKey);
        testUserDAO.deleteTestUser(Integer.valueOf(primaryKey));
        return SUCCESS;
    }

}
