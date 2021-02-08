package sample.gui.controller;

public interface ILogIn {
    void logIn();
    void saveUserInPreferences();
    void unsaveUserInPreferences();
    boolean checkIfStudentExists();
    boolean checkIfTeacherExists();
}
