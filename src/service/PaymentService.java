package service;


import dao.PaymentDAO;
import model.Payment;


public class PaymentService {
    public void addNewPayment(Payment payment){
        PaymentDAO paymentDAO= new PaymentDAO();
        paymentDAO.savePayment(payment);
    }
}
