package org.fasttrackit.walletmanager;

import org.fasttrackit.walletmanager.persistance.TransactionRepository;
import org.fasttrackit.walletmanager.transfer.CreateTransactionRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootApplication
public class WalletManagerApplication {

	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

		//SpringApplication.run(WalletManagerApplication.class, args);


		TransactionRepository transactionRepository = new TransactionRepository();
		CreateTransactionRequest request = new CreateTransactionRequest();
		request.setDescription("Incasare");
		request.setDate(Date.valueOf(LocalDate.now()));
		request.setType("Income");
		request.setValue(1000);
		transactionRepository.createTransaction(request);
	}

}
