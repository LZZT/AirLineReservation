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
    private Set<Traveler> TravelerSet;
    private Set<Payment> PaymentSet;

    public Customer() {
    }

    public Set<Payment> getPaymentSet() {
        return PaymentSet;
    }

    public void setPaymentSet(Set<Payment> paymentSet) {
        PaymentSet = paymentSet;
    }

    public Customer(String username) {
        this.username = username;
    }

    public void setTraveler(Traveler traveler){
        this.TravelerSet.add(traveler);
    }

    public Set<Traveler> getTravelerSet() {
        return TravelerSet;
    }

    public void setTravelerSet(Set<Traveler> travelerSet) {
        TravelerSet = travelerSet;
    }
    // leaving and returning Flights in Chart does not need to go to database;
    // It comes with session (~30 minutes or until user logout)

    private Set<Transactions> transactionsSet;

    public void setTransactionsSet(Set<Transactions> transactionsSet) {
        this.transactionsSet = transactionsSet;
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

    public Set<Transactions> getTransactionsSet() {
        return transactionsSet;
    }
}
