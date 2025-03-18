package bank.service;

import java.util.Collection;

import bank.commands.CommandManager;
import bank.commands.ICommand;
import bank.commands.DepositCommand;
import bank.commands.ICommandManager;
import bank.commands.TransferFundsCommand;
import bank.commands.WithdrawCommand;
import bank.dao.AccountDAO;
import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.Customer;



public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private ICommandManager commandManager;

	
	public AccountService(){
		accountDAO=new AccountDAO();
		commandManager = new CommandManager();
	}

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		return account;
	}

	public void deposit(long accountNumber, double amount) {

		ICommand depositCommand = new DepositCommand(accountDAO, amount, accountNumber);
		this.commandManager.executeCommand(depositCommand);
	}

	public Account getAccount(long accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
	
		ICommand withdrawCommand = new WithdrawCommand(accountDAO, amount, accountNumber);
		this.commandManager.executeCommand(withdrawCommand);
	}



	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		ICommand transferFundsCommand = new TransferFundsCommand(amount, toAccountNumber, fromAccountNumber, accountDAO);
		this.commandManager.executeCommand(transferFundsCommand);
	}
}
