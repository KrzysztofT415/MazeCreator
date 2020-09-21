package com.kasta;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;

        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);

        this.getItems().addAll(draw, erase, step, start);
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.getBoard().start();
    }

    private void handleStep(ActionEvent actionEvent) {
        this.mainView.getBoard().step();
    }

    private void handleErase(ActionEvent actionEvent) {
        this.mainView.setDrawMode(HexState.EMPTY);
    }

    private void handleDraw(ActionEvent actionEvent) {
        this.mainView.setDrawMode(HexState.WALL);
    }
}
