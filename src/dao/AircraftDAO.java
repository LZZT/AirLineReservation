package dao;

import model.Aircraft;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by yenchanghsieh on 3/25/17.
 */
public class AircraftDAO {
    public List<String> getAircraftModel() {
        List<String> aircraftList = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("SELECT model FROM Aircraft");
            Query query = session.createQuery(hql);

            aircraftList = (List<String>) query.list();

            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return aircraftList;
    }

    public Aircraft getAircraftByModel(String model) {
        Aircraft aircraft = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            aircraft = (Aircraft) session.get(Aircraft.class, model);
            tx.commit();
        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return aircraft;
    }
}
