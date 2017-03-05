package model;

/**
 * Created by QQZhao on 3/3/17.
 */
public class Customer {

    private String username;
    private String password;
    private String contactEmail;
    private int cBonus;

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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getcBonus() {
        return cBonus;
    }

    public void setcBonus(int cBonus) {
        this.cBonus = cBonus;
    }
}
