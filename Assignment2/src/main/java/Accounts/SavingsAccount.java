/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accounts;

import MainClasses.Account;

/**
 *
 * @author jolta
 */
public class SavingsAccount extends Account {

    public SavingsAccount() {
    }

    public SavingsAccount(String accountNumber, String bsbNumber, Double balance, Double withdrawLimit, Double dailyWithdrawTotal, Double interestRate, Double interestGained, String name, String adress, String pin, String password, String dob) {
        super(accountNumber, bsbNumber, balance, withdrawLimit, dailyWithdrawTotal, interestRate, interestGained, name, adress, pin, password, dob);
    }

    public SavingsAccount(String accountNumber, String bsbNumber, Double balance, Double withdrawLimit, Double dailyWithdrawTotal, Double interestRate, Double interestGained) {
        super(accountNumber, bsbNumber, balance, withdrawLimit, dailyWithdrawTotal, interestRate, interestGained);
    }
}
