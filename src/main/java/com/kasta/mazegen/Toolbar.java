package com.kasta.mazegen;

import com.kasta.model.HexState;
import com.kasta.model.algorithms.KruskalsAlgorithm;
import com.kasta.model.algorithms.RecursiveBacktrackingAlgorithm;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class Toolbar extends ToolBar {

    private final MainView mainView;
    private final ChoiceBox<String> choice;
    private final Slider time;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;

        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(250, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        time = new Slider(50, 500, 300);
        time.valueProperty().addListener(this::handleTime);

        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);

        choice = new ChoiceBox<>();

        choice.getItems().addAll("Recursive Backtracking", "Kruskal's Algorithm");
        choice.setValue("Recursive Backtracking");
        choice.onActionProperty().setValue(this::handleChoice);

        this.getItems().addAll(choice, draw, erase, spacer, time, step, start, stop, reset);
    }

    private void handleTime(Observable observable) {
        System.out.println("changed to :" + time.getValue());
        this.mainView.getVisualization().setTimeline((int) this.time.getValue());
    }

    private void handleChoice(ActionEvent actionEvent) {
        switch (choice.getValue()) {
            case "Recursive Backtracking":
                this.mainView.getVisualization().setAlgorithm(new RecursiveBacktrackingAlgorithm());
                break;
            case "Kruskal's Algorithm":
                this.mainView.getVisualization().setAlgorithm(new KruskalsAlgorithm());
                break;
        }
    }
    private void handleStop(ActionEvent actionEvent) { this.mainView.getVisualization().stop(); }
    private void handleStart(ActionEvent actionEvent) { this.mainView.getVisualization().start(); }
    private void handleReset(ActionEvent actionEvent) { this.mainView.getVisualization().reset(); }
    private void handleStep(ActionEvent actionEvent) { this.mainView.getVisualization().step(); }
    private void handleErase(ActionEvent actionEvent) { this.mainView.setDrawMode(HexState.EMPTY); }
    private void handleDraw(ActionEvent actionEvent) { this.mainView.setDrawMode(HexState.WALL); }
}
