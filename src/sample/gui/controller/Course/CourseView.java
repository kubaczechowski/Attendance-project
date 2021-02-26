package sample.gui.controller.Course;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
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
 * Class developed in JavaFX for the sake of curiosity
 * Later may be replaced with fxml file.
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
        contrainer.setId("courseItem");
        DropShadow ds = new DropShadow(15, Color.DARKGREEN);
        contrainer.setEffect(ds);
        contrainer.setMinWidth(500);
        contrainer.setMaxWidth(650);
        contrainer.setId("SingleCourse");
        contrainer.setPadding(new Insets(30));
        contrainer.getChildren().addAll(getleftSide(), getMiddle(),
               getRightSide(67, 45));
        contrainer.setBackground(new Background(new BackgroundFill(Color.rgb(250,
               250, 250), CornerRadii.EMPTY, Insets.EMPTY)));
        return contrainer;
    }


    private VBox getleftSide(){
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
        container.getChildren().addAll(getTopCorner(), region, teacher, teachersName,  location1, loc);
        return container;
    }

    private HBox getTopCorner(){
        HBox topCorner = new HBox();
        topCorner.setSpacing(15);
        ImageView img = new ImageView("/sample/gui/images/video-learning.png");
        img.setFitWidth(100 );
        img.setFitHeight(100);
        Label courseName = new Label(nameOfCourse);
        courseName.setId("courseName");
        topCorner.getChildren().addAll(img, courseName);
        return topCorner;
    }

    private VBox getMiddle(){
        VBox container = new VBox();
        container.setAlignment(Pos.BOTTOM_CENTER);
        Label time = new Label("Start time");
        time.setId("startT");
        Label end = new Label("End time");
        end.setId("endT");
        container.prefWidth(500);
        container.getChildren().addAll(time, getStartTimeContainer(), end,
                getEndTimeContainer());
        return container;
    }

    private HBox getEndTimeContainer() {
        Label endT = new Label(endTime);
        Label dayOfWeek2 = new Label(this.dayOfWeek);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(dayOfWeek2, endT);
        return hBox2;
    }


    private HBox getStartTimeContainer() {
        Label dayOfWeek1 = new Label(dayOfWeek);
        Label startT = new Label(startTime);
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(dayOfWeek1, startT);
        return hBox1;
    }



    private VBox getRightSide(int absDays, int presDays ){
        VBox vBox = new VBox();
        vBox.prefHeight(500);
        Region  region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);
        PieChart pieChart = Charts.getChart(absDays, presDays, false, 150, 150, Side.TOP,
                new Insets(15, 0, 0, 0));
        vBox.getChildren().addAll(getRightCorner(),pieChart);
        return vBox;
    }

    /**
     * method needs to be refactored.
     * Huge code smell. unreadable
     * @return
     */
    private HBox getRightCorner(){
        VBox left = new VBox();
        left.getChildren().addAll(getStartDateLabel(), getBottom());
        Label ed = new Label("End Date");
        ed.setId("endDateL");
        HBox bott2 = new HBox();
        Label endD = new Label(String.valueOf(endDay));
        endD.setId("endD");
        VBox right2 = new VBox();
        right2.setSpacing(0);
        Label endM = new Label(endMonth);
        endM.setId("endM");
        Label endY = new Label(String.valueOf(endYear));
        endY.setId("endY");
        right2.getChildren().addAll(endM, endY);
        bott2.getChildren().addAll(endD, right2);
        //all right
        VBox right0 = new VBox();
        right0.getChildren().addAll(ed, bott2);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        HBox masterContainer = new HBox();
        masterContainer.getChildren().addAll(left, getSeparator(), right0);
        return masterContainer;
    }

    private Label getStartDateLabel(){
        Label sd = new Label("Start Date");
        sd.setId("startDateL");
        return sd;
    }

    private HBox getBottom(){
        Label startD = new Label(String.valueOf(startDay));
        VBox startDvbox = new VBox();
        startDvbox.getChildren().add(startD);
        startDvbox.setAlignment(Pos.TOP_CENTER);
        startD.setId("startD");
        VBox right = new VBox();
        Label startM = new Label(startMonth);
        startM.setId("startM");
        Label startY = new Label(String.valueOf(startYear));
        startY.setId("startY");
        right.getChildren().addAll(startM, startY);
        right.setAlignment(Pos.TOP_LEFT);
        HBox bott = new HBox();
        bott.getChildren().addAll(startDvbox, right);
        bott.setAlignment(Pos.TOP_LEFT);
        return bott;
    }

    private Separator getSeparator(){
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        return separator;
    }

}
