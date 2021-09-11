/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import Interfaces.InitUI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author jolta
 */
public class WebUI implements InitUI {

    Button signInBtn, signOutBtn, exitBtn, confirmNewAccountBtn, newAccountBtn, registerBtn, returnBtn, chequeAccountBtn, fixedAccountBtn, netSavingsAccountBtn, savingsAccountBtn, cancelNewAccountBtn, closeAccountBtn;
    MenuButton accountMenuBtn, optionMenuBtn;
    MenuItem chequeMenu, fixedMenu, netSavingsMenu, savingsMenu, openAccount, closeAccount, updateDetails;

    ToggleButton toggleTransfer, toggleHistory, toggleWithdrawLimit;
    ToggleGroup accountOptionsToggle;

    TableView accountTable;

    TextField signInField, changeField, receiverField, amountField, firstNameField, lastNameField, dobField, adressField;
    PasswordField passwordField;
    LimitPinField pinField;

    Label topLabel, welcomeLabel, footerLabel;

    public WebUI() {
    }

    @Override
    public void setButtons() {

        signInBtn = new Button("Sign In");
        signOutBtn = new Button("Sign Out");
        exitBtn = new Button("Exit");

        newAccountBtn = new Button("New Account");
        registerBtn = new Button("Register");
        registerBtn.setId("signInBtn");

        returnBtn = new Button("Return");
        returnBtn.setId("signInBtn");

        chequeMenu = new MenuItem("Cheque");
        fixedMenu = new MenuItem("Fixed");
        netSavingsMenu = new MenuItem("Net Savings");
        savingsMenu = new MenuItem("Savings");
        accountMenuBtn = new MenuButton("Accounts", null, chequeMenu, fixedMenu, netSavingsMenu, savingsMenu);
        accountMenuBtn.setId("toggleBtn");

        openAccount = new MenuItem("Open Account");
        closeAccount = new MenuItem("Close Account");
        updateDetails = new MenuItem("Update Details");
        optionMenuBtn = new MenuButton("Options", null, openAccount, closeAccount, updateDetails);
        optionMenuBtn.setId("toggleBtn");

        toggleTransfer = new ToggleButton("Transfer Money");
        toggleTransfer.setId("toggleBtn");
        toggleHistory = new ToggleButton("View Transaction History");
        toggleHistory.setId("toggleBtn");
        toggleWithdrawLimit = new ToggleButton("Change Withdraw Limit");
        toggleWithdrawLimit.setId("toggleBtn");

        accountOptionsToggle = new ToggleGroup();
        toggleTransfer.setToggleGroup(accountOptionsToggle);
        toggleHistory.setToggleGroup(accountOptionsToggle);
        toggleWithdrawLimit.setToggleGroup(accountOptionsToggle);

        chequeAccountBtn = new Button("Cheque Account");
        chequeAccountBtn.setId("accountBtn");

        fixedAccountBtn = new Button("Fixed Account");
        fixedAccountBtn.setId("accountBtn");

        netSavingsAccountBtn = new Button("Net Savings Account");
        netSavingsAccountBtn.setId("accountBtn");

        savingsAccountBtn = new Button("Savings Account");
        savingsAccountBtn.setId("accountBtn");

        cancelNewAccountBtn = new Button("Cancel");
        cancelNewAccountBtn.setId("accountBtn");

        confirmNewAccountBtn = new Button("Confirm");
    }

    @Override
    public void setLabels() {

        topLabel = new Label("Your Money Bin Account");
        welcomeLabel = new Label("Welcome to Money Bin! Please sign in to continue.");
        welcomeLabel.setId("welcomeLabel");
        footerLabel = new Label("(c) Moneybin Co. LCC, all rights reserved");
    }

    @Override
    public void setFields() {

        signInField = new TextField();
        signInField.setPromptText("dd-MM-yyyy");
        signInField.setId("signInPageField");

        changeField = new TextField();

        receiverField = new TextField();
        receiverField.setPromptText("Recipient Account Number");
        receiverField.setId("signInPageField");

        amountField = new TextField();
        amountField.setPromptText("$$$");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setId("signInPageField");

        accountTable = new TableView();

        TableColumn<String, Account> column1 = new TableColumn<>("Account Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

        TableColumn<String, Account> column2 = new TableColumn<>("BSB Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("bsbNumber"));

        TableColumn<Double, Account> column3 = new TableColumn<>("Balance");
        column3.setCellValueFactory(new PropertyValueFactory<>("balance"));

        TableColumn<Double, Account> column4 = new TableColumn<>("Withdraw Limit");
        column4.setCellValueFactory(new PropertyValueFactory<>("withdrawLimit"));

        TableColumn<String, Account> column5 = new TableColumn<>("Withdrawn Today");
        column5.setCellValueFactory(new PropertyValueFactory<>("dailyWithdrawTotal"));

        TableColumn<Double, Account> column6 = new TableColumn<>("Interest Rate");
        column6.setCellValueFactory(new PropertyValueFactory<>("interestRate"));

        TableColumn<Double, Account> column7 = new TableColumn<>("Interest Gained");
        column7.setCellValueFactory(new PropertyValueFactory<>("interestGained"));

        accountTable.getColumns().add(column1);
        accountTable.getColumns().add(column2);
        accountTable.getColumns().add(column3);
        accountTable.getColumns().add(column4);
        accountTable.getColumns().add(column5);
        accountTable.getColumns().add(column6);
        accountTable.getColumns().add(column7);

        firstNameField = new TextField();
        firstNameField.setPromptText("First name");
        firstNameField.setId("signInPageField");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last name");
        lastNameField.setId("signInPageField");

        pinField = new LimitPinField();
        pinField.setPromptText("PIN - 4 Digits");
        pinField.setId("signInPageField");

        adressField = new TextField();
        adressField.setPromptText("Adress");
        adressField.setId("signInPageField");

        dobField = new TextField();
        dobField.setPromptText("dd-MM-yyyy");
        dobField.setId("signInPageField");
    }

    public void resetSignIn() {

        signInField.setText("");
        signInField.setPromptText("dd-MM-yyyy");

        passwordField.setText("");
        passwordField.setPromptText("Password");
    }

    public void resetRegister() {

        firstNameField.setText("");
        firstNameField.setPromptText("First name");

        lastNameField.setText("");
        lastNameField.setPromptText("Last name");

        pinField.setText("");
        pinField.setPromptText("PIN - 4 Digits");

        adressField.setText("");
        adressField.setPromptText("Adress");

        dobField.setText("");
        dobField.setPromptText("dd-MM-yyyy");

        passwordField.setText("");
        passwordField.setPromptText("Password");
    }

    public void updateWelcomeLabel(String message) {

        welcomeLabel.setText(message);
    }

    public Button getSignInBtn() {
        return signInBtn;
    }

    public Button getSignOutBtn() {
        return signOutBtn;
    }

    public Button getExitBtn() {
        return exitBtn;
    }

    public Button getConfirmNewAccountBtn() {
        return confirmNewAccountBtn;
    }

    public MenuButton getAccountMenuBtn() {
        return accountMenuBtn;
    }

    public MenuButton getOptionMenuBtn() {
        return optionMenuBtn;
    }

    public MenuItem getChequeMenu() {
        return chequeMenu;
    }

    public MenuItem getFixedMenu() {
        return fixedMenu;
    }

    public MenuItem getNetSavingsMenu() {
        return netSavingsMenu;
    }

    public MenuItem getSavingsMenu() {
        return savingsMenu;
    }

    public ToggleButton getToggleTranfer() {
        return toggleTransfer;
    }

    public ToggleButton getToggleHistory() {
        return toggleHistory;
    }

    public ToggleButton getToggleWithdrawLimit() {
        return toggleWithdrawLimit;
    }

    public ToggleGroup getTransferGroup() {
        return accountOptionsToggle;
    }

    public TableView getAccountTable() {
        return accountTable;
    }

    public TextField getSignInField() {
        return signInField;
    }

    public TextField getChangeField() {
        return changeField;
    }

    public TextField getReceiverField() {
        return receiverField;
    }

    public TextField getAmountField() {
        return amountField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getTopLabel() {
        return topLabel;
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }

    public Label getFooterLabel() {
        return footerLabel;
    }

    public MenuItem getOpenAccount() {
        return openAccount;
    }

    public MenuItem getCloseAccount() {
        return closeAccount;
    }

    public MenuItem getUpdateDetails() {
        return updateDetails;
    }

    public Button getNewAccountBtn() {
        return newAccountBtn;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getDobField() {
        return dobField;
    }

    public LimitPinField getPinField() {
        return pinField;
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public Button getReturnBtn() {
        return returnBtn;
    }

    public TextField getAdressField() {
        return adressField;
    }

    public Button getChequeAccountBtn() {
        return chequeAccountBtn;
    }

    public Button getFixedAccountBtn() {
        return fixedAccountBtn;
    }

    public Button getNetSavingsAccountBtn() {
        return netSavingsAccountBtn;
    }

    public Button getSavingsAccountBtn() {
        return savingsAccountBtn;
    }

    public Button getCancelNewAccountBtn() {
        return cancelNewAccountBtn;
    }

    public Button setCloseAccountBtn(String accountNumber) {

        closeAccountBtn = new Button(accountNumber);
        closeAccountBtn.setId("accountBtn");

        return closeAccountBtn;
    }

    public String getCloseAccountBtn() {

       return closeAccountBtn.getText();
    }
}
