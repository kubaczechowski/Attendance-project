package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.gui.controller.LogInController;
import sample.gui.util.Animations;

import java.io.IOException;

/**
 * @author kuba
 */
public class Main extends Application {
    
    /**
     * At first user has to log to the system
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/gui/view/logIn.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().
                getResource("/sample/gui/css/logIn.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //moving undecorated window functionality
        Animations.setRoot(root);
        root.setOnMousePressed(Animations.mousePressed);
        root.setOnMouseDragged(Animations.mouseDragged);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
