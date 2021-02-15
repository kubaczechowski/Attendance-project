package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.be.Student;
import sample.gui.model.CoursesModel;
import sample.gui.util.AddClassesToCal;

import java.io.IOException;
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
       // addEntries();
        updateTime();
        runCal();
    }

    private void addEntries() {

    }

    private void loadCalendar() {
        classes.setStyle(Calendar.Style.STYLE7);
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
        anchorPane.getChildren().add(calendarView);
    }

    static class openEditAttendance{
        public void openEditAttendance(Entry lecture ){
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/editAttendance.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditAttendance editAttendance = loader.getController();
            editAttendance.setEntry(lecture);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            //think about moving window functionality
        }


    }

}
