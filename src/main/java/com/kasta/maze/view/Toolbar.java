package com.kasta.maze.view;

import com.kasta.app.event.EventBus;
import com.kasta.maze.Log;
import com.kasta.maze.components.editor.DrawModeEvent;
import com.kasta.maze.components.simulator.SimulatorEvent;
import com.kasta.maze.model.CellState;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private EventBus eventBus;

    public Toolbar(EventBus eventBus) {
        this.eventBus = eventBus;
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);
        Button exit = new Button("Exit");
        exit.setOnAction(this::handleExit);

        this.getItems().addAll(draw, erase, reset, step, start, stop, exit);

        String button = "-fx-text-fill: #ffffff; -fx-background-color: #574838";
        String button_hover = "-fx-background-color: #859424";

        for (Node item : this.getItems()) {
            item.setStyle(button);
            item.setOnMouseEntered(e -> item.setStyle(button_hover));
            item.setOnMouseExited(e -> item.setStyle(button));
        }
        this.setStyle(button);
    }

    private void handleStop(ActionEvent actionEvent) {
        Log.add("Stop button pressed - emitted, emitted, ");
        this.eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STOP));
    }

    private void handleStart(ActionEvent actionEvent) {
        Log.add("Start button pressed - emitted, ");
        this.eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.START));
    }

    private void handleReset(ActionEvent actionEvent) {
        Log.add("Reset button pressed - emitted, ");
        this.eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.RESET));
    }

    private void handleStep(ActionEvent actionEvent) {
        Log.add("Step button pressed - emitted, ");
        this.eventBus.emit(new SimulatorEvent(SimulatorEvent.Type.STEP));
    }

    private void handleErase(ActionEvent actionEvent) {
        Log.add("Erase button pressed - emitted, ");
        this.eventBus.emit(new DrawModeEvent(CellState.EMPTY));
    }

    private void handleDraw(ActionEvent actionEvent) {
        Log.add("Draw button pressed - emitted, ");
        this.eventBus.emit(new DrawModeEvent(CellState.WALL));
    }

    private void handleExit(ActionEvent actionEvent) { System.exit(1); }

}
