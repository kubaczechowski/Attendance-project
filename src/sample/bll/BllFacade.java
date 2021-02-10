package sample.bll;

import sample.gui.controller.LogInController;

public interface BllFacade {
    boolean validEmail(String insertedEmail);

    boolean checkIfExists(LogInController.LoggingState userType, String email, String password);

    boolean emailExists(String email, LogInController.LoggingState user);
}
