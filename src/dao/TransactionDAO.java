package dao;

import model.Transactions;
import model.Traveler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;


public class TransactionDAO {



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

//    public boolean deleteTransaction(String transactionID) {
//
//        Session session = HibernateUtil.openSession();
//
//        Transaction tx = session.beginTransaction();
//
//
//        try {
//
//            String hql = String.format("DELETE Transaction WHERE transactionID = '%s'", transactionID);
//            Query query = session.createQuery(hql);
//            tx.commit();
//
//        } catch (Exception ex) {
//            if (null != tx) {
//                tx.rollback();
//            }
//        } finally {
//            HibernateUtil.close(session);
//        }
//
//        return true;
//    }

    public void deleteTransactionByTransID(String primaryKey){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            Transaction transaction = session.get(Transaction.class, primaryKey);
            session.delete(transaction);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }

        }finally {
            HibernateUtil.close(session);
        }
    }

    public boolean deleteTransaction(String transactionID) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {

            String hql = String.format("DELETE Transaction WHERE transactionID = '%s'", transactionID);
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return true;
    }


}
