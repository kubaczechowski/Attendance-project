package sample.gui.util;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

public class RegexValidator extends ValidatorBase {
    private String regEx;

    public RegexValidator(String message, String regEx) {
        super(message);
        this.regEx = regEx;
    }

    @Override
    protected void eval() {
        TextInputControl textField = (TextInputControl) srcControl.get();
        if ( (textField.getText() != null && !textField.getText().isEmpty())
                && !textField.getText().matches(regEx)) {
            hasErrors.set(true);
        } else {
            hasErrors.set(false);
        }
    }

/*
    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl) {
            evalTextInputField();
        }
        if (srcControl.get() instanceof ComboBoxBase) {
            evalComboBoxField();
        }
    }
//
    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) srcControl.get();
        if ( (textField.getText() != null || !textField.getText().isEmpty()) && !textField.getText().matches(regEx)) {
            hasErrors.set(true);
        } else {
            hasErrors.set(false);
        }
    }
    private void evalComboBoxField() {
        ComboBoxBase comboField = (ComboBoxBase) srcControl.get();
        Object value = comboField.getValue();
        hasErrors.set(value == null || value.toString().isEmpty());
    }

 */
}
