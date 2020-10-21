package com.kasta.maze;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Log {

    private static boolean logSwitch = true;
    private static final int width = 180;

    private static GridPane logs;
    private static int number = 0;

    private static String part = "";

    static {
        if (logSwitch) {
            Stage stage = new Stage(StageStyle.UNDECORATED);
            logs = new GridPane();
            Scene scene = new Scene(logs, width, Screen.getPrimary().getBounds().getHeight());
            logs.setStyle("-fx-background-color: linear-gradient(to bottom, #768320, #2A6547);");

            stage.setAlwaysOnTop(true);
            stage.setX(Screen.getPrimary().getBounds().getWidth() - 180);
            stage.setY(0);
            stage.setScene(scene);
            stage.show();
            log(" Command's log:");
        }
    }

    public static void add(String newPart) { part += newPart; }

    public static void log(String logInfo) {
        if (logSwitch) {
            Text t = new Text("> " + part + logInfo);
            t.setStyle("-fx-fill: white; -fx-font-size: 14;");

            double msgWidth = t.getLayoutBounds().getWidth();

            if (msgWidth >= width - 50) {
                KeyValue initKeyValue = new KeyValue(t.translateXProperty(), 0);
                KeyFrame initFrame = new KeyFrame(Duration.seconds(1), initKeyValue);

                KeyValue endKeyValue = new KeyValue(t.translateXProperty(), 1.5 * (width - (msgWidth + 50)));
                KeyFrame endFrame = new KeyFrame(Duration.seconds(6), endKeyValue);

                Timeline timeline = new Timeline(initFrame, endFrame);

                timeline.setCycleCount(Timeline.INDEFINITE);
                t.setOnMouseEntered(e -> timeline.playFromStart());
                t.setOnMouseExited(e -> timeline.stop());
            }

            logs.add(t,0,number);
            ++number;

            part = "";
        }
    }

    public static int getWidth() { return (logSwitch ? width : 0); }
}
