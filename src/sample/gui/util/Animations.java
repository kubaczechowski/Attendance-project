package sample.gui.util;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * class contains all methods responsible for
 * providing more interactive interface and therefore better UX
 * @author kuba
 */
public class Animations {
    private static boolean xyState= true;

    /**
     * animation moves node only horizontally
     * it can be used for example if the access was denied
     * and in one of the fields there was provided wrong information
     * @param node
     */
    public static void shakeNodeAnimation(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.playFromStart();
    }

    /**
     * shaking window animation
     * @param borderPane
     */
    public static void shakeStageAnimation(BorderPane borderPane) {
        Stage currentStage = (Stage) borderPane.getScene().getWindow();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),
                new EventHandler<ActionEvent>() {
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

    /**
     * whenever the node is hovered
     * it becomes more bright
     * @param node
     */
    public static void hoverNodeAnimation(Node node){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100) , node);
        fadeTransition.setFromValue(0.75);
        fadeTransition.setToValue(1);
        node.setOnMouseEntered(mouseEvent -> {
            //fade in??
            fadeTransition.setRate(1.0);
            fadeTransition.play();
        });
        node.setOnMouseExited(mouseEvent -> {
            fadeTransition.setRate(-2.0);
            fadeTransition.play();
        });
    }
}
