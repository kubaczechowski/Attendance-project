package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.be.Student;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditAttendance implements Initializable {
    private Entry entry;
    @FXML
    private VBox vBox;
   private ListView<Student> listView = new ListView();
   private TableColumn<Student, String> nameAndSurname =new TableColumn<>();
   private TableColumn<Student, String> atendance = new TableColumn<>();
   private TableColumn<Student, String>     avgAttendance = new TableColumn<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();
        setListView();
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
