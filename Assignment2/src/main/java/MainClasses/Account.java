
package MainClasses;

/**
 *
 * @author jolta
 */
public abstract class Account extends Customer {

    protected String accountNumber;
    protected String bsbNumber;
    protected Double balance;
    protected Double withdrawLimit;
    protected Double dailyWithdrawTotal;
    protected Double interestRate;
    protected Double interestGained;

    public Account() {
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double withdrawLimit, Double dailyWithdrawTotal, Double interestRate, Double interestGained, String name, String adress, String pin, String password, String dob) {
        super(name, adress, pin, password, dob);
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
        this.interestRate = interestRate;
        this.interestGained = interestGained;
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, Double interestRate, Double interestGained, String name, String adress, String pin, String password, String dob) {
        super(name, adress, pin, password, dob);
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
        this.interestRate = interestRate;
        this.interestGained = interestGained;
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, String name, String adress, String pin, String password, String dob) {
        super(name, adress, pin, password, dob);
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double withdrawLimit, Double dailyWithdrawTotal, Double interestRate, Double interestGained) {
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
        this.interestRate = interestRate;
        this.interestGained = interestGained;
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, Double interestRate, Double interestGained) {
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
        this.interestRate = interestRate;
        this.interestGained = interestGained;
    }

    public Account(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal) {
        this.accountNumber = accountNumber;
        this.bsbNumber = bsbNumber;
        this.balance = balance;
        this.dailyWithdrawTotal = dailyWithdrawTotal;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(Double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public Double getDailyWithdrawTotal() {
        return dailyWithdrawTotal;
    }

    public void setDailyWithdrawTotal(Double dailyWithdrawTotal) {
        this.dailyWithdrawTotal = dailyWithdrawTotal;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getInterestGained() {
        return interestGained;
    }

    public void setInterestGained(Double interestGained) {
        this.interestGained = interestGained;
    }

    public String getbsbNumber() {
        return bsbNumber;
    }

    public void setbsbNumber(String bsbNumber) {
        this.bsbNumber = bsbNumber;
    }
}
