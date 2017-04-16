package dao;


import model.Ticket;
import model.ValidateTicket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.TicketService;
import util.HibernateUtil;


public class ValidateTicketDAO {

    public ValidateTicket getValidateTicket(String flightNumber,String flightDate){

        ValidateTicket validateTicket = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            String hql = String.format("FROM ValidateTicket WHERE flightNumber = '%s' AND flightDate= '%s", flightNumber,flightDate);
            Query query = session.createQuery(hql);
            validateTicket =(ValidateTicket) (query.list().get(0));
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return validateTicket;
    }

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


    public void deleteValidateTicket( String flightNumber, String flightDate) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
            String hql = "DELETE FROM ValidateTicket WHERE flightNumber = :flightNumber AND flightDate=:flightDate ";
            Query query = session.createQuery(hql);
            query.setParameter("flightNumber", flightNumber);
            query.setParameter("flightDate", flightDate);
            query.executeUpdate();
            tx.commit();

        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

    }
    public boolean deleteValidateTicketByTicketID(String ticketID) {

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();


        try {
            TicketDAO ticketDAO = new TicketDAO();
            Ticket ticket =ticketDAO.getTicket(ticketID);
            String flightNumber = ticket.getFlightNumber();
            String flightDate = ticket.getFlightDate();
            int totalTicketNumber=getTotalTicketNumber(flightNumber,flightDate);
            if (totalTicketNumber>0){
            String hql = "UPDATE ValidateTicket set totalTicketNumber = totalTicketNumber-1 WHERE flightDate = :flightDate AND flightNumber=:flightNumber";
            Query query = session.createQuery(hql);
            query.setParameter("flightDate", flightDate);
            query.setParameter("flightNumber", flightNumber);
//            String hql = String.format("DELETE ValidateTicket WHERE flightNumber = '%s' AND flightDate = '%s'", flightNumber,flightDate);
//            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            tx.commit();}

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
