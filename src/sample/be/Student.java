package sample.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author kuba
 */
public class Student extends User {

    private StringProperty studyProgram;
    private IntegerProperty semester;
    private IntegerProperty absence; // abs / pres/ no data
    private IntegerProperty avgAttendance;

    public int getAbsence() {
        return absence.get();
    }

    public IntegerProperty absenceProperty() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence.set(absence);
    }

    public int getAvgAttendance() {
        return avgAttendance.get();
    }

    public IntegerProperty avgAttendanceProperty() {
        return avgAttendance;
    }

    public void setAvgAttendance(int avgAttendance) {
        this.avgAttendance.set(avgAttendance);
    }

    public Student(String firstName, String secondName, String filePathImg,
                   String studyProgram, int semester) {
        super(firstName, secondName, filePathImg);
        this.studyProgram = new SimpleStringProperty(this, "studyProgram", studyProgram);
        this.semester = new SimpleIntegerProperty(this, "semester", semester);
    }

    public Student(String firstName, String secondName, String filePathImg,
                   String studyProgram, int semester, int absence, int avgAttendance) {
        super(firstName, secondName, filePathImg);
        this.studyProgram = new SimpleStringProperty(this, "studyProgram", studyProgram);
        this.semester = new SimpleIntegerProperty(this, "semester", semester);
        this.absence = new SimpleIntegerProperty(this, "absence", absence);
        this.avgAttendance = new SimpleIntegerProperty(this, "avgAttendance",
                avgAttendance);
    }

    public String getStudyProgram() {
        return studyProgram.get();
    }

    public StringProperty studyProgramProperty() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram.set(studyProgram);
    }

    public int getSemester() {
        return semester.get();
    }

    public IntegerProperty semesterProperty() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester.set(semester);
    }
}
