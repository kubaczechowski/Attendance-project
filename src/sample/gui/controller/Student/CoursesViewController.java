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
        CourseView courseView = new CourseView();
        //scrollP.setContent(courseView.getCourseView());

        //VBox vBox = new VBox();
       // vBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //vBox.setAlignment(Pos.TOP_CENTER);
        //vBox.setSpacing(40);
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(30, 30, 30, 30));
        tilePane.setHgap(50);
        tilePane.setVgap(30);
        tilePane.setAlignment(Pos.BASELINE_CENTER);

        //vBox.setPrefSize(VBox.USE_COMPUTED_SIZE, VBox.USE_COMPUTED_SIZE);
        for(int i=0; i<15; i++){
           HBox courseItem = courseView.getCourseView();
            //courseItem.setPrefSize(HBox.USE_COMPUTED_SIZE, HBox.USE_COMPUTED_SIZE);
            tilePane.getChildren().add(courseItem);
        }
        scrollP.setContent(tilePane);

    }

}
