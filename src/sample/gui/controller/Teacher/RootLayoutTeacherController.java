package sample.gui.controller.Teacher;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.gui.controller.LogInController;
import sample.gui.controller.RootLayoutController;

import java.io.IOException;

public class RootLayoutTeacherController extends RootLayoutController {

    @Override
    protected void addLeftCol() {
       // super.addLeftCol();
        label = new Label("KLK");
        label.setId("KLK");

        dashboard = new Label("Dashboard");
        dashboard.getStyleClass().add("label-navigation");
        students = new Label("Students");
        students.getStyleClass().add("label-navigation");
        courses = new Label("Courses");
        courses.getStyleClass().add("label-navigation");
        achievements = new Label("Achievements");
        achievements.getStyleClass().add("label-navigation");
        calendar = new Label("Calendar");
        calendar.getStyleClass().add("label-navigation");
        attendanceStats = new Label("Attendance Stats");
        attendanceStats.getStyleClass().add("label-navigation");
        edit = new Label("Edit");
        edit.getStyleClass().add("label-navigation");
        notifications = new Label("Notifications");
        notifications.getStyleClass().add("label-navigation");
        VBox vBox = new VBox();
       // if(loggingState == LogInController.LoggingState.TEACHERLOGGED)
            vBox.getChildren().addAll(label, dashboard, students, calendar);
       // if(loggingState == LogInController.LoggingState.STUDENTLOGGED)
        //    vBox.getChildren().addAll(label, dashboard, courses);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);
        vBox.getStyleClass().add("vbox");
        borderPane.setLeft(vBox);
    }

    @Override
    public void initRootLayout() {
       // super.initRootLayout();
        try{
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/rootLayoutTeacher.fxml"));
            borderPane = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(borderPane);
            scene.getStylesheets().add(getClass().
                    getResource("/sample/gui/css/rootLayout.css").toExternalForm());
            primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
