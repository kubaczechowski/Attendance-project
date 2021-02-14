package sample.gui.util;

import com.calendarfx.model.Entry;
import sample.be.Course;
import sample.gui.model.CoursesModel;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class AddClassesToCal {
    private CoursesModel coursesModel = CoursesModel.createOrGetInstance();
    List<Course> courses = new ArrayList<>();

    public Entry<String> getClasses() {
        List<Entry<String>> entryList = new ArrayList<>();
        courses = coursesModel.getCoursesLoggedTeacher();
        
        for(Course course: courses){
            Entry<String> entry =  new Entry<>(course.getCourseName());
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDateTime day = dateTime.with(TemporalAdjusters.nextOrSame(getDayOfWeek(course)));
        }
        
    }
    
    private DayOfWeek getDayOfWeek(Course course){
        switch(course.getDayOfWeek()){
            case "MONDAY": return DayOfWeek.MONDAY;
            case "TUESDAY": return DayOfWeek.TUESDAY;
            case "WEDNESDAY": return DayOfWeek.WEDNESDAY;
            case "THURSDAY": return DayOfWeek.THURSDAY;
            case "FRIDAY": return DayOfWeek.FRIDAY;
            case "SATURDAY": return DayOfWeek.SATURDAY;
            case "SUNDAY": return DayOfWeek.SUNDAY;
        }
        return null;
    }


    protected enum DAYS{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
