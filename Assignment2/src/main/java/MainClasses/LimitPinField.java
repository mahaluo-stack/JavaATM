/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

import javafx.scene.control.PasswordField;

/**
 *
 * @author jolta
 */
public class LimitPinField extends PasswordField {

    private int pinLength;

    public LimitPinField() {
        this.pinLength = 4;
    }

    public void setMaxlength(int maxlength) {
        this.pinLength = maxlength;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < pinLength) {
            super.replaceText(start, end, text);
        }
    }
}
