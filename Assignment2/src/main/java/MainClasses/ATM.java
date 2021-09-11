/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import Accounts.ChequeAccount;
import Accounts.FixedAccount;
import Accounts.NetSavingsAccount;
import Accounts.SavingsAccount;
import Exceptions.EmptyFieldException;
import Interfaces.ATMOperations;
import Interfaces.MainOperations;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jolta
 */
public class ATM extends Application implements MainOperations, ATMOperations {

    BorderPane root;
    ATMUI getUI;

    HBox topBox, footerBox;
    VBox leftBox, rightBox, centerBox, keyPadBox;
    GridPane keyPadGrid, centerGrid;

    Customer newCustomer;
    Account newChequeAccount, newFixedAccount, newNetSavingsAccount, newSavingsAccount;

    String currentAccount, currentAmount;
    String currentTransaction = "";

    public static void main(String... args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initUI(stage);
    }

    public void initUI(Stage stage) {

        getUI = new ATMUI();
        root = new BorderPane();
        Scene scene = new Scene(root, 900, 900);
        root.setId("root");
        keyPadGrid = new GridPane();
        centerGrid = new GridPane();
        newCustomer = new Customer("Max Haggqvist", "Seaview Street", "0000", "max", "000");
        newChequeAccount = new ChequeAccount("054 6374 2134", "062", 4500.0, 0.0);
        newFixedAccount = new FixedAccount("054 2454 4373", "040", 9000.0, 0.0, 0.04, 0.0);
        newNetSavingsAccount = new NetSavingsAccount("144 1844 2904", "344", 2500.0, 2500.0, 0.0, 0.023, 0.0);
        newSavingsAccount = new SavingsAccount("054 3267 8749", "060", 100.0, 2500.0, 0.0, 0.015, 0.0);

        stage.setScene(scene);
        scene.getStylesheets().add("/styles/ATMUIstyles.css");

       
        getUI.setButtons();
        getUI.setLabels();
        getUI.setFields();

//<editor-fold defaultstate="collapsed" desc="CenterGrid with Amounts">
        GridPane.setConstraints(getUI.getPinLabel(), 0, 0);
        GridPane.setConstraints(getUI.getPinField(), 0, 1);

        GridPane.setConstraints(getUI.getAmount20Btn(), 0, 0);
        GridPane.setConstraints(getUI.getAmount40Btn(), 0, 1);
        GridPane.setConstraints(getUI.getAmount60Btn(), 0, 2);
        GridPane.setConstraints(getUI.getAmount80Btn(), 0, 3);

        GridPane.setConstraints(getUI.getAmount100Btn(), 1, 0);
        GridPane.setConstraints(getUI.getAmount200Btn(), 1, 1);
        GridPane.setConstraints(getUI.getAmount300Btn(), 1, 2);
        GridPane.setConstraints(getUI.getOtherBtn(), 1, 3);

        GridPane.setConstraints(getUI.getCancelBtn(), 0, 4);

        centerGrid.setVgap(10);
        centerGrid.setHgap(200);
        centerGrid.getChildren().addAll(getUI.getAmount20Btn(), getUI.getAmount40Btn(), getUI.getAmount60Btn(), getUI.getAmount80Btn(), getUI.getAmount100Btn(), getUI.getAmount200Btn(), getUI.getAmount300Btn(), getUI.getOtherBtn());
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Keypad Constraints">
        GridPane.setConstraints(getUI.getDigit1Btn(), 0, 0);
        GridPane.setConstraints(getUI.getDigit2Btn(), 1, 0);
        GridPane.setConstraints(getUI.getDigit3Btn(), 2, 0);

        GridPane.setConstraints(getUI.getDigit4Btn(), 0, 1);
        GridPane.setConstraints(getUI.getDigit5Btn(), 1, 1);
        GridPane.setConstraints(getUI.getDigit6Btn(), 2, 1);

        GridPane.setConstraints(getUI.getDigit7Btn(), 0, 2);
        GridPane.setConstraints(getUI.getDigit8Btn(), 1, 2);
        GridPane.setConstraints(getUI.getDigit9Btn(), 2, 2);

        GridPane.setConstraints(getUI.getDigitBlank1Btn(), 0, 3);
        GridPane.setConstraints(getUI.getDigit0Btn(), 1, 3);
        GridPane.setConstraints(getUI.getDigitBlank2Btn(), 2, 3);
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Fill Keypad Grid">
        keyPadGrid.getChildren().addAll(getUI.getDigit1Btn(), getUI.getDigit2Btn(), getUI.getDigit3Btn(), getUI.getDigit4Btn(), getUI.getDigit5Btn(), getUI.getDigit6Btn(), getUI.getDigit7Btn(), getUI.getDigit8Btn(), getUI.getDigit9Btn(), getUI.getDigit0Btn(), getUI.getDigitBlank1Btn(), getUI.getDigitBlank2Btn());
        keyPadGrid.setVgap(5);
        keyPadGrid.setHgap(5);
        keyPadGrid.setId("keyPadGrid");
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Fill Keypad Box">
        keyPadBox = new VBox();
        keyPadBox.getChildren().addAll(getUI.getCancelBtn(), getUI.getClearBtn(), getUI.getEnterBtn(), getUI.getBlankBtn());
        keyPadBox.setSpacing(5);
        keyPadBox.setId("keyPadBox");
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Put Keypad In Footer">
        footerBox = new HBox();
        footerBox.getChildren().addAll(keyPadGrid, keyPadBox);
        footerBox.setId("footerBox");
        footerBox.setAlignment(Pos.CENTER);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Set Columns & Top">
        leftBox = new VBox();
        leftBox.setId("column");
        leftBox.setSpacing(15);
        leftBox.setAlignment(Pos.BOTTOM_CENTER);
        rightBox = new VBox();
        rightBox.setId("column");
        rightBox.setSpacing(10);
        rightBox.setAlignment(Pos.BOTTOM_CENTER);
        topBox = new HBox();
        topBox.setId("topBox");
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Pinbox">
        centerBox = new VBox();
        centerBox.setId("pinBox");
        centerBox.setSpacing(5);
        centerBox.getChildren().addAll(getUI.getPinLabel(), getUI.getPinField());
        centerBox.setAlignment(Pos.CENTER);
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Fill Root">
        root.setBottom(footerBox);
        root.setLeft(leftBox);
        root.setRight(rightBox);
        root.setCenter(centerBox);
        root.setTop(topBox);
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Keypad Buttons Action">
        getUI.getDigit1Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(1);
            getUI.updatePinField(1);
        });
        getUI.getDigit2Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(2);
            getUI.updatePinField(2);
        });
        getUI.getDigit3Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(3);
            getUI.updatePinField(3);
        });
        getUI.getDigit4Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(4);
            getUI.updatePinField(4);
        });
        getUI.getDigit5Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(5);
            getUI.updatePinField(5);
        });
        getUI.getDigit6Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(6);
            getUI.updatePinField(6);
        });
        getUI.getDigit7Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(7);
            getUI.updatePinField(7);
        });
        getUI.getDigit8Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(8);
            getUI.updatePinField(8);
        });
        getUI.getDigit9Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(9);
            getUI.updatePinField(9);
        });
        getUI.getDigit0Btn().setOnAction((t) -> {

            getUI.updateAmountFieldATM(0);
            getUI.updatePinField(0);
        });
        getUI.getCancelBtn().setOnAction((t) -> {

            if (currentTransaction.equals("")) {

                try {

                    getUI.cancelPinField();
                } catch (EmptyFieldException ex) {

                    System.err.println("Error: " + ex);
                }
            } else {

                try {

                    getUI.cancelAmountFieldATM();
                } catch (EmptyFieldException ex) {

                    System.err.println("Error: " + ex);
                }
            }
        });
        getUI.getClearBtn().setOnAction((t) -> {

            if (currentTransaction.equals("")) {

                try {

                    getUI.clearPinField();
                } catch (EmptyFieldException ex) {

                    System.err.println("Error: " + ex);
                }
            } else {

                try {

                    getUI.clearAmountFieldATM();
                } catch (EmptyFieldException ex) {

                    System.err.println("Error: " + ex);
                }
            }
        });
        getUI.getEnterBtn().setOnAction((t) -> {

            signIn();
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Withdraw/Deposit/View Balance Buttons Action">
        getUI.getWithdrawBtn().setOnAction((t) -> {

            this.currentTransaction = "Withdraw";
            showAccounts();
            clearLeftBox();
            leftCancelButtons();
        });

        getUI.getDepositBtn().setOnAction((t) -> {

            this.currentTransaction = "Deposit";
            showAccounts();
            clearLeftBox();
            leftCancelButtons();
        });

        getUI.getBalanceBtn().setOnAction((t) -> {

            this.currentTransaction = "Balance";
            getUI.getCancelTransactionBtn().setText("Return");
            showAccounts();
            clearLeftBox();
            leftCancelButtons();
        });
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Account Buttons Action">
        getUI.getSavingsBtn().setOnAction((t) -> {

            currentAccount = "Savings";

            if (currentTransaction.equals("Balance")) {

                clearCenterBox();
                getUI.updateTransactionLabel("Account: " + currentAccount + "\n" + "Balance: " + newSavingsAccount.getBalance());
                centerBox.getChildren().add(getUI.getTransactionLabel());
            } else {

                showAmountButtons();
            }
        });
        getUI.getNetSavingsBtn().setOnAction((t) -> {

            currentAccount = "Net savings";

            if (currentTransaction.equals("Balance")) {

                clearCenterBox();
                getUI.updateTransactionLabel("Account: " + currentAccount + "\n" + "Balance: " + newNetSavingsAccount.getBalance());
                centerBox.getChildren().add(getUI.getTransactionLabel());
            } else {

                showAmountButtons();
            }
        });
        getUI.getFixedBtn().setOnAction((t) -> {

            currentAccount = "Fixed";

            if (currentTransaction.equals("Balance")) {

                clearCenterBox();
                getUI.updateTransactionLabel("Account: " + currentAccount + "\n" + "Balance: " + newFixedAccount.getBalance());
                centerBox.getChildren().add(getUI.getTransactionLabel());
            } else {

                showAmountButtons();
            }
        });
        getUI.getChequeBtn().setOnAction((t) -> {

            currentAccount = "Cheque";

            if (currentTransaction.equals("Balance")) {

                clearCenterBox();
                getUI.updateTransactionLabel("Account: " + currentAccount + "\n" + "Balance: " + newChequeAccount.getBalance());
                centerBox.getChildren().add(getUI.getTransactionLabel());
            } else {

                showAmountButtons();
            }
        });
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Amount Button Action">
        getUI.getAmount20Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$20";
            getUI.getAmount20Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount40Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$40";
            getUI.getAmount40Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount60Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$60";
            getUI.getAmount60Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount80Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$80";
            getUI.getAmount80Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount100Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$100";
            getUI.getAmount100Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount200Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$200";
            getUI.getAmount200Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getAmount300Btn().setOnAction((t) -> {

            getUI.resetAmountBtnId();
            this.currentAmount = "$300";
            getUI.getAmount300Btn().setId("activeAmountBtn");
            amountBtnPressed();
        });
        getUI.getOtherBtn().setOnAction((t) -> {

            try {

                getUI.cancelAmountFieldATM();
            } catch (EmptyFieldException ex) {

            }
            getUI.resetAmountBtnId();
            this.currentAmount = "other";
            getUI.getOtherBtn().setId("activeAmountBtn");
            amountBtnPressed();
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Continue Transaction Button">
        getUI.getContinueTransactionBtn().setOnAction((ActionEvent t) -> {

            if (this.currentAmount.equals("other") && getUI.getLimitAmountField().getText().isEmpty()) {

                System.out.println("No amount given");
                getUI.getLimitAmountField().setPromptText("Error!");
            } else if (this.currentAmount.equals("other")) {

                this.currentAmount = "$" + getUI.getLimitAmountField().getText();
                getUI.updateTransactionLabel("Confirm transaction");
                getUI.updateConfirmTransactionLabel(currentTransaction, currentAmount, currentAccount);

                clearCenterBox();
                centerBox.getChildren().addAll(getUI.getTransactionLabel(), getUI.getConfirmTransactionLabel());

                clearRightBox();
                rightBox.getChildren().add(getUI.getConfirmTransactionBtn());
            } else {

                getUI.updateTransactionLabel("Confirm transaction");
                getUI.updateConfirmTransactionLabel(currentTransaction, currentAmount, currentAccount);

                clearCenterBox();
                centerBox.getChildren().addAll(getUI.getTransactionLabel(), getUI.getConfirmTransactionLabel());

                clearRightBox();
                rightBox.getChildren().add(getUI.getConfirmTransactionBtn());
            }
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Confirm Transaction Button">
        getUI.getConfirmTransactionBtn().setOnAction((t) -> {

            if (currentTransaction.equals("Deposit")) {

                if (checkNotes() == true) {

                    deposit();
                    clearLeftBox();
                    getUI.getCancelTransactionBtn().setText("Return");
                    leftCancelButtons();
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Invalid amount");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                }
            } else if (currentTransaction.equals("Withdraw")) {

                if (checkNotes() == true) {

                    withdraw();
                    clearLeftBox();
                    getUI.getCancelTransactionBtn().setText("Return");
                    leftCancelButtons();
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Invalid amount");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                }
            }

            clearRightBox();
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cancel Buttons">
        getUI.getCancelTransactionBtn().setOnAction((t) -> {

            getUI.getCancelTransactionBtn().setText("Cancel Transaction");
            getUI.updateTransactionLabel("What would you like to do?");
            getUI.resetAmountBtnId();

            clearCenterBox();
            clearLeftBox();
            clearRightBox();

            centerBox.getChildren().addAll(getUI.getTransactionLabel(), getUI.getWithdrawBtn(), getUI.getDepositBtn(), getUI.getBalanceBtn());
            leftBox.getChildren().add(getUI.getCancelReturnBtn());
        });

        getUI.getCancelReturnBtn().setOnAction((t) -> {

            try {

                resetATM();
            } catch (EmptyFieldException ex) {

                System.err.println("ResetATM tried to clear an empty field" + ex);
            }
        });
//</editor-fold>

        stage.show();
    }

    @Override
    public void showAccounts(String currentTransaction) {

    }

    @Override
    public void showDetails() {
    }

    @Override
    public void transfer() {
    }

    @Override
    public void withdraw() {

        switch (currentAccount) {

            case "Savings":

                if (newSavingsAccount.getBalance() >= Integer.valueOf(currentAmount)) {

                    newSavingsAccount.setBalance((newSavingsAccount.getBalance() - Integer.valueOf(currentAmount)));

                    clearCenterBox();
                    getUI.updateTransactionLabel("Withdrawn: $" + currentAmount + "\n From: " + currentAccount + " account");
                    centerBox.getChildren().add(getUI.getTransactionLabel());

                    System.out.println("Withdrawn money");
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Insufficient funds");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                    System.out.println("Insufficient funds");
                }

                break;

            case "Net savings":

                if (newNetSavingsAccount.getBalance() >= Integer.valueOf(currentAmount)) {

                    newNetSavingsAccount.setBalance((newNetSavingsAccount.getBalance() - Integer.valueOf(currentAmount)));

                    clearCenterBox();
                    getUI.updateTransactionLabel("Withdrawn: $" + currentAmount + "\n From: " + currentAccount + " account");
                    centerBox.getChildren().add(getUI.getTransactionLabel());

                    System.out.println("Withdrawn money");
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Insufficient funds");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                    System.out.println("Insufficient funds");
                }
                break;

            case "Fixed":

                if (newFixedAccount.getBalance() >= Integer.valueOf(currentAmount)) {

                    newFixedAccount.setBalance((newFixedAccount.getBalance() - Integer.valueOf(currentAmount)));

                    clearCenterBox();
                    getUI.updateTransactionLabel("Withdrawn: $" + currentAmount + "\n From: " + currentAccount + " account");
                    centerBox.getChildren().add(getUI.getTransactionLabel());

                    System.out.println("Withdrawn money");
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Insufficient funds");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                    System.out.println("Insufficient funds");
                }
                break;

            case "Cheque":

                if (newChequeAccount.getBalance() >= Integer.valueOf(currentAmount)) {

                    newChequeAccount.setBalance((newChequeAccount.getBalance() - Integer.valueOf(currentAmount)));

                    clearCenterBox();
                    getUI.updateTransactionLabel("Withdrawn: $" + currentAmount + "\n From: " + currentAccount + " account");
                    centerBox.getChildren().add(getUI.getTransactionLabel());

                    System.out.println("Withdrawn money");
                } else {

                    clearCenterBox();
                    getUI.updateTransactionLabel("Insufficient funds");
                    centerBox.getChildren().add(getUI.getTransactionLabel());
                    System.out.println("Insufficient funds");
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void deposit() {

        switch (currentAccount) {

            case "Savings":

                newSavingsAccount.setBalance((newSavingsAccount.getBalance() + Integer.valueOf(currentAmount)));

                clearCenterBox();
                getUI.updateTransactionLabel("Deposited: $" + currentAmount + "\n To: " + currentAccount + " account");
                centerBox.getChildren().add(getUI.getTransactionLabel());

                System.out.println("Deposited money");
                break;
            case "Net savings":

                newNetSavingsAccount.setBalance((newNetSavingsAccount.getBalance() + Integer.valueOf(currentAmount)));

                clearCenterBox();
                getUI.updateTransactionLabel("Deposited: $" + currentAmount + "\n To: " + currentAccount + " account");
                centerBox.getChildren().add(getUI.getTransactionLabel());

                System.out.println("Deposited money");
                break;

            case "Fixed":

                newFixedAccount.setBalance((newFixedAccount.getBalance() + Integer.valueOf(currentAmount)));

                clearCenterBox();
                getUI.updateTransactionLabel("Deposited: $" + currentAmount + "\n To: " + currentAccount + " account");
                centerBox.getChildren().add(getUI.getTransactionLabel());

                System.out.println("Deposited money");
                break;

            case "Cheque":

                newChequeAccount.setBalance((newChequeAccount.getBalance() + Integer.valueOf(currentAmount)));

                clearCenterBox();
                getUI.updateTransactionLabel("Deposited: $" + currentAmount + "\n To: " + currentAccount + " account");
                centerBox.getChildren().add(getUI.getTransactionLabel());

                System.out.println("Deposited money");
                break;
            default:
                break;
        }
    }

    @Override
    public void signIn() {
        if (getUI.getPinField().getText().equals(newCustomer.getPin())) {

            centerBox.getChildren().removeAll(getUI.getPinLabel(), getUI.getPinField());
            centerBox.setSpacing(20);
            centerBox.getChildren().addAll(getUI.getTransactionLabel(), getUI.getWithdrawBtn(), getUI.getDepositBtn(), getUI.getBalanceBtn());

            leftBox.getChildren().add(getUI.getCancelReturnBtn());
        } else {

            getUI.getPinField().clear();
            getUI.getPinField().setPromptText("bad PIN");
        }
    }

    @Override
    public void signOut() {
    }

    private void resetATM() throws EmptyFieldException {

        getUI.updateTransactionLabel("What would you like to do?");
        getUI.resetAmountBtnId();
        getUI.cancelPinField();

        clearCenterBox();
        clearLeftBox();
        clearRightBox();
        centerBox.setSpacing(5);

        centerBox.getChildren().addAll(getUI.getPinLabel(), getUI.getPinField());
        centerBox.setAlignment(Pos.CENTER);

        currentTransaction = "";
    }

    private void amountBtnPressed() {

        rightBox.getChildren().removeAll(getUI.getLimitAmountField(), getUI.getContinueTransactionBtn());

        if (currentAmount.equals("other")) {

            rightBox.getChildren().addAll(getUI.getLimitAmountField(), getUI.getContinueTransactionBtn());
        } else {

            rightBox.getChildren().add(getUI.getContinueTransactionBtn());
        }
    }

    private void showAmountButtons() {

        getUI.updateTransactionLabel(currentTransaction + " an amount from " + "\n" + currentAccount + " account");

        clearCenterBox();
        clearLeftBox();
        leftCancelButtons();

        centerBox.getChildren().add(getUI.getTransactionLabel());
        centerBox.getChildren().add(centerGrid);
    }

    private void showAccounts() {

        getUI.updateTransactionLabel("Choose account");
        clearCenterBox();
        centerBox.getChildren().addAll(getUI.getTransactionLabel(), getUI.getSavingsBtn(), getUI.getNetSavingsBtn(), getUI.getFixedBtn(), getUI.getChequeBtn());
    }

    private void leftCancelButtons() {
        leftBox.getChildren().addAll(getUI.getCancelTransactionBtn(), getUI.getCancelReturnBtn());
    }

    private void clearCenterBox() {
        centerBox.getChildren().clear();
    }

    private void clearLeftBox() {
        leftBox.getChildren().clear();
    }

    private void clearRightBox() {
        rightBox.getChildren().clear();
    }

    @Override
    public boolean checkNotes() {

        if (currentAmount.equals("other")) {
            currentAmount = getUI.getAmountFieldATM().getText();
        }
        currentAmount = currentAmount.replaceAll("[^0-9]", "");

        if (Integer.valueOf(currentAmount) < 20) {

            System.err.println("Amount too small");
            return false;
        } else if (Integer.valueOf(currentAmount) > 10000) {

            System.err.println("Amount too big");
            return false;
        } else if (!currentAmount.endsWith("0")) {

            System.err.println("Bad amount given");
            return false;
        } else {

            return true;
        }
    }
}
