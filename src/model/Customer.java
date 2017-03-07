package model;

import java.util.Set;

/**
 * Created by QQZhao on 3/3/17.
 */
public class Customer {

    private String username;
    private String password;
    private String contactEmail;
    private int cBonus;

    // leaving and returning Flights in Chart does not need to go to database;
    // It comes with session (~30 minutes or until user logout)

    private Set<Flight> leavingFlightsInChart;
    private Set<Flight> returningFlightsInChart;

    public Set<Flight> getLeavingFlightsInChart() {
        return leavingFlightsInChart;
    }

    public void setLeavingFlightsInChart(Set<Flight> leavingFlightsInChart) {
        this.leavingFlightsInChart = leavingFlightsInChart;
    }

    public Set<Flight> getReturningFlightsInChart() {
        return returningFlightsInChart;
    }

    public void setReturningFlightsInChart(Set<Flight> returningFlightsInChart) {
        this.returningFlightsInChart = returningFlightsInChart;
    }

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
