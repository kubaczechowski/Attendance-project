package sample.gui.model;

import sample.be.Student;
import sample.dal.StudentDAO;

import java.util.List;

public class StudentModel {

    private StudentDAO studentDAO;

    public StudentModel() {
        studentDAO = new StudentDAO();
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public List<String> getAbsentToday() {
        return studentDAO.getAbsentToday();
    }

    public List<Student> getAbsentDays(){
        return studentDAO.getAbsentDays();
    }
}
