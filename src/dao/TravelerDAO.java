package dao;

import model.Traveler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;


public class TravelerDAO {

    public Traveler getTraveler(String travelerid){

        Traveler traveler = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            traveler =  session.get(Traveler.class, travelerid);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return traveler;
    }

    public void saveTraveler(Traveler traveler){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            if (traveler!=getTraveler(traveler.getPhone())){
                session.save(traveler);
                tx.commit();
            }


        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }
    }

    public boolean deleteTraveler(String phone) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {

            String hql = String.format("DELETE Traveller WHERE contactPhone = '%s'", phone);
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
