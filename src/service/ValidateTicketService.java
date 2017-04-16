package service;


import dao.FlightDAO;
import dao.TransactionDAO;
import dao.ValidateTicketDAO;
import model.Flight;
import model.Transactions;
import model.ValidateTicket;


public class ValidateTicketService {
    public ValidateTicket getValidateTicket(String flightNumber,String flightDate){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        return validateTicketDAO.getValidateTicket(flightNumber,flightDate);

    }

    public void deleteValidateTicket(String flightNumber,String flightDate){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        validateTicketDAO.deleteValidateTicket(flightNumber,flightDate);

    }

    public void recordValidateTicket(ValidateTicket validateTicket){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        validateTicketDAO.saveValidateTicket(validateTicket);
    }

    public int getTotalTicketNumber(String flightNumber,String flightDate){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        return  validateTicketDAO.getTotalTicketNumber(flightNumber,flightDate);
    }

    public boolean updateValidateTicket(int totalTicketNumber,String flightNumber, String flightDate){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        return  validateTicketDAO.updateValidateTicket(totalTicketNumber,flightNumber,flightDate);
    }

    public int getCapacity(String aircraftmodel){
        FlightDAO flightDAO = new FlightDAO();
        return  flightDAO.getCapacity(aircraftmodel);
    }

    public boolean isAvaliable(Flight flight, String departingDate){
        int capacity=getCapacity(flight.getAircraftModel().getModel());
        int recordNumber= getTotalTicketNumber(flight.getFlightNumber(),departingDate);
        if (recordNumber!=0) {
            if (recordNumber >=capacity) {
                return false;
            }
        }
        return true;
    }
    public void deleteValidateTicketByTicketID(String  ticketID){
        ValidateTicketDAO validateTicketDAO = new ValidateTicketDAO();
        validateTicketDAO.deleteValidateTicketByTicketID(ticketID);
    }
}
