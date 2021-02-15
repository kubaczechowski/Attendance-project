package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditAttendance implements Initializable {
    private Entry entry;
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();
        setListView();
    }

    private void setListView() {
    }

    private void setLabels() {
        Label course = new Label(entry.getTitle());
        Label date = new Label(entry.getStartDate().toString());
        Label attendanceOnTheCourse = new Label("79%");
        Label attendanceOnThatDay = new Label("56%");
        
    }

    public void setEntry(Entry lecture) {
        this.entry =lecture;
    }
}
