package sample.bll;

import sample.be.Student;
import sample.gui.controller.LogInController;

import java.util.List;

public interface BllFacade {
    boolean validEmail(String insertedEmail);

    boolean checkIfExists(LogInController.LoggingState userType, String email, String password);

    boolean emailExists(String email, LogInController.LoggingState user);

    List<Student> getAllStudents();
}
