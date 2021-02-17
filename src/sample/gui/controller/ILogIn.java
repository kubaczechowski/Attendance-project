package sample.gui.controller;
/**
 * @author kuba
 */
public interface ILogIn {

    void logIn(LogInController.LoggingState state);
    void saveUserInPreferences();
    void unsaveUserInPreferences();
    boolean checkIfStudentExists();
    boolean checkIfTeacherExists();
}
