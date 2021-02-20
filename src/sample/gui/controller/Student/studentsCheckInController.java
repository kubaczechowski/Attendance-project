package sample.gui.controller.Student;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class studentsCheckInController  implements Initializable {
    
    @FXML
    Label lblDate;
    @FXML
    PieChart pieChartThisMonths;
    @FXML
    PieChart pieChartThisSemester;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clock();
        PieCharts();
    }




    private void clock(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE\ndd.MM.yyyy\n  HH:mm");
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
        pieChartThisMonths.setTitle("Attendace in this Months");

        pieChartThisSemester.setData(pieChartSemester);
        pieChartThisSemester.setLabelsVisible(false);
        pieChartThisSemester.setTitle("Attendace in this Semester");
    }



}
