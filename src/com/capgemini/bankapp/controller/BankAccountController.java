package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.capgemini.bankapp.exception.AccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

import sun.rmi.server.Dispatcher;

@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 1)
public class BankAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final org.apache.log4j.Logger logger = Logger.getLogger(BankAccountController.class);

	private BankAccountService bankService;
	// Logger logger = logger.getLogger(BankAccountController.class);

	public BankAccountController() {
		bankService = new BankAccountServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String path = request.getServletPath();

		if (path.equals("/displayallbankaccounts.do")) {

			List<BankAccount> bankAccounts = bankService.displayAllAccounts();
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayallbankaccounts.jsp");
			request.setAttribute("accounts", bankAccounts);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path = request.getServletPath();
		System.out.println(path);

		if (path.equals("/addNewBankAccount.do")) {
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			double balance = Double.parseDouble(request.getParameter("account_balance"));

			BankAccount account = new BankAccount(accountHolderName, accountType, balance);
			if (bankService.addNewBankAccount(account)) {

				out.println("<h2> Bank Account created successfully..</h2>");
				out.println("<h2><a href='index.html'>|home|</h2>");
				out.close();
			}
		}

		if (path.equals("/withdraw.do")) {
			long accountId = Long.parseLong(request.getParameter("name"));
			double amount = Double.parseDouble(request.getParameter("amount"));

			try {
				double balance = bankService.withdraw(accountId, amount);
				out.println("withdraw successfull... current balance is " + balance);
				out.println("<h2><a href='index.html'>|home|</h2>");
				out.close();

			} catch (LowBalanceException e) {
				out.print(e.getMessage());
				logger.error("exception ", e);

			} catch (AccountNotFoundException e) {
				out.print(e.getMessage());
			}
		}

		if (path.equals("/deposit.do")) {
			long accountId = Long.parseLong(request.getParameter("account_number"));
			double amount = Double.parseDouble(request.getParameter("amount"));

			try {
				double balance = bankService.deposit(accountId, amount);
				out.println("successfully Deposited... current balance is " + balance);
				out.println("<h2><a href='index.html'>|home|</h2>");
				out.close();

			} catch (AccountNotFoundException e) {
				out.print(e.getMessage());
			}
		}

		if (path.equals("/delete.do")) {
			long accountId = Long.parseLong(request.getParameter("account_number"));

			try {
				if (bankService.deleteBankAccount(accountId)) {
					out.println("successfully deleted...");
					out.println("<h2><a.href='index.html'>|home|</h2>");
					out.close();
				}

			} catch (AccountNotFoundException e) {
				out.print(e.getMessage());
			}
		}

		if (path.equals("/fundTransfer.do")) {
			long fromAccountId = Long.parseLong(request.getParameter("from_account"));
			long toAccountId = Long.parseLong(request.getParameter("to_account"));
			double amount = Double.parseDouble(request.getParameter("amount"));

			try {
				double balance = bankService.fundTransfer(fromAccountId, toAccountId, amount);
				out.println("withdraw successfull... current balance is " + balance);
				out.println("<h2><a href='index.html'>|home|</h2>");
				out.close();

			} catch (LowBalanceException e) {
				out.print(e.getMessage());
			} catch (AccountNotFoundException e) {
				out.print(e.getMessage());
			}
		}
		if (path.equals("/checkBalance.do")) {
			long accountId = Long.parseLong(request.getParameter("account_number"));

			try {
				double balance = bankService.checkBalance(accountId);
				out.println("account balance is..." + balance);
				out.println("<h2><a href='index.html'>|home|</h2>");
				out.close();

			} catch (AccountNotFoundException e) {
				out.print(e.getMessage());
			}
		}

		if (path.equals("/searchaccount.do")) {
			long accountId = Long.parseLong(request.getParameter("accountNumber"));
			try {
				BankAccount account = bankService.findAccountById(accountId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
				request.setAttribute("account", account);
				dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				out.print("Account doesn't exist");
				out.println("<h2><a href='index.html'>|home|</h2>");
			} catch (AccountNotFoundException e) {
				out.print("account doesn't exist in database");
			}
		}
		if (path.equals("/updateAccount.do")) {
			long accountId = Long.parseLong(request.getParameter("accountnumber"));

			try {
				BankAccount account = bankService.findAccountById(accountId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("updatedetails.jsp");
				request.setAttribute("account", account);
				dispatcher.forward(request, response);
				out.close();

			} catch (AccountNotFoundException | SQLException e) {
				out.print(e.getMessage());
			}
		}
		if (path.equals("/update.do")) {
			long accountId = Long.parseLong(request.getParameter("accountid"));
			String accountHoldername = request.getParameter("accountholdername");
			String accountType = request.getParameter("accounttype");

			boolean result = bankService.updateAccountDetails(accountId, accountHoldername, accountType);
			if(result) {
				out.print("account updated successfully");
				out.println("<h2><a href='index.html'>|home|</h2>");
			}
			else {
				out.print("failed to update....");
				out.println("<h2><a href='index.html'>|home|</h2>");
			}
			out.close();
		}
		
		
		
	}

}
