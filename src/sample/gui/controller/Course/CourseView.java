package sample.gui.controller.Course;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.gui.util.Charts;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * class is used for creating single instance of course that will be
 * displayed in the courses overview
 *
 * further development needed
 * @author kuba
 */
public class CourseView {
//Here is just test date only for the sake of designing

    private String nameOfCourse = "Fashion";
    private String nameOfTeacher = "Marcelo Burlon";
    private String location = "Paris";
    private String dayOfWeek = "Mon";
    private String startTime = "12:45";
    private String endTime = " 15:45";

    private int startDay = 7;
    private int startYear = 2020;
    private String startMonth = "February";

    private int endDay = 30;
    private int endYear = 2020;
    private String endMonth = "May";


    public HBox getCourseView(){
        HBox contrainer = new HBox();
        contrainer.setId("SingleCourse");
        contrainer.setPadding(new Insets(30));
        contrainer.getChildren().addAll(getleftSide(), getMiddle(),
                getRightSide(67, 45));
       contrainer.setBackground(new Background(new BackgroundFill(Color.rgb(250,
               250, 250), CornerRadii.EMPTY, Insets.EMPTY)));
        return contrainer;
    }


    private VBox getleftSide(){
        HBox topCorner = new HBox();
        topCorner.setSpacing(15);
        ImageView img = new ImageView("/sample/gui/images/video-learning.png");
        img.setFitWidth(100 );
        img.setFitHeight(100);
        Label courseName = new Label(nameOfCourse);
        courseName.setId("courseName");
        topCorner.getChildren().addAll(img, courseName);

        Label teacher = new Label("Teacher: ");
        teacher.setId("teacherL");
        Label teachersName = new Label(nameOfTeacher);
        Label location1 = new Label("Location: ");
        location1.setId("locationL");
        Label loc  = new Label(location);

        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);

        VBox container = new VBox();
        container.prefWidth(500);
        container.getChildren().addAll(topCorner, region, teacher, teachersName,  location1, loc);
        return container;
    }

    private VBox getMiddle(){
        VBox container = new VBox();
        container.setAlignment(Pos.BOTTOM_CENTER);
        Label time = new Label("Start time");
        Label startT = new Label(startTime);
        time.setId("startT");
        Label end = new Label("End time");
        end.setId("endT");
        Label endT = new Label(endTime);
        Label dayOfWeek1 = new Label(dayOfWeek);
        Label dayOfWeek2 = new Label(this.dayOfWeek);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(dayOfWeek1, startT);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(dayOfWeek2, endT);
        container.prefWidth(500);
        container.getChildren().addAll(time, hBox1, end, hBox2);

        return container;
    }

    private VBox getRightSide(int absDays, int presDays ){
        VBox vBox = new VBox();
        vBox.prefHeight(500);
        Region  region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);

        vBox.getChildren().addAll(getRightCorner(),region,
                Charts.getChart(absDays, presDays, true, 90, 90, Side.TOP));
        return vBox;
    }

    private HBox getRightCorner(){
        HBox masterContainer = new HBox();
        //left
        VBox left = new VBox();
        Label sd = new Label("Start Date");
        sd.setId("startDateL");
        HBox bott = new HBox();
        Label startD = new Label(String.valueOf(startDay));
        startD.setId("startD");
        VBox right = new VBox();
        Label startM = new Label(startMonth);
        Label startY = new Label(String.valueOf(startYear));

        right.getChildren().addAll(startM, startY);
        bott.getChildren().addAll(startD, right);
        //all left
        left.getChildren().addAll(sd, bott);

        //right
        VBox right0 = new VBox();
        Label ed = new Label("End Date");
        ed.setId("endDateL");
        HBox bott2 = new HBox();
        Label endD = new Label(String.valueOf(endDay));
        endD.setId("endD");
        VBox right2 = new VBox();
        Label endM = new Label(endMonth);
        Label endY = new Label(String.valueOf(endYear));

        right2.getChildren().addAll(endM, endY);
        bott2.getChildren().addAll(endD, right2);
        //all right
        right0.getChildren().addAll(ed, bott2);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        //everything
        masterContainer.getChildren().addAll(left,separator, right0);
        return masterContainer;
    }

}
