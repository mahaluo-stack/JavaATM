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
public class ChequeAccount extends Account {

    public ChequeAccount() {
    }

    public ChequeAccount(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal, String name, String adress, String pin, String password, String dob) {
        super(accountNumber, bsbNumber, balance, dailyWithdrawTotal, name, adress, pin, password, dob);
    }

    public ChequeAccount(String accountNumber, String bsbNumber, Double balance, Double dailyWithdrawTotal) {
        super(accountNumber, bsbNumber, balance, dailyWithdrawTotal);
    }
}
