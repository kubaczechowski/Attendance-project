package sample.gui.controller.Course;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CourseItemController {
    @FXML
    private ImageView img;
    @FXML
    private Label name;
    @FXML
    private Label city;
    @FXML
    private Label dayOfweek2;
    @FXML
    private Label endHour;
    @FXML
    private Label dayOfWeek;
    @FXML
    private Label startHour;
    @FXML
    private PieChart chart;
    @FXML
    private Label startD;
    @FXML
    private Label startM;
    @FXML
    private Label startY;
    @FXML
    private Label endD;
    @FXML
    private Label endM;
    @FXML
    private Label endYear;

    public CourseItemController(ImageView img) {
        this.img = img;
    }

    public CourseItemController(ImageView img, Label name, Label city,
                                Label dayOfweek2, Label endHour, Label dayOfWeek,
                                Label startHour, PieChart chart, Label startD, Label startM,
                                Label startY, Label endD, Label endM, Label endYear) {
        this.img = img;
        this.name = name;
        this.city = city;
        this.dayOfweek2 = dayOfweek2;
        this.endHour = endHour;
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.chart = chart;
        this.startD = startD;
        this.startM = startM;
        this.startY = startY;
        this.endD = endD;
        this.endM = endM;
        this.endYear = endYear;
    }


}
