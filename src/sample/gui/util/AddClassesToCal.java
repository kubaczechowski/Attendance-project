package sample.gui.util;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import sample.be.Course;
import sample.gui.model.CoursesModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Method is used for adding classes taught by the
 * logged in teacher
 * @author kuba
 */
public class AddClassesToCal {
    private CoursesModel coursesModel = CoursesModel.createOrGetInstance();
    List<Course> courses = new ArrayList<>();

    public CalendarSource getClasses() {
        CalendarSource calendarSource = new CalendarSource("source");
        Calendar calendar = new Calendar("Test");
        courses = coursesModel.getCoursesLoggedTeacher();

        for(Course course: courses){
            Entry<String> entry =  new Entry<>(course.getCourseName() +
                    "                     Attendance 79%");
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDateTime day = dateTime.with(TemporalAdjusters.
                    nextOrSame(getDayOfWeek(course)));
            entry.changeStartDate(LocalDate.from(day));
            entry.changeEndDate(LocalDate.from(day));
            String[] startTime = course.getStartTime().split(":");
            entry.changeStartTime(LocalTime.of(Integer.parseInt(startTime[0].trim()),
                    Integer.parseInt(startTime[1].trim())));
            String[] endTime = course.getEndTime().split(":");
            entry.changeEndTime(LocalTime.of(Integer.parseInt(endTime[0].trim()),
                    Integer.parseInt(endTime[1].trim())));
            entry.setLocation("Schoool");

            calendar.addEntry(entry);
        }
        calendarSource.getCalendars().addAll(calendar);
        return calendarSource;
    }
    
    private DayOfWeek getDayOfWeek(Course course){
        switch(course.getDayOfWeek().trim()){
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

}
