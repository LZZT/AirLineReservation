package dao;

import model.Ticket;
import model.Transactions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class TicketDAO {

//    public Traveler getTraveler(String travelerid){
//
//        Traveler traveler = null;
//
//        Session session = HibernateUtil.openSession();
//
//        Transactions tx = session.beginTransaction();
//
//        try{
//
//            traveler = (Traveler) session.get(Traveler.class, travelerid);
//            tx.commit();
//
//        }catch (Exception ex){
//            if(null != tx){
//                tx.rollback();
//            }
//        }finally {
//            HibernateUtil.close(session);
//        }
//
//        return traveler;
//    }

    public void saveTicket(Ticket ticket){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.save(ticket);
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
