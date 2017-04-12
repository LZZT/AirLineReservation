package service;


import dao.PaymentDAO;
import dao.TicketDAO;
import dao.TransactionDAO;
import model.Payment;
import model.Ticket;
import model.Transactions;
import model.Traveler;

import java.util.ArrayList;
import java.util.List;


public class PaymentService {
    public void addNewPayment(Payment payment){
        PaymentDAO paymentDAO= new PaymentDAO();
        paymentDAO.savePayment(payment);
    }
    public void deletePayment(String cardNumber){
        PaymentDAO paymentDAO= new PaymentDAO();
        paymentDAO.deletePayment(cardNumber);
    }

    public boolean isPaymentExists(String cardNumber){

        PaymentDAO paymentDAO= new PaymentDAO();

        Payment payment = paymentDAO.getPayment(cardNumber);

        if (payment == null){
            return false;
        }
        return true;
    }



    public List<Payment> getCreditCardByUsername(String username){

        PaymentDAO paymentDAO = new PaymentDAO();
        List<Payment> payments = paymentDAO.getPaymentInfoByUsername(username);
        return payments;
    }
}
