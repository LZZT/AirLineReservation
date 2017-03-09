package dao;

import model.Airport;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by QQZhao on 3/5/17.
 */
public class AirportDAO {

    public Airport getAirportByCode(String airportCode){

        Airport airport = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            airport = (Airport) session.get(Airport.class, airportCode);
            tx.commit();
        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return airport;
    }


    public List<Airport> getAirportsByCityName(String cityName){

        List<Airport> airportsList = null;

        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try{

            String hql = String.format("FROM Airport WHERE city = '%s'", cityName);
            Query query = session.createQuery(hql);
            airportsList = (List<Airport>)query.list();
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return airportsList;
    }

    public Airport getAirportByAirportName(String airportName){

        Airport airport = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            String hql = String.format("FROM Airport WHERE name = '%s'", airportName);
            Query query = session.createQuery(hql);
            airport = (Airport)(query.list().get(0));
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return airport;
    }
}
