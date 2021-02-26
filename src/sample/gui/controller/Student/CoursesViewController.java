package sample.gui.controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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

        scrollP.fitToWidthProperty().set(true);
        scrollP.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

       CourseView courseView = new CourseView();
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(30, 30, 30, 30));
        tilePane.setHgap(50);
        tilePane.setVgap(30);
        tilePane.setAlignment(Pos.BASELINE_CENTER);
        for(int i=0; i<15; i++){
           HBox courseItem = courseView.getCourseView();
           tilePane.getChildren().add(courseItem);
        }
        scrollP.setContent(tilePane);

    }

}
