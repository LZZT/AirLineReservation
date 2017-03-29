package service;


import dao.TravelerDAO;
import model.Traveler;


public class TravelerService {
    public void registerNewTraveler(Traveler traveler){
        TravelerDAO travelerDAO = new TravelerDAO();
        travelerDAO.saveTraveler(traveler);
    }
    public void deleteTraveler(String phone){
        TravelerDAO travelerDAO = new TravelerDAO();
        travelerDAO.deleteTraveler(phone);
    }
}
