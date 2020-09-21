package com.kasta.model;

import com.kasta.model.algorithms.MazeGeneratingAlgorithm;
import com.kasta.model.algorithms.RecursiveBacktrackingAlgorithm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Visualization {

    private Timeline timeline;
    private Board board;
    private HexState[][] initBoard;

    private MazeGeneratingAlgorithm algorithm;
    private boolean initiated = false;

    public Visualization(Board board) {
        this.board = board;
        this.algorithm = new RecursiveBacktrackingAlgorithm();

        this.timeline = new Timeline(new KeyFrame(Duration.millis(300), this::makeStep));
        this.timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void init() {
        initBoard = board.copy();
        initiated = true;
        algorithm.init(board); }

    private void makeStep(ActionEvent actionEvent) { step(); }

    public void step() {
        if (!initiated) { init(); }
        try { algorithm.step(board); }
        catch (Exception ex) { this.timeline.stop(); }
    }

    public void start() {
        if (!initiated) { init(); }
        this.timeline.play(); }

    public void stop() { this.timeline.stop(); }
    public void reset() {
        this.timeline.stop();
        this.board.set(initBoard);
        initiated = false;
    }

    public void setAlgorithm(MazeGeneratingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setTimeline(int time) {
        this.timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(time), this::makeStep));
        if (this.timeline.getStatus() == Animation.Status.RUNNING) { this.timeline.play(); }
    }
}
