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
        /*
        label.setOnMousePressed(mouseEvent -> {
            label.setText("key pressed");
        });

         */
        //label.setFont(new Font("Arial", 45));

         dashboard = new Label("Dashboard");
        //dashboard.setFont(new Font("Arial", 25));

         students = new Label("Students");
      // students.setFont(new Font("Arial", 25));

         courses = new Label("Courses");
       // courses.setFont(new Font("Arial", 25));

         achievements = new Label("Achievements");
       // achievements.setFont(new Font("Arial", 25));

        calendar = new Label("Calendar");
      // calendar.setFont(new Font("Arial", 25));

        attendanceStats = new Label("Attendance Stats");
       // attendanceStats.setFont(new Font("Arial", 25));

        edit = new Label("Edit");
        //edit.setFont(new Font("Arial", 25));

        Label notifications = new Label("Notifications");
       // notifications.setFont(new Font("Arial", 25));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, dashboard, students, courses,
                achievements, calendar, attendanceStats, edit, notifications);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);
        vBox.getStyleClass().add("vbox");


                //borderPane.getChildren().add(vBox);
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
        achivements.setOnMousePressed(mouseEvent -> {
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

    private void openWindow(View notifications) {

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
