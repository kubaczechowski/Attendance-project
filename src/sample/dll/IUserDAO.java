package sample.dll;

import sample.gui.controller.LogInController;

public interface IUserDAO {
    boolean checkIfExistsInSystem(String email, String password,
                                  LogInController.LoggingState state);
}
