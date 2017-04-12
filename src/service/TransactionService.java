package service;


import dao.FlightDAO;
import dao.TransactionDAO;
import dao.TravelerDAO;
import model.Flight;
import model.Ticket;
import model.Transactions;
import model.Traveler;

import javax.transaction.Transaction;
import java.util.*;


public class TransactionService {
    public void addNewTransaction(Transactions transactions){
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.saveTansaction(transactions);
    }
    public void deleteTransaction(String transactionID){
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.deleteTransaction(transactionID);
    }

    public List<Transactions> getTransactionByCustomer(String username){
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getTransactionByCustomer(username);
    }


    public  Map<Transactions,List<Ticket>> getTransactionAndTicket(String username){
        TicketService ticketService = new TicketService();
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transactions> transactionsList = transactionDAO.getTransactionByCustomer(username);
        Map<Transactions,List<Ticket>> transactionsListMap = new HashMap<>();
        for (Transactions transactions:transactionsList){
            transactionsListMap.put(transactions,ticketService.getTicketByTransaction(transactions.getTransactionID()));
        }
        return transactionsListMap;
    }
    public void addCancelTransaction(String ticketID){
        TicketService ticketService = new TicketService();
        Ticket ticket =ticketService.getTicket(ticketID);
        System.out.println(ticket.getPrice());
        Transactions transaction = getTransactions(ticket.getTransactionID());
        transaction.setPrice(0-ticket.getPrice());
        TransactionService transactionService = new TransactionService();
        transactionService.addNewTransaction(transaction);
    }




    public  Transactions getTransactions(String transactionID){
        TransactionDAO transactionDAO = new TransactionDAO();
        return  transactionDAO.getTransactions(transactionID);
    }
    public  void UpdateTransactionPrice(String ticketID){
        TicketService ticketService = new TicketService();
        Ticket ticket =ticketService.getTicket(ticketID);
        FlightDAO flightDAO = new FlightDAO();
        Flight flight= flightDAO.getSingleFlightByFlightNumber(ticket.getFlightNumber());
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.UpdateTransactionPrice(ticket.getTransactionID(),flight.getPrice());
    }
}
