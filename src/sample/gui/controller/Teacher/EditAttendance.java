package sample.gui.controller.Teacher;

import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample.be.Student;
import sample.gui.model.StudentsModel;

import java.io.IOException;


/**
 * Functionality in the development stage.
 * Functionality will be used when the teacher clicks on the
 * Entry in the calendar View. They will be enabled to see more details
 * and take further steps (edit or see course attendace in the specific view)
 *
 * Class developed in JavaFX for the sake of curiosity
 *
 * @author kuba
 */
public class EditAttendance  {

    private StudentsModel studentsModel =new StudentsModel();

    private Entry entry;
    @FXML
    private VBox vBox;
   private TableView<Student> tableView = new TableView<Student>();
   private TableColumn<Student, String> nameAndSurname =new TableColumn<>();
   private TableColumn<Student, String> atendance = new TableColumn<>();
   private TableColumn<Student, String>    avgAttendance = new TableColumn<>();

    ObservableList<Student> studentObservableList =
            FXCollections.observableArrayList();

  private JFXButton editButton = new JFXButton();
  private JFXButton courseAttButton = new JFXButton();
  private JFXButton close = new JFXButton();


    public ObservableList<Student> getStudentObservableList() {
        return studentObservableList;
    }

    private void setStudentObservableList(){
        studentObservableList.addAll(studentsModel.getAllStudents());
    }

    /**
     * Buttons located on the bottom.
     * @return HBox
     */
    private HBox setButtons() {
        setTextOnButtons();
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(editButton, courseAttButton, close);
        return hBox;
    }

    private void setTextOnButtons(){
        editButton.setText("Edit");
        courseAttButton.setText("Course Attendance");
        close.setText("Close");
        editButton.setId("editB");
        courseAttButton.setId("courseAttB");
        close.setId("closeB");
    }

    //I guess we didn't set any list
    private void setListView() {
        tableView.getColumns().addAll(nameAndSurname, atendance, avgAttendance);
        setStudentObservableList();
        initNameColumn();
        initAttendanceColumn();
        initAvgAttendanceColumn();
        tableView.setItems(getStudentObservableList());
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
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> p) {
                return  new ReadOnlyObjectWrapper<>(p.getValue().getFirstName() +" "+
                        p.getValue().getSecondName());
            }
        });
    }

    private VBox setLabels() {
        String s1 = entry.getTitle();
        Label course = new Label(s1);
        String s2 = entry.getStartDate().toString();
        Label date = new Label(s2);
        Label attendanceOnTheCourse = new Label("79%");
        Label attendanceOnThatDay = new Label("56%");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(course, date, attendanceOnTheCourse, attendanceOnThatDay);
        return vBox;
    }

    /**
     * method initializes view just after the entry is set
     */
    private void initialize() {
        setListView();
        vBox.getChildren().addAll(setLabels(), tableView, setButtons());
        setButtonsOnAction();
    }

    private void setButtonsOnAction() {
        closeEventHandler();
    }

    private void closeEventHandler() {
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage s = (Stage) close.getScene().getWindow();
                s.close();
            }
        });
    }

    public void setEntry(Entry lecture) {
        this.entry =lecture;
        initialize();
    }

    public static class OpenEditAttendance{
        public static void openEditAttendance(Entry<?> lecture ){
            FXMLLoader loader = new FXMLLoader(OpenEditAttendance.class.
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
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }


    }

}
