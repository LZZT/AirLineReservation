package service;


import dao.FlightDAO;
import dao.TicketDAO;
import dao.TravelerDAO;
import model.Ticket;
import model.Traveler;


public class TicketService {
    public void addNewTicket(Ticket  ticket){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
    }
    public void deleteTicketByTransID(String  transactionID){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.deleteTicketByTransID(transactionID);
    }

}
