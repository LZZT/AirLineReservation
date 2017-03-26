package dao;

import model.Transactions;
import model.Traveler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class TransactionDAO {

//    public Traveler getTraveler(String travelerid){
//
//        Traveler traveler = null;
//
//        Session session = HibernateUtil.openSession();
//
//        Transactions tx = session.beginTransaction();
//
//        try{
//
//            traveler = (Traveler) session.get(Traveler.class, travelerid);
//            tx.commit();
//
//        }catch (Exception ex){
//            if(null != tx){
//                tx.rollback();
//            }
//        }finally {
//            HibernateUtil.close(session);
//        }
//
//        return traveler;
//    }

    public void saveTansaction(Transactions transactions){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.save(transactions);
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
