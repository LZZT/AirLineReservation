package service;


import dao.TransactionDAO;
import dao.TravelerDAO;
import model.Transactions;
import model.Traveler;

import java.util.List;


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
}
