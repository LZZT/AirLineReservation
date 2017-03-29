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




}
