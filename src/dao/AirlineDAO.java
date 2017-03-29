package dao;

import model.Airline;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by yenchanghsieh on 3/25/17.
 */
public class AirlineDAO {
    public List<String> getAirlinesName() {
        List<String> airlineList = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("SELECT name FROM Airline");
            Query query = session.createQuery(hql);

            airlineList = (List<String>) query.list();

            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return airlineList;
    }

    public Airline getAirlineByName(String name) {
        Airline airline = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            airline = (Airline) session.get(Airline.class, name);
            tx.commit();
        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return airline;
    }
}
