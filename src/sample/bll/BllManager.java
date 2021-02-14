package sample.bll;

import com.calendarfx.model.Entry;
import sample.be.Course;
import sample.be.Student;
import sample.dll.CourseDAO;
import sample.dll.UserDAO;
import sample.gui.controller.LogInController;

import java.util.List;

public class BllManager implements BllFacade{
    Validations validations = new Validations();
    UserDAO userDAO = new UserDAO();
    CourseDAO courseDAO = new CourseDAO();


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

    @Override
    public List<Student> getAllStudents() {
        return userDAO.getAllStudents();
    }

    @Override
    public List<Course> getCourses(String teachersFName, String teachersSName) {
        return courseDAO.getCoursesForATeacher(teachersFName, teachersSName);
    }
}
