package dao;

import model.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class PaymentDAO {

    public Payment getPayment(String cardNumber){

        Payment payment = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            payment = (Payment) session.get(Payment.class, cardNumber);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return payment;
    }

    public void savePayment(Payment payment){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.save(payment);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }
    }


}
