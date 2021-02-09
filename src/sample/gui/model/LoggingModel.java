package sample.gui.model;

import sample.bll.BllFacade;
import sample.bll.BllManager;
import sample.gui.controller.LogInController;

public class LoggingModel {
    private BllFacade logicLayer = new BllManager();

    public boolean validEmail(String insertedEmail) {
        return logicLayer.validEmail(insertedEmail);
    }

    public boolean checkIfExist(LogInController.LoggingState userType, String email,
                                String password) {
        return logicLayer.checkIfExists(userType, email, password);
    }
}
