package sample.gui.util;

import com.jfoenix.validation.base.ValidatorBase;

public class ShowMessage extends ValidatorBase {
    public ShowMessage(String message) {
        super(message);
    }

    @Override
    protected void eval() {
        hasErrors.set(true);
    }
}
