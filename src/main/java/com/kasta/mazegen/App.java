package com.kasta.mazegen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, Screen.getPrimary().getBounds().getWidth() * 0.9, Screen.getPrimary().getBounds().getHeight() * 0.9);
        stage.setScene(scene);
        stage.show(); }

    public static void main(String[] args) { launch(); }
}