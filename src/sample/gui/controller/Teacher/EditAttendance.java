package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.be.Student;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Functionality in the development stage.
 * Functionality will be used when the teacher clicks on the
 * Entry in the calendar View. They will be enabled to see more details
 * and take further steps (edit or see course attendace in the specific view)
 *
 * @author kuba
 */
public class EditAttendance implements Initializable {
    private Entry entry;
    @FXML
    private VBox vBox;
   private ListView<Student> listView = new ListView();
   private TableColumn<Student, String> nameAndSurname =new TableColumn<>();
   private TableColumn<Student, String> atendance = new TableColumn<>();
   private TableColumn<Student, String>     avgAttendance = new TableColumn<>();

  private JFXButton editButton = new JFXButton();
  private JFXButton courseAttButton = new JFXButton();
  private JFXButton close = new JFXButton();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();
        vBox.getChildren().addAll(setLabels(), listView, setButtons());
    }

    private HBox setButtons() {
        editButton.setText("Edit");
        courseAttButton.setText("Course Attendance");
        close.setText("Close");
        editButton.setId("editB");
        courseAttButton.setId("courseAttB");
        close.setId("closeB");

        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(editButton, courseAttButton, close);
        return hBox;
    }

    private void setListView() {
        // fname and scname / abs/prse/no data  / attendance in percents
        //check if there is a record for that that for that student
        //show attendance in percents (calulate it based on the days
        // when student was absent and wasn't)
        initNameColumn();
        initAttendanceColumn();
        initAvgAttendanceColumn();

    }

    private void initAvgAttendanceColumn() {
        avgAttendance.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String>
                                                        p) {
                return  new ReadOnlyObjectWrapper<String>(showAvgAtt(p.getValue().getAvgAttendance()));
            }

            private String showAvgAtt(int avgAttendance) {
                if(avgAttendance ==-1)
                    return "no data";
                else
                    return avgAttendance + "%";

            }
        });
    }

    private void initAttendanceColumn() {
        atendance.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String>
                                                        p) {
                return new ReadOnlyObjectWrapper<String>(showAttendance(p.getValue().absenceProperty()));
            }

            private String showAttendance(IntegerProperty absenceProperty) {
                switch(absenceProperty.getValue()){
                    case 2: return "Present";

                    case -2: return "absent";
                    //if 0 or whatever
                    default: return "no data";
                }
            }
        });
    }

    private void initNameColumn() {
        nameAndSurname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String>
                                                        p) {
                return  new ReadOnlyObjectWrapper<>(p.getValue().getFirstName() +" "+
                        p.getValue().getSecondName());
            }
        });
    }

    private VBox setLabels() {
        String s1 = entry.getTitle().toString();
        Label course = new Label(s1);
        String s2 = entry.getStartDate().toString();
        Label date = new Label(s2);
        Label attendanceOnTheCourse = new Label("79%");
        Label attendanceOnThatDay = new Label("56%");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(course, date, attendanceOnTheCourse, attendanceOnThatDay);
        return vBox;
        
    }

    public void setEntry(Entry lecture) {
        this.entry =lecture;
    }
}
