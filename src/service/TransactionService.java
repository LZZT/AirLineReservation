package service;


import dao.TransactionDAO;
import dao.TravelerDAO;
import model.Ticket;
import model.Transactions;
import model.Traveler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public  List<List<Ticket>> getTransactionAndTicket(String username){
         TicketService ticketService = new TicketService();
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transactions> transactionsList = transactionDAO.getTransactionByCustomer(username);
        List<List<Ticket>> transactionsListMap = new ArrayList<>();
        for (Transactions transactions:transactionsList){
            transactionsListMap.add(ticketService.getTicketByTransaction(transactions.getTransactionID()));
        }
        return transactionsListMap;
    }

    public  Map<Transactions,List<Ticket>> getTransactionAndTicket2(String username){
        TicketService ticketService = new TicketService();
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transactions> transactionsList = transactionDAO.getTransactionByCustomer(username);
        Map<Transactions,List<Ticket>> transactionsListMap = new HashMap<>();
        for (Transactions transactions:transactionsList){
            transactionsListMap.put(transactions,ticketService.getTicketByTransaction(transactions.getTransactionID()));
        }
        return transactionsListMap;
    }
}
