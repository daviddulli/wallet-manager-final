package org.fasttrackit.walletmanager.web;

import org.fasttrackit.walletmanager.config.ObjectMapperConfiguration;
import org.fasttrackit.walletmanager.domain.Transaction;
import org.fasttrackit.walletmanager.service.TransactionService;
import org.fasttrackit.walletmanager.transfer.CreateTransactionRequest;
import org.fasttrackit.walletmanager.transfer.UpdateTransactionRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {

    private TransactionService transactionService = new TransactionService();
    //endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateTransactionRequest request = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), CreateTransactionRequest.class);
        try {
            transactionService.createTransaction(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");

        try {
            transactionService.deleteTransaction(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");
        UpdateTransactionRequest request = ObjectMapperConfiguration.objectMapper.readValue(req.getReader(), UpdateTransactionRequest.class);
        try {
            transactionService.updateTransaction(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<Transaction> transactions = transactionService.getTransactions();

            String response = ObjectMapperConfiguration.objectMapper
                    .writeValueAsString(transactions);

            resp.getWriter().print(response);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }
    //for pre-flight request
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
    }

    //CORS configuration (Cross Origin Resource Sharing)
    private void setAccessControlHeaders(HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

    }

}
