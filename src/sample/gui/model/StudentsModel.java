package sample.gui.model;

import sample.be.Student;
import sample.bll.BllFacade;
import sample.bll.BllManager;

import java.util.List;

public class StudentsModel {
    private BllFacade logicLayer = new BllManager();

    public List<Student> getAllStudents() {
        return logicLayer.getAllStudents();
    }
}
