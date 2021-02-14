package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import sample.gui.model.CoursesModel;
import sample.gui.util.AddClassesToCal;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CalendarViewController implements Initializable {

    private CalendarView calendarView = new CalendarView();
   // private CoursesModel coursesModel = new CoursesModel();
    private AddClassesToCal addClassesToCal = new AddClassesToCal();
    Calendar classes = new Calendar("Classes");
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCalendar();
        addEntries();
        updateTime();
        runCal();
    }

    private void addEntries() {
        //add entries
        /*
        Entry<String> entry = new Entry<>("SCO1");
        Interval interval = new Interval();
        entry.setInterval(interval);
        entry.setRecurrenceRule("RRULE:FREQ=WEEKLY;INTERVAL=3");
        entry.changeStartDate(LocalDate.now());
        entry.changeEndDate(LocalDate.now());
        entry.changeStartTime(LocalTime.of(12,30));
        entry.changeEndTime(LocalTime.of(13,30));
        classes.addEntry(entry);

         */
        classes.addEntries(addClassesToCal.getClasses());

    }

    private void loadCalendar() {
       // Calendar meetings = new Calendar("Meetings");
        classes.setStyle(Calendar.Style.STYLE7);
       /// meetings.setStyle(Calendar.Style.STYLE2);
        CalendarSource myCalendarSource = new CalendarSource("Timetable");
        myCalendarSource.getCalendars().add(classes);
        calendarView.getCalendarSources().addAll(myCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());
    }

    private void updateTime(){

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });
                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    private void runCal() {
        calendarView.showWeekPage();
        anchorPane.getChildren().add(calendarView);
    }


}
