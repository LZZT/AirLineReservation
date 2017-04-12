package service;


import dao.FlightDAO;
import dao.TicketDAO;
import dao.TravelerDAO;
import model.Flight;
import model.Ticket;
import model.Traveler;

import java.util.List;
import java.util.Set;


public class TicketService {
    public void addNewTicket(Ticket  ticket){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
    }
    public void deleteTicketByTransID(String  transactionID){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.deleteTicketByTransID(transactionID);
    }
    public Flight getSingleFlightByFlightNumber(String flightNumber){
        FlightDAO flightDAO = new FlightDAO();
        return flightDAO.getSingleFlightByFlightNumber(flightNumber);
    }

    public List<Ticket> getTicketByTransaction(String transactionID){
        TravelerService travelerService = new TravelerService();
        TicketDAO ticketDAO = new TicketDAO();
        List<Ticket> ticketList= ticketDAO.getTicketByTransaction(transactionID);
        for (Ticket ticket:ticketList){
            Traveler traveler = travelerService.getTraveler(ticket.getTravellerID());
            ticket.setFirstName(traveler.getFirstname());
            ticket.setLastName(traveler.getLastname());
            ticket.setDepartureCity(getSingleFlightByFlightNumber(ticket.getFlightNumber()).getDepartureAirport().getCity().getCity());
            ticket.setArrivalCity(getSingleFlightByFlightNumber(ticket.getFlightNumber()).getArrivalAirport().getCity().getCity());
            ticket.setPrice(getSingleFlightByFlightNumber(ticket.getFlightNumber()).getPrice());
        }
        return  ticketList;
    }
    public void deleteTicketByTicketID(String  ticketID){
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.deleteTicketByTicketID(ticketID);
    }
    public Ticket getTicket(String ticketID){
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getTicket(ticketID);
    }

}
