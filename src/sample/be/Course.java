package sample.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author kuba
 */
public class Course {
    private StringProperty teachersFName;
    private StringProperty teachersSName;
    private StringProperty courseName;
    private StringProperty startTime;
    private StringProperty endTime;
    private StringProperty dayOfWeek;

    public Course(String teachersFName, String teachersSName,
                  String courseName, String startTime,
                  String endTime, String dayOfWeek) {
        this.teachersFName = new SimpleStringProperty(teachersFName);
        this.teachersSName = new SimpleStringProperty(teachersSName);
        this.courseName = new SimpleStringProperty(courseName);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.dayOfWeek = new SimpleStringProperty(dayOfWeek);
    }

    public String getTeachersFName() {
        return teachersFName.get();
    }

    public StringProperty teachersFNameProperty() {
        return teachersFName;
    }

    public void setTeachersFName(String teachersFName) {
        this.teachersFName.set(teachersFName);
    }

    public String getTeachersSName() {
        return teachersSName.get();
    }

    public StringProperty teachersSNameProperty() {
        return teachersSName;
    }

    public void setTeachersSName(String teachersSName) {
        this.teachersSName.set(teachersSName);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public String getDayOfWeek() {
        return dayOfWeek.get();
    }

    public StringProperty dayOfWeekProperty() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek.set(dayOfWeek);
    }
}
