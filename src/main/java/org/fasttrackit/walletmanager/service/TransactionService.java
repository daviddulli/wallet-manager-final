package org.fasttrackit.walletmanager.service;

import org.fasttrackit.walletmanager.domain.Transaction;
import org.fasttrackit.walletmanager.persistance.TransactionRepository;
import org.fasttrackit.walletmanager.transfer.CreateTransactionRequest;
import org.fasttrackit.walletmanager.transfer.UpdateTransactionRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepository();

    public void createTransaction(CreateTransactionRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating transaction: " + request);
        transactionRepository.createTransaction(request);
    }

    public void  updateTransaction(long id, UpdateTransactionRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating transaction " + id + ": " + request);
        transactionRepository.updateTransaction(id, request);
    }

    public void deleteTransaction(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting transaction " + id);
        transactionRepository.deleteTransaction(id);
    }

    public List<Transaction> getTransactions() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving all transactions...");
        return transactionRepository.getTransaction();
    }




}
