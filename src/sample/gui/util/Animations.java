package sample.gui.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animations {
    private static boolean xyState= true;

    public static void shakeNodeAnimation(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
    }

    public static void shakeStageAnimation(BorderPane borderPane) {
        Stage currentStage = (Stage) borderPane.getScene().getWindow();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (xyState) {
                    currentStage.setX(currentStage.getX() + 10);
                    currentStage.setY(currentStage.getY() + 10);
                } else {
                    currentStage.setX(currentStage.getX() - 10);
                    currentStage.setY(currentStage.getY() - 10);
                }
                xyState= !xyState;
            }
        }));
        timeline.setAutoReverse(true);
        timeline.cycleCountProperty().setValue(3);
        timeline.play();
    }
}
