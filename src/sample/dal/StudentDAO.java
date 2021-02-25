package sample.dal;

import sample.be.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Cicily Scarse",3,25, "monday"));
        students.add(new Student("Gus Audibert",10,4, "-"));
        students.add(new Student("Rowan Donohue",3,13, "monday"));
        students.add(new Student("Niven Canadine",4,12, "-"));
        students.add(new Student("Electra Boomes",17,11, "-"));
        students.add(new Student("Florenza Mardell",12,11, "-"));
        students.add(new Student("Verge Swanbourne",8,8, "wednesday"));
        students.add(new Student("Byrle Corgenvin",13,2, "friday"));
        students.add(new Student("Jeff Gude",8,7, "monday"));
        students.add(new Student("Scarlet De Vaar",1,4, "-"));
        students.add(new Student("Cati Templar",16,10, "-"));
        students.add(new Student("Tiebout McGurk",4,2, "-"));
        students.add(new Student("Shelby Robbie",7,10, "tuesday"));

        return students;
    }

    public List<String> getAbsentToday() {
        List<String> students = new ArrayList<>();
        students.add("Jeff Gude");
        students.add("Shelby Robbie");
        students.add("Niven Canadine");

        return students;
    }

    public List<Student> getAbsentDays(){
        List<Student> days = new ArrayList<>();
       days.add(new Student("01.11.2020"));
       days.add(new Student("22.12.2020"));
       days.add(new Student("05.01.2021"));


       return days;
    }
}
