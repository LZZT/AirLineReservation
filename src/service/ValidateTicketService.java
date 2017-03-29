package service;


import dao.FlightDAO;
import dao.TransactionDAO;
import dao.ValidateTicketDAO;
import model.Flight;
import model.Transactions;
import model.ValidateTicket;


public class ValidateTicketService {
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
            if (recordNumber < capacity) {
             updateValidateTicket(recordNumber + 1, flight.getFlightNumber(), departingDate);
            } else {
                return false;
            }
        }
        else{
            ValidateTicket validateTicket = new ValidateTicket();
            validateTicket.setFlightNumber(flight.getFlightNumber());
            validateTicket.setFlightDate(departingDate);
            validateTicket.setCapacity(capacity);
            validateTicket.setTotalTicketNumber(1);
            recordValidateTicket(validateTicket);
        }

        return true;
    }
}
