package sample.gui.controller.Student;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sample.gui.controller.RootLayoutController;

public class RootLayoutStudentController extends RootLayoutController {
    @Override
    protected void addLeftCol() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, dashboard, courses);
        vBox.setPadding(new Insets(10, 20, 0, 20));
        vBox.setSpacing(20);
        vBox.getStyleClass().add("vbox");
        borderPane.setLeft(vBox);
    }
}
