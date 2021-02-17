package sample.bll;

import sample.be.Course;
import sample.be.Student;
import sample.gui.controller.LogInController;

import java.util.List;
/**
 * @author kuba
 */
public interface BllFacade {
    boolean validEmail(String insertedEmail);

    boolean checkIfExists(LogInController.LoggingState userType, String email, String password);

    boolean emailExists(String email, LogInController.LoggingState user);

    List<Student> getAllStudents();

    List<Course> getCourses(String teachersFName, String teachersSName);

    List<Student> searchStudents(String t1);
}
