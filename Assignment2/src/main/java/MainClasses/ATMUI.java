/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import Exceptions.EmptyFieldException;
import Interfaces.InitUI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author jolta
 */
public class ATMUI implements InitUI {

    Button digit0Btn, digit1Btn, digit2Btn, digit3Btn, digit4Btn, digit5Btn, digit6Btn, digit7Btn, digit8Btn, digit9Btn, digitBlank1Btn, digitBlank2Btn;
    Button amount20Btn, amount40Btn, amount60Btn, amount80Btn, amount100Btn, amount200Btn, amount300Btn;
    Button cancelBtn, clearBtn, enterBtn, otherBtn, blankBtn;
    Button withdrawBtn, depositBtn, balanceBtn, cancelReturnBtn, continueTransactionBtn, cancelTransactionBtn, confirmTransactionBtn;
    Button chequeBtn, fixedBtn, netSavingsBtn, savingsBtn;

    LimitPinField pinField;

    LimitAmountField amountFieldATM;

    Label pinLabel, transactionLabel, confirmTransactionLabel;

    
    public ATMUI() {
    }

    @Override
    public void setFields() {

        pinField = new LimitPinField();
        pinField.setPromptText("X X X X");
        pinField.setId("pinField");
        pinField.setMaxlength(4);
    }

    @Override
    public void setLabels() {

        pinLabel = new Label("Enter PIN");
        pinLabel.setId("pinLabel");

        transactionLabel = new Label("What would you like to do?");
        transactionLabel.setId("transactionLabel");

        confirmTransactionLabel = new Label();
        confirmTransactionLabel.setId("transactionLabel");
    }

    @Override
    public void setButtons() {

        digit0Btn = new Button("0");
        digit0Btn.setId("keyPadBtn");
        digit1Btn = new Button("1");
        digit1Btn.setId("keyPadBtn");
        digit2Btn = new Button("2");
        digit2Btn.setId("keyPadBtn");
        digit3Btn = new Button("3");
        digit3Btn.setId("keyPadBtn");
        digit4Btn = new Button("4");
        digit4Btn.setId("keyPadBtn");
        digit5Btn = new Button("5");
        digit5Btn.setId("keyPadBtn");
        digit6Btn = new Button("6");
        digit6Btn.setId("keyPadBtn");
        digit7Btn = new Button("7");
        digit7Btn.setId("keyPadBtn");
        digit8Btn = new Button("8");
        digit8Btn.setId("keyPadBtn");
        digit9Btn = new Button("9");
        digit9Btn.setId("keyPadBtn");
        digitBlank1Btn = new Button("");
        digitBlank1Btn.setId("blankKeyBtn");
        digitBlank2Btn = new Button("");
        digitBlank2Btn.setId("blankKeyBtn");

        amount20Btn = new Button("$20");
        amount20Btn.setId("amountBtn");
        amount40Btn = new Button("$40");
        amount40Btn.setId("amountBtn");
        amount60Btn = new Button("$60");
        amount60Btn.setId("amountBtn");
        amount80Btn = new Button("$80");
        amount80Btn.setId("amountBtn");
        amount100Btn = new Button("$100");
        amount100Btn.setId("amountBtn");
        amount200Btn = new Button("$200");
        amount200Btn.setId("amountBtn");
        amount300Btn = new Button("$300");
        amount300Btn.setId("amountBtn");

        otherBtn = new Button("Other");
        otherBtn.setId("amountBtn");

        cancelBtn = new Button("CANCEL");
        cancelBtn.setId("cancelBtn");
        clearBtn = new Button("CLEAR");
        clearBtn.setId("clearBtn");
        enterBtn = new Button("ENTER");
        enterBtn.setId("enterBtn");
        blankBtn = new Button("");
        blankBtn.setId("blankBtn");

        withdrawBtn = new Button("Withdraw");
        withdrawBtn.setId("transactionBtn");
        depositBtn = new Button("Deposit");
        depositBtn.setId("transactionBtn");
        balanceBtn = new Button("View Balance");
        balanceBtn.setId("transactionBtn");

        cancelReturnBtn = new Button("Exit and Return Card");
        cancelReturnBtn.setId("cancelReturnBtn");

        continueTransactionBtn = new Button("Continue >>");
        continueTransactionBtn.setId("continueTransactionBtn");

        cancelTransactionBtn = new Button("Cancel Transaction");
        cancelTransactionBtn.setId("cancelTransactionBtn");

        confirmTransactionBtn = new Button("Confirm");
        confirmTransactionBtn.setId("continueTransactionBtn");

        chequeBtn = new Button("Cheque");
        chequeBtn.setId("accountBtn");
        fixedBtn = new Button("Fixed");
        fixedBtn.setId("accountBtn");
        netSavingsBtn = new Button("Net Savings");
        netSavingsBtn.setId("accountBtn");
        savingsBtn = new Button("Savings");
        savingsBtn.setId("accountBtn");

        amountFieldATM = new LimitAmountField();
        amountFieldATM.setPromptText("Use keypad to enter");
        amountFieldATM.setId("amountField");
    }

    public void updatePinField(Integer digit) {

        if (pinField.getText().length() >= 4) {

            pinField.replaceText(0, 4, pinField.getText());
        } else {

            pinField.setText(pinField.getText() + String.valueOf(digit));
        }
    }

    public void updateAmountFieldATM(Integer digit) {

        if (amountFieldATM.getText().length() >= 5) {

            amountFieldATM.replaceText(0, 5, amountFieldATM.getText());
        } else {

            amountFieldATM.setText(amountFieldATM.getText() + String.valueOf(digit));
        }
    }

    public void clearPinField() throws EmptyFieldException {

        if (!pinField.getText().isEmpty()) {

            pinField.setText(pinField.getText().substring(0, pinField.getText().length() - 1));
        } else {

            throw new EmptyFieldException();
        }
    }

    public void clearAmountFieldATM() throws EmptyFieldException {

        if (!amountFieldATM.getText().isEmpty()) {

            amountFieldATM.setText(amountFieldATM.getText().substring(0, amountFieldATM.getText().length() - 1));
        } else {

            throw new EmptyFieldException();
        }
    }

    public void cancelPinField() throws EmptyFieldException {

        if (!pinField.getText().isEmpty()) {

            pinField.setText("");
        } else {

            throw new EmptyFieldException();
        }
    }

    public void cancelAmountFieldATM() throws EmptyFieldException {

        if (!amountFieldATM.getText().isEmpty()) {

            amountFieldATM.setText("");
        } else {

            throw new EmptyFieldException();
        }
    }

    public void resetAmountBtnId() {

        amount20Btn.setId("amountBtn");
        amount40Btn.setId("amountBtn");
        amount60Btn.setId("amountBtn");
        amount80Btn.setId("amountBtn");
        amount100Btn.setId("amountBtn");
        amount200Btn.setId("amountBtn");
        amount300Btn.setId("amountBtn");
        otherBtn.setId("amountBtn");
    }

    public void updateTransactionLabel(String text) {

        transactionLabel.setText(text);
    }

    public void updateConfirmTransactionLabel(String transactionType, String amount, String account) {

        if (transactionType.equals("Deposit")) {
            confirmTransactionLabel.setText(transactionType + ": " + amount + " " + "\nTo: " + account + " account");
        } else {
            confirmTransactionLabel.setText(transactionType + ": " + amount + " " + "\nFrom: " + account + " account");
        }
    }

    public Button getDigit0Btn() {
        return digit0Btn;
    }

    public Button getDigit1Btn() {
        return digit1Btn;
    }

    public Button getDigit2Btn() {
        return digit2Btn;
    }

    public Button getDigit3Btn() {
        return digit3Btn;
    }

    public Button getDigit4Btn() {
        return digit4Btn;
    }

    public Button getDigit5Btn() {
        return digit5Btn;
    }

    public Button getDigit6Btn() {
        return digit6Btn;
    }

    public Button getDigit7Btn() {
        return digit7Btn;
    }

    public Button getDigit8Btn() {
        return digit8Btn;
    }

    public Button getDigit9Btn() {
        return digit9Btn;
    }

    public Button getDigitBlank1Btn() {
        return digitBlank1Btn;
    }

    public Button getDigitBlank2Btn() {
        return digitBlank2Btn;
    }

    public Button getAmount20Btn() {
        return amount20Btn;
    }

    public Button getAmount40Btn() {
        return amount40Btn;
    }

    public Button getAmount60Btn() {
        return amount60Btn;
    }

    public Button getAmount80Btn() {
        return amount80Btn;
    }

    public Button getAmount100Btn() {
        return amount100Btn;
    }

    public Button getAmount200Btn() {
        return amount200Btn;
    }

    public Button getAmount300Btn() {
        return amount300Btn;
    }

    public Button getOtherBtn() {
        return otherBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public Button getEnterBtn() {
        return enterBtn;
    }

    public LimitAmountField getAmountFieldATM() {
        return amountFieldATM;
    }

    public LimitPinField getPinField() {
        return pinField;
    }

    public Button getBlankBtn() {
        return blankBtn;
    }

    public Label getPinLabel() {
        return pinLabel;
    }

    public Button getWithdrawBtn() {
        return withdrawBtn;
    }

    public Button getDepositBtn() {
        return depositBtn;
    }

    public Button getBalanceBtn() {
        return balanceBtn;
    }

    public Label getTransactionLabel() {
        return transactionLabel;
    }

    public Button getCancelReturnBtn() {
        return cancelReturnBtn;
    }

    public Button getContinueTransactionBtn() {
        return continueTransactionBtn;
    }

    public Button getCancelTransactionBtn() {
        return cancelTransactionBtn;
    }

    public Button getChequeBtn() {
        return chequeBtn;
    }

    public Button getFixedBtn() {
        return fixedBtn;
    }

    public Button getNetSavingsBtn() {
        return netSavingsBtn;
    }

    public Button getSavingsBtn() {
        return savingsBtn;
    }

    public Label getConfirmTransactionLabel() {
        return confirmTransactionLabel;
    }

    public Button getConfirmTransactionBtn() {
        return confirmTransactionBtn;
    }

    public LimitAmountField getLimitAmountField() {

        return amountFieldATM;
    }
}
