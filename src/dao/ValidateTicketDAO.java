package dao;


import model.ValidateTicket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;


public class ValidateTicketDAO {

    public int getTotalTicketNumber(String flightNumber, String flightDate) {
        Session session = HibernateUtil.openSession();
        int totalTicketNumber = 0;
        Transaction tx = session.beginTransaction();

        try {

            String hql = String.format("SELECT V.totalTicketNumber FROM ValidateTicket V WHERE V.flightNumber = '%s' AND V.flightDate = '%s'", flightNumber, flightDate);
            Query query = session.createQuery(hql);
            totalTicketNumber = (int) (query.list().get(0));
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return totalTicketNumber;
    }


    public void saveValidateTicket(ValidateTicket validateTicket) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try {
            session.save(validateTicket);
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }


    public boolean updateValidateTicket(int totalTicketNumber, String flightNumber, String flightDate) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
            String hql = "UPDATE ValidateTicket set totalTicketNumber = :totalTicketNumber WHERE flightNumber = :flightNumber AND flightDate=:flightDate ";
            Query query = session.createQuery(hql);
            query.setParameter("totalTicketNumber", totalTicketNumber);
            query.setParameter("flightNumber", flightNumber);
            query.setParameter("flightDate", flightDate);
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
