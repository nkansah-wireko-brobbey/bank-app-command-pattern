package bank.commands;

import bank.dao.IAccountDAO;
import bank.domain.Account;

public class TransferFundsCommand implements ICommand {

    private double amount;
    private long toAccount;
    private long fromAccount;
    private IAccountDAO accountDAO;
    private String description;
     

    public TransferFundsCommand(double amount, long toAccount, long fromAccount, IAccountDAO accountDAO) {
        this.amount = amount;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.accountDAO = accountDAO;
    }

    @Override
    public void execute() {
        Account fromAccount = accountDAO.loadAccount(this.fromAccount);
		Account toAccount = accountDAO.loadAccount(this.toAccount);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
    }

    @Override
    public void unExecute() {
        Account fromAccount = accountDAO.loadAccount(this.fromAccount);
		Account toAccount = accountDAO.loadAccount(this.toAccount);
		toAccount.transferFunds(fromAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
    }
    
}
