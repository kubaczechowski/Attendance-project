package sample.gui.model;

import sample.be.Student;
import sample.bll.BllFacade;
import sample.bll.BllManager;

import java.util.ArrayList;
import java.util.List;

public class StudentsModel {
    private BllFacade logicLayer = new BllManager();
    private List<Student> studentList = new ArrayList<>();

    public StudentsModel() {
        this.studentList = logicLayer.getAllStudents();
    }

    public List<Student> getAllStudents() {
        return studentList;
    }

    public List<Student> search(String t1) {
        if (t1.isEmpty() || t1.isBlank() || t1==null)
            return logicLayer.getAllStudents();
        else
            return logicLayer.searchStudents(t1);
    }
}
