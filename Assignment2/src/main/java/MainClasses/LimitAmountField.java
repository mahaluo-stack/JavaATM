/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import javafx.scene.control.TextField;

/**
 *
 * @author jolta
 */
public class LimitAmountField extends TextField {

    private int amountLength;

    public LimitAmountField() {
        this.amountLength = 5;
    }

    public void setMaxlength(int maxlength) {
        this.amountLength = maxlength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < amountLength) {
            super.replaceText(start, end, text);
        }
    }
}
