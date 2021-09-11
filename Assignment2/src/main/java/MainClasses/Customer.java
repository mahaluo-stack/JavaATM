/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolta
 */
public class Customer {
    
    protected String name;
    protected String adress;
    protected String pin;
    protected String password;
    protected String dob;
    protected List accounts = new ArrayList<>();

    public Customer(String name, String adress, String pin, String password, String dob) {
        this.name = name;
        this.adress = adress;
        this.pin = pin;
        this.password = password;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return " Name: " + this.name + "\n" + " Adress: " + this.adress + "\n" + " PIN: " + this.pin + "\n" + " Password: " + this.password + "\n" + " D.O.B: " + this.dob + "\n";
    }

    
    
    
    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List getAccounts() {
        return accounts;
    }

    public void setAccounts(List accounts) {
        this.accounts = accounts;
    }
}
