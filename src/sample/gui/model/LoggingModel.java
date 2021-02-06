package sample.gui.model;

import sample.bll.BllFacade;
import sample.bll.BllManager;

public class LoggingModel {
    private BllFacade logicLayer = new BllManager();

    public boolean validEmail(String insertedEmail) {
        return logicLayer.validEmail(insertedEmail);
    }
}
