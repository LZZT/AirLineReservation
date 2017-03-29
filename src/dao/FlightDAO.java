package dao;

import model.Flight;
import org.apache.struts2.components.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by QQZhao on 3/6/17.
 */
public class FlightDAO {

    public List<List<Flight>> getNoneStopFlightByAirportsCodeAndDate(String departureAirportCode, String arrivalAirportCode, int weekNum) {

        List<List<Flight>> flightsList = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("FROM Flight WHERE departureAirport = '%s' AND arrivalAirport = '%s' AND daysOperated like '%%%d%%'", departureAirportCode, arrivalAirportCode, weekNum);
            Query query = session.createQuery(hql);

            List<Flight> flightListAsSingleItem = (List<Flight>) query.list();
            flightsList = new ArrayList<>();
            for (Flight f : flightListAsSingleItem) {
                List<Flight> fList = new ArrayList<>();
                fList.add(f);
                flightsList.add(fList);
            }

            tx.commit();


        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return flightsList;
    }

    public List<List<Flight>> getOneStopFlightByAirportsCodeAndDate(String departureAirportCode, String arrivalAirportCode, int weekNum) {

        List<List<Flight>> flightsList = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("SELECT f1.flightNumber, f2.flightNumber FROM Flight f1, Flight f2 WHERE f1.departureAirport = '%s' AND f1.arrivalAirport != '%s' AND f1.daysOperated like '%%%d%%' AND f1.arrivalAirport = f2.departureAirport AND f2.arrivalAirport = '%s' AND f1.arrivalTime < f2.departureTime",
                    departureAirportCode, arrivalAirportCode, weekNum, arrivalAirportCode);

            String hql2 = "SELECT f1.flightNumber, f2.flightNumber FROM Flight f1, Flight f2 WHERE f1.flightNumber = f2.flightNumber";
            Query query = session.createQuery(hql);
            List<Object[]> objArrayList = query.list();
            flightsList = new ArrayList<>();
            for (Object[] objectArray : objArrayList) {
                List<Flight> arrayToList = new ArrayList<>();
                for (Object f : objectArray) {
                    arrayToList.add(getSingleFlightByFlightNumber((String) f));
                }
                flightsList.add(arrayToList);
            }

            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return flightsList;
    }

    public List<Flight> getFlightByFlightNumber(String flightNumber) {
        List<Flight> flightsList = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("FROM Flight WHERE flightNumber = '%s'", flightNumber);
            Query query = session.createQuery(hql);
            flightsList = (List<Flight>) query.list();

            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return flightsList;
    }

    public List<List<Flight>> getTwoStopFlightByAirportsCodeAndDate(String departureAirportCode, String arrivalAirportCode, int weekNum) {
        List<List<Flight>> flightsList = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();


        return flightsList;
    }

    public Flight getSingleFlightByFlightNumber(String flightNumber) {
        Flight flight = null;
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {
            flight = (Flight) session.get(Flight.class, flightNumber);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return flight;
    }


    public boolean saveFlight(Flight flight) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
            session.save(flight);

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

    public boolean updateFlight(Flight flight) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
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

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return true;
    }

    public boolean deleteFlight(String flightNumber) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {

            String hql = String.format("DELETE Flight WHERE flightNumber = '%s'", flightNumber);
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

    public int getCapacity(String aircraftModel) {
        Session session = HibernateUtil.openSession();
        int capacity = 0;
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("SELECT A.capacity FROM Aircraft A WHERE A.model = '%s' ", aircraftModel );
            Query query = session.createQuery(hql);
            capacity = (int) (query.list().get(0));
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return capacity;
    }



}
