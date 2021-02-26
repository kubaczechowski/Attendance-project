package sample.gui.controller.Teacher;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import sample.gui.util.AddClassesToCal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author kuba
 */
public class CalendarViewController implements Initializable {

    private CalendarView calendarView = new CalendarView();
    private AddClassesToCal addClassesToCal = new AddClassesToCal();
    Calendar classes = new Calendar("Classes");
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCalendar();
        updateTime();
        runCal();
        setClickListener();
    }

    /**
     * method handles event of clicking on the entry
     * then new window is opened
     */
    private void setClickListener() {
        calendarView.setEntryDetailsCallback(param -> {
            EditAttendance.OpenEditAttendance.openEditAttendance(param.getEntry());
            return true;
        });
    }


    private void loadCalendar() {
        classes.setStyle(Calendar.Style.STYLE5);
        calendarView.getCalendarSources().
                addAll(addClassesToCal.getClasses());
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
        borderPane.setCenter(calendarView);
    }


}
