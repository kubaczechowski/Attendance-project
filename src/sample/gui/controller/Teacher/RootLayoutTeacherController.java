package sample.gui.controller.Teacher;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.gui.controller.LogInController;
import sample.gui.controller.RootLayoutController;
import sample.gui.util.Animations;

import java.io.IOException;

/**
 * class is a controller of the root layout used by the teachers
 * @author kuba
 */
public class RootLayoutTeacherController extends RootLayoutController {

    @Override
    protected void addLeftCol() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, dashboard, students, calendar);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);
        vBox.getStyleClass().add("vbox");
        borderPane.setLeft(vBox);
    }

    @Override
    public void initRootLayout() {
        try{
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/rootLayoutTeacher.fxml"));
            borderPane = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(borderPane);
            scene.getStylesheets().add(getClass().
                    getResource("/sample/gui/css/rootLayout.css").toExternalForm());
            scene.getStylesheets().add(getClass().
                    getResource("/sample/gui/css/studentsView.css").toExternalForm());
            primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void openWindow(View viewType) {
        super.openWindow(viewType);

        if(viewType == View.DASHBOARD){
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/sample/gui/view/Teacher/"+ "TeacherView" +".fxml"));
            try{
                BorderPane view = (BorderPane) loader.load();
                Animations.fadeInTransition(view, 900);
                this.borderPane.setCenter(view);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
