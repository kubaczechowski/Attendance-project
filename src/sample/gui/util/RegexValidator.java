package sample.gui.util;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
/**
 * @author kuba
 */
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

}
