package sample.gui.model;

import com.calendarfx.model.Entry;
import sample.be.Course;
import sample.bll.BllFacade;
import sample.bll.BllManager;

import java.util.List;

public class CoursesModel {
   private String teachersFName, teachersSName;
   private BllFacade logicL = new BllManager();
    private static CoursesModel coursesModel;
    public List<Course> getCoursesLoggedTeacher() {
       // return logicL.getCourses(teachersFName, teachersSName);
        //remember to change it later to the one above;
        return logicL.getCourses("piotr", "czechowski");
    }

    //singletonPattern
    public static CoursesModel createOrGetInstance() {
        if (coursesModel == null) {
            coursesModel = new CoursesModel();
        }
        return coursesModel;
    }

    public String getTeachersFName() {
        return teachersFName;
    }

    public void setTeachersFName(String teachersFName) {
        this.teachersFName = teachersFName;
    }

    public String getTeachersSName() {
        return teachersSName;
    }

    public void setTeachersSName(String teachersSName) {
        this.teachersSName = teachersSName;
    }
}
