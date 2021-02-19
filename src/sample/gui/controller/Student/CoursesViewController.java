package sample.gui.controller.Student;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * class is responsible for creating a single course view
 */
public class CoursesViewController implements Initializable {
    private String nameOfCourse;
    private String nameOfTeacher;
    private String location;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    private int startDay;
    private int startYear;
    private String startMonth;

    private int endDay;
    private int endYear;
    private String endMonth;

    private void createleftSide(){
        HBox topCorner = new HBox();
        ImageView img = new ImageView("/sample/gui/images/video-learning.png");
        img.setFitWidth(75);
        img.setFitHeight(75);
        Label courseName = new Label(nameOfCourse);
        topCorner.getChildren().addAll(img, courseName);

        Label teacher = new Label("Teacher: ");
        Label teachersName = new Label(nameOfTeacher);
        Label location1 = new Label("Location: ");
        Label loc  = new Label(location);

        VBox container = new VBox();
        container.getChildren().addAll(topCorner, teacher, teachersName, location1, loc);
    }

    private void createMiddle(){
        VBox container = new VBox();
        Label time = new Label("Start time");
        Label startT = new Label(startTime);
        Label end = new Label(" End time");
        Label endT = new Label(endTime);
        Label dayOfWeek1 = new Label(dayOfWeek);
        Label dayOfWeek2 = new Label(this.dayOfWeek);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(dayOfWeek1, startT);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(dayOfWeek2, endT);

        container.getChildren().addAll(time, hBox1, end, hBox2);
    }

    private void createRightSide(){

    }

    private void getRightCorner(){
        HBox masterContainer = new HBox();
        //left
        VBox left = new VBox();
        Label sd = new Label("Start Date");
        HBox bott = new HBox();
        Label startD = new Label(String.valueOf(startDay));
        VBox right = new VBox();
        Label startM = new Label(startMonth);
        Label startY = new Label(String.valueOf(startYear));

        //right
        VBox right0 = new VBox();
        Label ed = new Label("Start Date");
        HBox bott2 = new HBox();
        Label endD = new Label(String.valueOf(endDay));
        VBox right2 = new VBox();
        Label endM = new Label(endMonth);
        Label endY = new Label(String.valueOf(endYear));
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
