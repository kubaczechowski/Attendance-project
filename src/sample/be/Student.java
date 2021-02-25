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


    private String name;

    public double getP_month() {
        return p_month;
    }

    public void setP_month(double p_month) {
        this.p_month = p_month;
    }

    public double getP_semester() {
        return p_semester;
    }

    public void setP_semester(double p_semester) {
        this.p_semester = p_semester;
    }

    private double p_month;
    private double p_semester;
    private String day; //which day they are usually absent on



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

    public Student(String name, double p_month, double p_semester, String day) {
        super(name);
        this.name = name;
        this.p_month = p_month;
        this.p_semester = p_semester;
        this.day = day;
    }
    private String absenceDays;
    public Student(String absenceDays) {
        super(absenceDays);
        this.absenceDays = absenceDays;


    }

    public String getAbsenceDays() {
        return absenceDays;
    }

    public void setAbsenceDays(String absenceDays) {
        this.absenceDays = absenceDays;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getP_Month() {
        return p_month;
    }

    public void setP_Month(double month) {
        this.p_month = month;
    }

    public double getP_Semester() {
        return p_semester;
    }

    public void setP_Semester(double semester) {
        this.p_semester = semester;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
