package com.kasta.maze.components.simulator;

import com.kasta.app.observable.Property;
import com.kasta.maze.model.BoardCellStates;

public class SimulatorState {
    private Property<BoardCellStates> board = new Property<>();

    private Property<Boolean> simulating = new Property<>(false);

    public SimulatorState(BoardCellStates boardCellStates) {
        this.board.set(boardCellStates);
    }

    public Property<BoardCellStates> getBoard() {
        return board;
    }

    public Property<Boolean> getSimulating() {
        return simulating;
    }
}
