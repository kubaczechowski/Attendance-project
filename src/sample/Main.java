package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gui.controller.LogInController;

import java.io.IOException;

public class Main extends Application {

    /**
     * At first user has to log into the system
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
      /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/gui/view/logIn.fxml"));
        Parent root=null; //local variables arent automatically initialized
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //add css file
        scene.getStylesheets().clear();
      //  scene.getStylesheets().add("/sample/gui/css/logIn.css");
        primaryStage.show();

       */
        Parent root = FXMLLoader.load(getClass().getResource("/sample/gui/view/logIn.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
