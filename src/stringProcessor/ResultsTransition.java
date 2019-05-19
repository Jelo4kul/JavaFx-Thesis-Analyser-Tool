package stringProcessor;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Jelo
 */
public class ResultsTransition {

    FadeTransition fadeOutTransition, fadeInTransition;
    TranslateTransition translateOutTransition, translateInTransition;
    double loca;

    public ResultsTransition() {

    }

    public void parallelTransition(Node fadeOut, Node nodefadeIn) {
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                translateOutTransition,
                fadeOutTransition,
                translateInTransition,
                fadeInTransition);
        parallelTransition.setCycleCount(1);
        parallelTransition.setAutoReverse(true);

        parallelTransition.play();
    }

    public void fadeOutTransition(Node n) {

        fadeOutTransition = new FadeTransition(Duration.millis(2000), n);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setCycleCount(1);
        fadeOutTransition.setAutoReverse(true);

    }

    public void translateOutTransition(Node n) {

        translateOutTransition
                = new TranslateTransition(Duration.millis(2000), n);
        translateOutTransition.setFromX(n.getLayoutX());
        translateOutTransition.setToX(n.getLayoutX() - 400);
        translateOutTransition.setCycleCount(1);
        translateOutTransition.setAutoReverse(true);
    }

    public void translateInTransition(Node n) {

        translateInTransition
                = new TranslateTransition(Duration.millis(2000), n);
        translateInTransition.setFromX(n.getLayoutX() - 400);
        translateInTransition.setToX(n.getLayoutX() / 12);
        translateInTransition.setCycleCount(1);
        translateInTransition.setAutoReverse(true);
    }

    public void fadeInTransition(Node n) {

        fadeInTransition = new FadeTransition(Duration.millis(2000), n);
        fadeInTransition.setFromValue(0.1);
        fadeInTransition.setToValue(1.0);
        fadeInTransition.setCycleCount(1);
        fadeInTransition.setAutoReverse(true);

    }
}
