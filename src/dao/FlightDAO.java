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

    public List<Flight> getFlightByFlightNumber(String flightNumber) {
        List<Flight> flightsList = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try{

            String hql = String.format("FROM Flight WHERE flightNumber = '%s'", flightNumber);
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

    public boolean saveFlight(Flight flight){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try{
            session.save(flight);

            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return true;
    }

    public boolean updateFlight(Flight flight){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try{
            String hql = "UPDATE Flight set price = :price, daysOperated = :daysOperated, departureAirport = :departureAirport WHERE flightNumber = :flightNumber";
            Query query = session.createQuery(hql);
            query.setParameter("flightNumber", flight.getFlightNumber());
            /*
            query.setParameter("departureTime", flight.getDepartureTime());
            query.setParameter("arrivalTime", flight.getArrivalTime());
            query.setParameter("airline", flight.getAirline().getCode());
            query.setParameter("aircraftModel", flight.getAircraftModel().getModel());
            */
            query.setParameter("departureAirport", flight.getDepartureAirport().getCode());
            //query.setParameter("arrivalAirport", flight.getArrivalAirport().getCode());
            query.setParameter("daysOperated", flight.getDaysOperated());
            query.setParameter("price", flight.getPrice());
            int result = query.executeUpdate();
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return true;
    }

    public boolean deleteFlight(String flightNumber){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try{

            String hql = String.format("DELETE Flight WHERE flightNumber = '%s'", flightNumber);
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return true;
    }

}
