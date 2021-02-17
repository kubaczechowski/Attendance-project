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

import java.io.IOException;
/**
 * @author kuba
 */
public class Main extends Application {

    private double xOffset;
    private double yOffset;

    /**
     * At first user has to log into the system
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().
                getResource("/sample/gui/view/logIn.fxml"));
       // Parent root = (Parent) fxmlLoader.load();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/gui/view/logIn.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().
                getResource("/sample/gui/css/logIn.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>()  {
            @Override
            public void handle(MouseEvent mouseEvent) {
                    xOffset = mouseEvent.getSceneX();
                    yOffset = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                    root.getScene().getWindow().setX(me.getScreenX() - xOffset);
                    root.getScene().getWindow().setY(me.getScreenY() - yOffset);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
