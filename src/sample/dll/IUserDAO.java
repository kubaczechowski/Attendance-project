package sample.dll;

import sample.gui.controller.LogInController;

public interface IUserDAO {
    boolean checkIfExistsInSystem(String email, String password,
                                  LogInController.LoggingState state);

    boolean emailExists(String email, LogInController.LoggingState user);
}
