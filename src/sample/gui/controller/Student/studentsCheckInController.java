package sample.gui.controller.Student;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.be.Student;
import sample.gui.model.StudentModel;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class studentsCheckInController  implements Initializable {


    @FXML
    private JFXButton logOut;
    @FXML
    private Label lblDate;
    @FXML
    private PieChart pieChartThisMonths;
    @FXML
    private PieChart pieChartThisSemester;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn tableColumnDays;
    @FXML
    private TableColumn tableColumnEdit;








    private StudentModel studentModel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentModel = new StudentModel();
        clock();
        PieCharts();
        Absences();
        LogOut();
    }

    private void LogOut() {
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage s = (Stage) logOut.getScene().getWindow();
                s.close();
            }
        });
    }


    private void clock(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("  EEEE\ndd.MM.yyyy\n    HH:mm");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void PieCharts(){
        ObservableList<PieChart.Data> pieChartMonths = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieChartSemester = FXCollections.observableArrayList();
        pieChartMonths.add(new PieChart.Data("Present", 100));
        pieChartMonths.add(new PieChart.Data("Absent", 20));
        pieChartSemester.add(new PieChart.Data("Present", 2200));
        pieChartSemester.add(new PieChart.Data("Absent", 59));

        pieChartThisMonths.setData(pieChartMonths);
        pieChartThisMonths.setLabelsVisible(false);
        pieChartThisMonths.setTitle("Attendance in this Months");

        pieChartThisSemester.setData(pieChartSemester);
        pieChartThisSemester.setLabelsVisible(false);
        pieChartThisSemester.setTitle("Attendance in this Semester");
    }


    public void Absences(){
        tableColumnDays.setCellValueFactory(new PropertyValueFactory<Student, String>("absenceDays"));
        tableColumnEdit.setCellValueFactory(new PropertyValueFactory<Student, Void>(""));


        ObservableList<Student> days = FXCollections.observableArrayList(studentModel.getAbsentDays());

        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellButtons = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<Student, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setStyle("-fx-background-color: #d2eff9");
                        btn.setOnAction((ActionEvent event) -> {
                            Student edit = getTableView().getItems().get(getIndex());
                            btn.setDisable(true);
                            btn.setText("Waiting");



                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        tableView.setItems(days);

        tableColumnEdit.setCellFactory(cellButtons);

    }



}
