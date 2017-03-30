package service;


import dao.PaymentDAO;
import model.Payment;


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
}
