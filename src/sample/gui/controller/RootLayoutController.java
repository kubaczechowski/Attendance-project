package sample.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public BorderPane borderPane;
    @FXML
    private Stage primaryStage;

    //labels
    private Label label;
    private Label dashboard;
    private Label students;
    private Label courses;
    private  Label achievements;
    private Label calendar;
    private  Label attendanceStats;
    private  Label edit;
    private  Label notifications;

    private void addLeftCol(){
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
        vBox.getChildren().addAll(label, dashboard, students, courses,
                achievements, calendar, attendanceStats, edit, notifications);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);
        vBox.getStyleClass().add("vbox");
        borderPane.setLeft(vBox);
    }

    public void goToTeacherDashboard(){
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/sample/gui/view/DashboardTeacher.fxml"));
         //local variables arent automatically initialized
        try {
           // Parent root = loader.load();
            AnchorPane dashboard = (AnchorPane) loader.load();
            borderPane.setCenter(dashboard);

            //pass primary stage
            DashboardTeacherController dashboardTeacherController = new DashboardTeacherController();
            Stage primaryStage = (Stage) borderPane.getScene().getWindow();
            dashboardTeacherController.setPrimaryStage(primaryStage);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout(){
        try{
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/sample/gui/view/rootLayoutPane.fxml"));
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
    private void addLabelsOnAction() {
        dashboard.setOnMousePressed(mouseEvent -> {
            openWindow(View.DASHBOARD);
        });

        students.setOnMousePressed(mouseEvent -> {
            openWindow(View.STUDENTS);
        });
        courses.setOnMousePressed(mouseEvent -> {
            openWindow(View.COURSES);
        });
        achievements.setOnMousePressed(mouseEvent -> {
            openWindow(View.ACHIVEMENTS);
        });
        calendar.setOnMousePressed(mouseEvent -> {
            openWindow(View.CALENDAR);
        });
        attendanceStats.setOnMousePressed(mouseEvent -> {
            openWindow(View.ATTENDANCESTATS);
        });
        edit.setOnMousePressed(mouseEvent -> {
            openWindow(View.EDIT);
        });
        notifications.setOnMousePressed(mouseEvent -> {
            openWindow(View.NOTIFICATIONS);
        });

    }

    private void openWindow(View viewType) {
        if(viewType == View.STUDENTS){
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/StudentsView.fxml"));
            try{
                BorderPane studentView = (BorderPane) loader.load();
               this.borderPane.setCenter(studentView);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(viewType== View.CALENDAR){
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/calendarView.fxml"));
            try{
                AnchorPane calendarView = (AnchorPane) loader.load();
                this.borderPane.setCenter(calendarView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLeftCol();
        addLabelsOnAction();
    }

    public enum View{
        DASHBOARD,
        STUDENTS,
        COURSES,
        ACHIVEMENTS,
        CALENDAR,
        ATTENDANCESTATS,
        EDIT,
        NOTIFICATIONS
    }

}
