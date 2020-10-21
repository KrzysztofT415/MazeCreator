package com.kasta.maze.components.simulator;

import com.kasta.app.command.CommandExecutor;
import com.kasta.maze.Log;
import com.kasta.maze.model.Simulation;
import com.kasta.maze.model.algorithms.RecursiveBacktrackingAlgorithm;
import com.kasta.maze.view.MainView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private final Timeline timeline;
    private Simulation simulation;

    private final SimulatorState state;
    private final CommandExecutor commandExecutor;
    private final MainView mainView;

    private boolean reset = true;

    public Simulator(SimulatorState state, CommandExecutor commandExecutor, MainView mainView) {
        this.state = state;
        this.commandExecutor = commandExecutor;
        this.mainView = mainView;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> this.doStep()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void handle(SimulatorEvent event) {
        Log.log("handled - success");
        switch (event.getEventType()) {
            case START:
                start();
                break;
            case STOP:
                stop();
                break;
            case STEP:
                doStep();
                break;
            case RESET:
                reset();
                break;
        }
    }

    private void doStep() {
        if (reset) {
            reset = false;
            this.simulation = new Simulation(this, mainView.getBoard(), state.getBoard().get(), new RecursiveBacktrackingAlgorithm());
            this.state.getSimulating().set(true);
        }

        this.simulation.step();

        SimulatorCommand command = (state) -> state.getBoard().set(simulation.getBoard());
        commandExecutor.execute(command);
    }

    private void start() {
        this.timeline.play();
    }

    private void stop() {
        this.timeline.stop();
    }

    private void reset() {
        reset = true;
        this.state.getSimulating().set(false);
    }
}
