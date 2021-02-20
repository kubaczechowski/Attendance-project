package sample.gui.controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.gui.controller.Course.CourseItemController;
import sample.gui.controller.Course.CourseView;
import sample.gui.util.Charts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author kuba
 */
public class CoursesViewController implements Initializable {
    @FXML
    private ScrollPane scrollP;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CourseView courseView = new CourseView();
        scrollP.setContent(courseView.getCourseView());
    }

}
