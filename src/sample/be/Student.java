package sample.be;

public class Student {
    private String name;
    private double month;
    private double semester;
    //which day they are usually absent on
    private String day;

    public Student(String name, double month, double semester, String day) {
        this.name = name;
        this.month = month;
        this.semester = semester;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonth() {
        return month;
    }

    public void setMonth(double month) {
        this.month = month;
    }

    public double getSemester() {
        return semester;
    }

    public void setSemester(double semester) {
        this.semester = semester;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
