package dao;

import model.Flight;
import org.apache.struts2.components.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by QQZhao on 3/6/17.
 */
public class FlightDAO {

    public List<Flight> getFlightByAirportsCodeAndDate(String departureAirportCode, String arrivalAirportCode, int weekNum){

        List<Flight> flightsList = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try{

            String hql = String.format("FROM Flight WHERE departureAirport = '%s' AND arrivalAirport = '%s' AND daysOperated like '%%%d%%'", departureAirportCode, arrivalAirportCode, weekNum);
            Query query = session.createQuery(hql);
            flightsList = (List<Flight>)query.list();

            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return flightsList;
    }

}
