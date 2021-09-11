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
import Interfaces.MainOperations;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jolta
 */
public class Web extends Application implements MainOperations {

    BorderPane root;
    WebUI getUI;
    HBox topBox, accountBox;
    VBox leftBox, rightBox, centerBox, footerBox;
    GridPane signInGrid;
    Customer newCustomer;
    Account newChequeAccount, newFixedAccount, newNetSavingsAccount, newSavingsAccount;
    String currentAccount, currentCustomer, currentAction;
    String toOutPut = "";
    File customerFiles, customerDOB;

    String accNr = "";
    String bsbNr = "";
    Double balance = 0.0;
    Double withdrawLimit = 0.0;
    Double withdrawTotal = 0.0;
    Double interestRate = 0.0;
    Double interestGained = 0.0;
    int accountCounter = 0;
    List<Button> buttonList = new ArrayList<>();

    public static void main(String... args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        initUI(stage);
    }

    public void initUI(Stage stage) {

        getUI = new WebUI();
        root = new BorderPane();
        Scene scene = new Scene(root, 1100, 900);
        root.setId("root");
        signInGrid = new GridPane();
        newCustomer = new Customer();

        getUI.setButtons();
        getUI.setLabels();
        getUI.setFields();

        stage.setScene(scene);
        scene.getStylesheets().add("/styles/WebUIstyles.css");

//<editor-fold defaultstate="collapsed" desc="Grid Constraints">
        GridPane.setConstraints(getUI.getWelcomeLabel(), 0, 0);
        GridPane.setConstraints(getUI.getSignInField(), 0, 1);
        GridPane.setConstraints(getUI.getPasswordField(), 0, 2);
        GridPane.setConstraints(getUI.getSignInBtn(), 0, 3);
        GridPane.setConstraints(getUI.getNewAccountBtn(), 0, 4);
        GridPane.setConstraints(getUI.getExitBtn(), 0, 5);
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Set Start Page">
        centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getSignInField(), getUI.getPasswordField(), getUI.getSignInBtn(), getUI.getNewAccountBtn(), getUI.getExitBtn());
        centerBox.setSpacing(3);

        getUI.getSignInBtn().setId("signInBtn");
        getUI.getExitBtn().setId("signInBtn");
        getUI.newAccountBtn.setId("signInBtn");

        root.setCenter(centerBox);

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="New Account Button">
        getUI.getNewAccountBtn().setOnAction((t) -> {

            getUI.updateWelcomeLabel("Enter your details to get started");
            centerBox.getChildren().removeAll(getUI.getWelcomeLabel(), getUI.getSignInField(), getUI.getPasswordField(), getUI.getSignInBtn(), getUI.getNewAccountBtn(), getUI.getExitBtn());
            centerBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getFirstNameField(), getUI.getLastNameField(), getUI.getDobField(), getUI.getAdressField(), getUI.getPinField(), getUI.getPasswordField(), getUI.getRegisterBtn(), getUI.getReturnBtn(), getUI.getExitBtn());
        });
        getUI.getReturnBtn().setOnAction((t) -> {

            getUI.updateWelcomeLabel("Welcome to Money Bin! Please sign in to continue.");
            centerBox.getChildren().removeAll(getUI.getWelcomeLabel(), getUI.getFirstNameField(), getUI.getLastNameField(), getUI.getDobField(), getUI.getAdressField(), getUI.getPinField(), getUI.getPasswordField(), getUI.getRegisterBtn(), getUI.getReturnBtn(), getUI.getExitBtn());
            centerBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getSignInField(), getUI.getPasswordField(), getUI.getSignInBtn(), getUI.getNewAccountBtn(), getUI.getExitBtn());
        });

        getUI.getRegisterBtn().setOnAction((ActionEvent t) -> {

            register();
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Option Menu">
//<editor-fold defaultstate="collapsed" desc="Option Menu Button">
        getUI.getOptionMenuBtn().setOnMouseClicked((t) -> {

            rightBox.getChildren().clear();
            getUI.getWelcomeLabel().setId("welcomeLabel");

            leftBox.getChildren().removeAll(getUI.getToggleTranfer(), getUI.getToggleHistory(), getUI.getToggleWithdrawLimit());
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Option Menu Items">
        getUI.getOpenAccount().setOnAction((t) -> {

            rightBox.getChildren().removeAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            getUI.updateWelcomeLabel("Open Account");
            getUI.getWelcomeLabel().setId("openAccountLabel");
            rightBox.setSpacing(4);
            rightBox.setAlignment(Pos.TOP_CENTER);
            rightBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());
            currentAction = "openAccount";
        });

        getUI.getCloseAccount().setOnAction((t) -> {

            rightBox.getChildren().removeAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            getUI.updateWelcomeLabel("Close Account");
            getUI.getWelcomeLabel().setId("openAccountLabel");
            rightBox.setSpacing(4);
            rightBox.setAlignment(Pos.TOP_CENTER);
            rightBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());
            currentAction = "closeAccount";
        });

        getUI.getUpdateDetails().setOnAction((t) -> {
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Account Buttons">
        getUI.getChequeAccountBtn().setOnAction((t) -> {

            getUI.getWelcomeLabel().setId("confirmAccountLabel");
            getUI.getConfirmNewAccountBtn().setId("accountBtn");
            currentAccount = "Cheque";
            rightBox.getChildren().removeAll(getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());

            if (currentAction.equals("openAccount")) {

                getUI.updateWelcomeLabel("Open Cheque Account?");
                rightBox.getChildren().addAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            }
            if (currentAction.equals("closeAccount")) {

                getUI.updateWelcomeLabel("Close Cheque Account?");
                setCloseAccountButtons(currentAccount);
            }
        });
        getUI.getFixedAccountBtn().setOnAction((t) -> {

            getUI.getWelcomeLabel().setId("confirmAccountLabel");
            getUI.getConfirmNewAccountBtn().setId("accountBtn");
            currentAccount = "Fixed";
            rightBox.getChildren().removeAll(getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());

            if (currentAction.equals("openAccount")) {

                getUI.updateWelcomeLabel("Open Fixed Account?");
                rightBox.getChildren().addAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            }
            if (currentAction.equals("closeAccount")) {

                getUI.updateWelcomeLabel("Close Fixed Account?");
                setCloseAccountButtons(currentAccount);
            }
        });
        getUI.getNetSavingsAccountBtn().setOnAction((t) -> {

            getUI.getWelcomeLabel().setId("confirmAccountLabel");
            getUI.getConfirmNewAccountBtn().setId("accountBtn");
            currentAccount = "Net Savings";
            rightBox.getChildren().removeAll(getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());

            if (currentAction.equals("openAccount")) {

                getUI.updateWelcomeLabel("Open Net Savings Account?");
                rightBox.getChildren().addAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            }
            if (currentAction.equals("closeAccount")) {

                getUI.updateWelcomeLabel("Close Net Savings Account?");
                setCloseAccountButtons(currentAccount);
            }
        });
        getUI.getSavingsAccountBtn().setOnAction((t) -> {

            getUI.getWelcomeLabel().setId("confirmAccountLabel");
            getUI.getConfirmNewAccountBtn().setId("accountBtn");
            currentAccount = "Savings";
            rightBox.getChildren().removeAll(getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());

            if (currentAction.equals("openAccount")) {

                getUI.updateWelcomeLabel("Open Savings Account?");
                rightBox.getChildren().addAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            }
            if (currentAction.equals("closeAccount")) {

                getUI.updateWelcomeLabel("Close Savings Account?");
                setCloseAccountButtons(currentAccount);
            }
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cancel & Confirm Buttons">
        getUI.getCancelNewAccountBtn().setOnAction((t) -> {

            rightBox.getChildren().clear();
        });

        getUI.getConfirmNewAccountBtn().setOnAction((t) -> {

            if (currentAction.equals("openAccount")) {

                getUI.updateWelcomeLabel(currentAccount + " Account opened");
                openAccount(currentAccount);
                rightBox.getChildren().removeAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
                rightBox.getChildren().removeAll(getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());
                showAccounts(currentAccount);
            }
            if (currentAction.equals("closeAccount")) {
                rightBox.getChildren().clear();
            }

        });
//</editor-fold>

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="Account Menu">
        getUI.getAccountMenuBtn().setOnMouseClicked((t) -> {

            leftBox.getChildren().removeAll(getUI.getToggleTranfer(), getUI.getToggleHistory(), getUI.getToggleWithdrawLimit());

            centerBox.getChildren().removeAll(getUI.getAccountTable());

            rightBox.getChildren().removeAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
            rightBox.getChildren().removeAll(getUI.getWelcomeLabel(), getUI.getChequeAccountBtn(), getUI.getFixedAccountBtn(), getUI.getNetSavingsAccountBtn(), getUI.getSavingsAccountBtn());
            getUI.getWelcomeLabel().getStyleClass().add("welcomeLabel");
        });

        getUI.getChequeMenu().setOnAction((ActionEvent t) -> {

            currentAccount = "Cheque";
            showAccounts(currentAccount);
        });

        getUI.getFixedMenu().setOnAction((ActionEvent t) -> {

            currentAccount = "Fixed";
            showAccounts(currentAccount);
        });

        getUI.getNetSavingsMenu().setOnAction((ActionEvent t) -> {

            currentAccount = "Net Savings";
            showAccounts(currentAccount);
        });

        getUI.getSavingsMenu().setOnAction((ActionEvent t) -> {

            currentAccount = "Savings";
            showAccounts(currentAccount);
        });
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sign In/Out + Exit Button">
        getUI.getSignInBtn().setOnAction((t) -> {

            signIn();
        });

        getUI.getSignOutBtn().setOnAction((t) -> {

            signOut();
        });

        getUI.getExitBtn().setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
//</editor-fold>

        stage.show();
    }

    public void register() {

        newCustomer.setName(getUI.getFirstNameField().getText() + " " + getUI.getLastNameField().getText());
        newCustomer = new Customer(newCustomer.getName(), getUI.getAdressField().getText(), getUI.getPinField().getText(), getUI.getPasswordField().getText(), getUI.getDobField().getText());
        new File("./" + "Customer " + newCustomer.getDob() + " -").mkdir();

        try {

            File outputFile = new File("./" + "/Customer " + newCustomer.getDob() + " -/" + newCustomer.getDob() + ".txt");

            FileWriter out = new FileWriter(outputFile);
            PrintWriter pw = new PrintWriter(out);

            pw.println(newCustomer.getName() + "\n");
            pw.println(newCustomer.getAdress() + "\n");
            pw.println(newCustomer.getPin() + "\n");
            pw.println(newCustomer.getPassword() + "\n");
            pw.println(newCustomer.getDob() + "\n");
            out.close();
            pw.close();

            getUI.resetSignIn();
            getUI.resetRegister();
            System.out.println("Register succesful");
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
    }

    public void openAccount(String currentAccount) {

        Random accountDigits = new Random();

        long accountNumber = 10000000000L + ((long) accountDigits.nextInt(900000000) * 100) + accountDigits.nextInt(100);

        int bsbNumber1 = accountDigits.nextInt(9);
        int bsbNumber2 = accountDigits.nextInt(9);
        int bsbNumber3 = accountDigits.nextInt(9);

        String bsbNumber = String.valueOf(bsbNumber1) + String.valueOf(bsbNumber2) + String.valueOf(bsbNumber3);

        System.out.println("Created account number: " + accountNumber);
        System.out.println("Created BSB number: " + bsbNumber);
        System.out.println("For account type: " + currentAccount + " Account");

        switch (currentAccount) {

            case "Cheque":

                newChequeAccount = new ChequeAccount(String.valueOf(accountNumber), bsbNumber, 0.0, 0.0);
                try {

                    File outputFile = new File("./" + "/Customer " + currentCustomer + " -/" + currentAccount + " " + bsbNumber + ".txt");

                    FileWriter out = new FileWriter(outputFile);
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("Account Number: " + newChequeAccount.getAccountNumber() + "\n");
                    pw.println("BSB Number: " + newChequeAccount.getbsbNumber() + "\n");
                    pw.println("Balance: " + newChequeAccount.getBalance() + "\n");
                    pw.println("Daily Withdraw Total: " + newChequeAccount.getDailyWithdrawTotal() + "\n");
                    out.close();
                    pw.close();
                } catch (IOException ex) {
                    System.err.println("Error: " + ex);
                }

                break;
            case "Fixed":

                newFixedAccount = new FixedAccount(String.valueOf(accountNumber), bsbNumber, 0.0, 0.0, 0.04, 0.0);
                try {

                    File outputFile = new File("./" + "/Customer " + currentCustomer + " -/" + currentAccount + " " + bsbNumber + ".txt");

                    FileWriter out = new FileWriter(outputFile);
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("Account Number: " + newFixedAccount.getAccountNumber() + "\n");
                    pw.println("BSB Number: " + newFixedAccount.getbsbNumber() + "\n");
                    pw.println("Balance: " + newFixedAccount.getBalance() + "\n");
                    pw.println("Daily Withdraw Total: " + newFixedAccount.getDailyWithdrawTotal() + "\n");
                    pw.println("Interest Rate: " + newFixedAccount.getInterestRate() + "\n");
                    pw.println("Interest Gained: " + newFixedAccount.getInterestGained() + "\n");
                    out.close();
                    pw.close();
                } catch (IOException ex) {
                    System.err.println("Error: " + ex);
                }
                break;
            case "Net Savings":

                newNetSavingsAccount = new NetSavingsAccount(String.valueOf(accountNumber), bsbNumber, 0.0, 2500.0, 0.0, 0.023, 0.0);
                try {

                    File outputFile = new File("./" + "/Customer " + currentCustomer + " -/" + currentAccount + " " + bsbNumber + ".txt");

                    FileWriter out = new FileWriter(outputFile);
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("Account Number: " + newNetSavingsAccount.getAccountNumber() + "\n");
                    pw.println("BSB Number: " + newNetSavingsAccount.getbsbNumber() + "\n");
                    pw.println("Balance: " + newNetSavingsAccount.getBalance() + "\n");
                    pw.println("Withdraw Limit: " + newNetSavingsAccount.getWithdrawLimit() + "\n");
                    pw.println("Daily Withdraw Total: " + newNetSavingsAccount.getDailyWithdrawTotal() + "\n");
                    pw.println("Interest Rate: " + newNetSavingsAccount.getInterestRate() + "\n");
                    pw.println("Interest Gained: " + newNetSavingsAccount.getInterestGained() + "\n");
                    out.close();
                    pw.close();
                } catch (IOException ex) {
                    System.err.println("Error: " + ex);
                }
                break;
            case "Savings":

                newSavingsAccount = new SavingsAccount(String.valueOf(accountNumber), bsbNumber, 0.0, 2500.0, 0.0, 0.015, 0.0);
                try {

                    File outputFile = new File("./" + "/Customer " + currentCustomer + " -/" + currentAccount + " " + bsbNumber + ".txt");

                    FileWriter out = new FileWriter(outputFile);
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("Account Number: " + newSavingsAccount.getAccountNumber() + "\n");
                    pw.println("BSB Number: " + newSavingsAccount.getbsbNumber() + "\n");
                    pw.println("Balance: " + newSavingsAccount.getBalance() + "\n");
                    pw.println("Withdraw Limit: " + newSavingsAccount.getWithdrawLimit() + "\n");
                    pw.println("Daily Withdraw Total: " + newSavingsAccount.getDailyWithdrawTotal() + "\n");
                    pw.println("Interest Rate: " + newSavingsAccount.getInterestRate() + "\n");
                    pw.println("Interest Gained: " + newSavingsAccount.getInterestGained() + "\n");
                    out.close();
                    pw.close();
                } catch (IOException ex) {
                    System.err.println("Error: " + ex);
                }
                break;
            default:
                break;
        }
    }

    public void closeAccount(File currentAccount) {

        System.out.println(currentAccount);
        currentAccount.delete();

        rightBox.getChildren().clear();
        rightBox.getChildren().addAll(getUI.getConfirmNewAccountBtn(), getUI.getCancelNewAccountBtn());
    }

    public void setCloseAccountButtons(String currentAccount) {

        File[] accountButtons = customerFiles.listFiles((dir1, name) -> name.startsWith(currentAccount));

        for (File file : accountButtons) {

            if (file.getName().startsWith(currentAccount)) {

                try {

                    FileReader in = new FileReader(file);
                    BufferedReader bf = new BufferedReader(in);
                    String line;
                    while ((line = bf.readLine()) != null) {

                        if (line.startsWith("Account Number: ")) {
                            accNr = line.replaceAll("[^0-9]", "");
                            buttonList.add(getUI.setCloseAccountBtn(accNr));
                            buttonList.get(accountCounter).setOnAction((t) -> {
                                closeAccount(file);
                            });
                            rightBox.getChildren().add(buttonList.get(accountCounter));
                        }
                    }

//                    in.close();
//                    bf.close();
                } catch (IOException ex) {

                    System.err.println("Error: " + ex);
                }
            }
            accountCounter++;
        }

        System.out.println("Found " + accountCounter + " " + currentAccount + " Accounts");
        accountCounter = 0;
    }

    public void setAccountWindow() {

        topBox = new HBox(getUI.getAccountMenuBtn(), getUI.getTopLabel(), getUI.getOptionMenuBtn());
        topBox.setId("topBox");
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(300.0);
        root.setTop(topBox);

        leftBox = new VBox();
        leftBox.setId("leftBox");
        leftBox.setSpacing(2);
        root.setLeft(leftBox);

        rightBox = new VBox();
        rightBox.setId("rightBox");
        leftBox.setAlignment(Pos.TOP_CENTER);
        root.setRight(rightBox);

        centerBox = new VBox();
        centerBox.setId("centerBox");
        centerBox.setAlignment(Pos.TOP_CENTER);
        root.setCenter(centerBox);

        footerBox = new VBox(getUI.getSignOutBtn(), getUI.getExitBtn());
        getUI.getSignOutBtn().setId("footerBtn");
        getUI.getExitBtn().setId("footerBtn");
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setId("footerBox");
        root.setBottom(footerBox);
    }

    @Override
    public void showAccounts(String currentAccount) {

        centerBox.getChildren().removeAll(getUI.getAccountTable());
        centerBox.getChildren().addAll(getUI.getAccountTable());
        File[] accounts = customerFiles.listFiles((dir1, name) -> name.startsWith(currentAccount));

        getUI.getAccountTable().getItems().clear();
        accountCounter = 0;

        for (File file : accounts) {

            if (file.getName().startsWith(currentAccount)) {

                try {

                    FileReader in = new FileReader(file);
                    BufferedReader bf = new BufferedReader(in);
                    String line;
                    while ((line = bf.readLine()) != null) {

                        if (line.startsWith("Account Number: ")) {

                            accNr = line.replaceAll("[^0-9]", "");
                        }
                        if (line.startsWith("BSB Number: ")) {

                            bsbNr = line.replaceAll("[^0-9]", "");
                        }
                        if (line.startsWith("Balance: ")) {

                            String[] getBalance = line.split(" ");
                            balance = Double.valueOf(getBalance[1]);
                        }
                        if (line.startsWith("Withdraw Limit: ")) {

                            String[] getLimit = line.split(" ");
                            withdrawLimit = Double.valueOf(getLimit[2]);
                        }
                        if (line.startsWith("Daily Withdraw Total: ")) {

                            String[] getTotal = line.split(" ");
                            withdrawTotal = Double.valueOf(getTotal[3]);
                        }
                        if (line.startsWith("Interest Rate: ")) {

                            String[] getRate = line.split(" ");
                            interestRate = Double.valueOf(getRate[2]);
                        }
                        if (line.startsWith("Interest Gained: ")) {

                            String[] getRate = line.split(" ");
                            interestGained = Double.valueOf(getRate[2]);
                        }
                    }

                    if (currentAccount.equals("Cheque")) {
                        getUI.getAccountTable().getItems().add(new ChequeAccount(accNr, bsbNr, balance, withdrawTotal));
                    }
                    if (currentAccount.equals("Fixed")) {
                        getUI.getAccountTable().getItems().add(new FixedAccount(accNr, bsbNr, balance, withdrawTotal, interestRate, interestGained));
                    }
                    if (currentAccount.equals("Net Savings")) {
                        getUI.getAccountTable().getItems().add(new NetSavingsAccount(accNr, bsbNr, balance, withdrawLimit, withdrawTotal, interestRate, interestGained));
                    }
                    if (currentAccount.equals("Savings")) {
                        getUI.getAccountTable().getItems().add(new SavingsAccount(accNr, bsbNr, balance, withdrawLimit, withdrawTotal, interestRate, interestGained));
                    }

                    in.close();
                    bf.close();
                } catch (IOException ex) {

                    System.err.println("Error: " + ex);
                }
            }
            accountCounter++;
        }
        leftBox.getChildren().removeAll(getUI.getToggleTranfer(), getUI.getToggleHistory(), getUI.getToggleWithdrawLimit());
        leftBox.getChildren().addAll(getUI.getToggleTranfer(), getUI.getToggleHistory(), getUI.getToggleWithdrawLimit());
        System.out.println("Found " + accountCounter + " " + currentAccount + " Accounts");
    }

    @Override
    public void signIn() {

        Boolean foundDOB = false;
        customerDOB = new File(".");
        File[] files = customerDOB.listFiles((dir1, name) -> name.startsWith("Customer"));

        for (File file : files) {

            if (file != null) {

                if (file.getName().equals("Customer " + getUI.getSignInField().getText() + " -")) {

                    System.out.println("DOB Found");
                    foundDOB = true;
                    break;
                } else {

                    foundDOB = false;
                }
            }
        }
        if (foundDOB == true) {

            Boolean foundPassword = false;

            try {

                File CustomerPassword = new File("Customer " + getUI.getSignInField().getText() + " -" + "\\" + getUI.getSignInField().getText() + ".txt");
                FileReader in = new FileReader(CustomerPassword);
                BufferedReader bf = new BufferedReader(in);
                String line;

                while ((line = bf.readLine()) != null) {
                    if (getUI.getPasswordField().getText().equals(line)) {

                        foundPassword = true;
                    }
                }
                if (foundPassword == true) {

                    System.out.println("Password Found!");
                    currentCustomer = getUI.getSignInField().getText();
                    customerFiles = new File("Customer " + currentCustomer + " -" + "\\");
                    centerBox.getChildren().removeAll(getUI.getWelcomeLabel(), getUI.getSignInField(), getUI.getPasswordField(), getUI.getSignInBtn(), getUI.getNewAccountBtn(), getUI.getExitBtn());
                    setAccountWindow();
                } else {

                    getUI.resetSignIn();
                    getUI.getPasswordField().setPromptText("Bad Password!");
                }

                in.close();
                bf.close();
            } catch (IOException ex) {

                System.err.println("Error: " + ex);
            }
        } else {

            getUI.resetSignIn();
            getUI.getSignInField().setPromptText("Bad DOB!");
        }
    }

    @Override
    public void signOut() {

        root.getChildren().removeAll(topBox, leftBox, rightBox, footerBox, centerBox);
        getUI.resetSignIn();
        centerBox.getChildren().removeAll(getUI.getAccountTable());

        centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(getUI.getWelcomeLabel(), getUI.getSignInField(), getUI.getPasswordField(), getUI.getSignInBtn(), getUI.getNewAccountBtn(), getUI.getExitBtn());
        centerBox.setSpacing(3);

        getUI.getReturnBtn().setId("signInBtn");
        getUI.getSignInBtn().setId("signInBtn");
        getUI.getExitBtn().setId("signInBtn");
        getUI.newAccountBtn.setId("signInBtn");

        root.setCenter(centerBox);
    }

    @Override
    public void showDetails() {
    }

    @Override
    public void transfer() {
    }
}
