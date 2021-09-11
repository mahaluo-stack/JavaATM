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
public class FixedAccount extends Account {

    public FixedAccount() {
    }

    public FixedAccount(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, Double interestRate, Double interestGained, String name, String adress, String pin, String password, String dob) {
        super(accountNumber, bsbNumber, balance, dailyWithdrawTotal, interestRate, interestGained, name, adress, pin, password, dob);
    }

    public FixedAccount(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, Double interestRate, Double interestGained) {
        super(accountNumber, bsbNumber, balance, dailyWithdrawTotal, interestRate, interestGained);
    }
}
