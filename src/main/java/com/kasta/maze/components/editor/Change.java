package com.kasta.maze.components.editor;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;

public class Change {
    private CellPosition position;
    private CellState newState;
    private CellState prevState;

    public Change(CellPosition position, CellState newState, CellState prevState) {
        this.position = position;
        this.newState = newState;
        this.prevState = prevState;
    }

    public CellPosition getPosition() {
        return position;
    }

    public CellState getNewState() {
        return newState;
    }

    public CellState getPrevState() {
        return prevState;
    }
}
