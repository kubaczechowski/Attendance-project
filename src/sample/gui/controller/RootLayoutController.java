package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane rootLayout;

    private void addLeftCol(){
        Label label = new Label("KLK");
        label.setFont(new Font("Arial", 45));

        Label dashboard = new Label("KLK");
        dashboard.setFont(new Font("Arial", 25));

        Label students = new Label("Students");
       students.setFont(new Font("Arial", 25));

        Label courses = new Label("Courses");
        courses.setFont(new Font("Arial", 25));

        Label achievements = new Label("Achievements");
        achievements.setFont(new Font("Arial", 25));

        Label calendar = new Label("Calendar");
       calendar.setFont(new Font("Arial", 25));

        Label attendanceStats = new Label("AttendanceStats");
        attendanceStats.setFont(new Font("Arial", 25));

        Label edit = new Label("Edit");
        edit.setFont(new Font("Arial", 25));

        Label notifications = new Label("Notifications");
        notifications.setFont(new Font("Arial", 25));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, dashboard, students, courses,
                achievements, calendar, attendanceStats, edit, notifications);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);

        rootLayout.getChildren().add(vBox);
    }

    public void goToDashboard(){
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("gui/view/dashboardLayout.fxml"));
         //local variables arent automatically initialized
        try {
            Parent root = loader.load();
            AnchorPane dashboard = (AnchorPane) loader.load();
            rootLayout.setCenter(dashboard);

            //pass primary stage
            DashboardTeacherController dashboardTeacherController = new DashboardTeacherController();
            Stage primaryStage = (Stage) rootLayout.getScene().getWindow();
            dashboardTeacherController.setPrimaryStage(primaryStage);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLeftCol();
    }
}
