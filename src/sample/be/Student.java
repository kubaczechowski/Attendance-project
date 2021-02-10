package sample.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends User {

    private StringProperty studyProgram;
    private IntegerProperty semester;

    public Student(String firstName, String secondName, String filePathImg,
                   String studyProgram, int semester) {
        super(firstName, secondName, filePathImg);
        this.studyProgram = new SimpleStringProperty(this, "studyProgram", studyProgram);
        this.semester = new SimpleIntegerProperty(this, "semester", semester);
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
