package bank.commands;

import bank.dao.IAccountDAO;
import bank.domain.Account;

class WithdrawCommand implements ICommand {

    private IAccountDAO accountDAO;
    private double amount;
    private long accountNumber;

    public WithdrawCommand(IAccountDAO accountDAO, double amount, long accountNumber) {
        this.accountDAO = accountDAO;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    @Override
    public void execute() {

        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);

    }

    @Override
    public void unExecute() {
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);
    }

   

}