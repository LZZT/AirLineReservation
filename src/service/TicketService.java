package service;


import dao.TicketDAO;
import dao.TravelerDAO;
import model.Ticket;
import model.Traveler;


public class TicketService {
    public void addNewTicket(Ticket  ticket){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
}
}
