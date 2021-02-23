package sample.gui.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.gui.controller.Teacher.DashboardTeacherController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller responsible for managing all  views available for the user
 * it handles the menu view: column
 * @author kuba
 */
public class RootLayoutController implements Initializable {

    public BorderPane borderPane;
    @FXML
    protected Stage primaryStage;

    //labels
   protected Label label;
    protected Label dashboard;
    protected Label students;
    protected Label courses;
    protected Label calendar;

    /**
     * method provide labels visible in the menu
     * other windows are available for student and other for teacher
     */
    protected void addLeftCol(){
       //implementation provided in the children classes
    }

    public void goToTeacherDashboard(){
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/sample/gui/view/Teacher/dashboardTeacher.fxml"));
        try {
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

    public void goToStudentDashboard(){
        //Laslos Implementation
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

    // I see here huge code smell
    protected void addLabelsOnAction() {
        dashboard.setOnMousePressed(mouseEvent -> {
            openWindow(View.DASHBOARD);
        });

        students.setOnMousePressed(mouseEvent -> {
            openWindow(View.STUDENTS);
        });
        courses.setOnMousePressed(mouseEvent -> {
            openWindow(View.COURSES);
        });
        calendar.setOnMousePressed(mouseEvent -> {
            openWindow(View.CALENDAR);
        });
    }

    protected void openWindow(View viewType) {
        String windowName =null;
        if(viewType == View.STUDENTS || viewType== View.CALENDAR){
            if(viewType== View.STUDENTS)
                windowName ="StudentsView";
            else
                windowName = "calendarView";

            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/"+ windowName +".fxml"));
            try{
                BorderPane view = (BorderPane) loader.load();
                //trying to do fade in and out animation
                FadeTransition ft = new FadeTransition(Duration.millis(1000), view);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                this.borderPane.setCenter(view);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(viewType == View.COURSES){
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Student/"+ "coursesView" +".fxml"));
            try{
                ScrollPane view = (ScrollPane) loader.load();
                this.borderPane.setCenter(view);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLeftColLabels();
        addLeftCol();
        addLabelsOnAction();
    }

   protected void addLeftColLabels() {
       label = new Label("KLK");
       label.setId("KLK");
       dashboard = new Label("Dashboard");
       dashboard.getStyleClass().add("label-navigation");
       students = new Label("Students");
       students.getStyleClass().add("label-navigation");
       courses = new Label("Courses");
       courses.getStyleClass().add("label-navigation");
       calendar = new Label("Calendar");
       calendar.getStyleClass().add("label-navigation");
    }

    public enum View{
        DASHBOARD,
        STUDENTS,
        COURSES,
        CALENDAR,
    }

}
