package model;

/**
 * Created by liweihao on 3/8/17.
 */
public class Payment {
    private String cardNumber;
    private String cardFirstname;
    private String cardLastname;
    private String expDate;
    private String cvv;
    private String billingAddress;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardFirstname() {
        return cardFirstname;
    }

    public void setCardFirstname(String cardFirstname) {
        this.cardFirstname = cardFirstname;
    }

    public String getCardLastname() {
        return cardLastname;
    }

    public void setCardLastname(String cardLastname) {
        this.cardLastname = cardLastname;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }


}
