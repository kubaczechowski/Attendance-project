package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gui.controller.LogInController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/view/TeacherView.fxml"));
        Parent root=null; //local variables arent automatically initialized
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Teacher");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //add css file
        scene.getStylesheets().clear();
        //scene.getStylesheets().add("/sample/gui/css/logIn.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
