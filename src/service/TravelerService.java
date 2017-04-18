package service;


import dao.PaymentDAO;
import dao.TravelerDAO;
import model.Payment;
import model.Traveler;

import java.util.List;


public class TravelerService {
    public void registerNewTraveler(Traveler traveler) {
        TravelerDAO travelerDAO = new TravelerDAO();
        travelerDAO.saveTraveler(traveler);
    }

    public void deleteTraveler(String phone) {
        TravelerDAO travelerDAO = new TravelerDAO();
        travelerDAO.deleteTraveler(phone);
    }

    public boolean isTravelerExists(String phone) {

        TravelerDAO travelerDAO = new TravelerDAO();

        Traveler traveler = travelerDAO.getTraveler(phone);

        if (traveler == null) {
            return false;
        }
        return true;
    }

    public Traveler getTraveler(String phone) {
        TravelerDAO travelerDAO = new TravelerDAO();
        return travelerDAO.getTraveler(phone);
    }

    public List<Traveler> getTravelerByUsername(String username) {
        TravelerDAO travelerDAO = new TravelerDAO();
        return travelerDAO.getTravelerList(username);
    }

    public List<Traveler> getTravelerList(String username) {
        TravelerDAO travelerDAO = new TravelerDAO();
        return travelerDAO.getTravelerList(username);
    }

    public void updateTravelerInfo(Traveler t) {
        TravelerDAO travelerDAO = new TravelerDAO();
        travelerDAO.updateTravelerInfo(t);
    }
}
