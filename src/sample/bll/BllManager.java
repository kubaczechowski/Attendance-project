package sample.bll;

import sample.dll.UserDAO;
import sample.gui.controller.LogInController;

public class BllManager implements BllFacade{
    Validations validations = new Validations();
    UserDAO userDAO = new UserDAO();

    @Override
    public boolean validEmail(String insertedEmail) {
        String regexEmail = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return validations.validateInput(regexEmail, insertedEmail);
    }

    @Override
    public boolean checkIfExists(LogInController.LoggingState userType, String email, String password) {
        return userDAO.checkIfExistsInSystem(email, password, userType);
    }

    @Override
    public boolean emailExists(String email, LogInController.LoggingState user) {
        return userDAO.emailExists(email, user);
    }
}
