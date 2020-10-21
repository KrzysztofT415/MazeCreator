package com.kasta.maze.components.infobar;

import com.kasta.app.observable.Property;
import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;

public class InfoBarViewModel {
    private final Property<CellState> currentDrawMode = new Property<>(CellState.WALL);
    private final Property<CellPosition> cursorPosition = new Property<>();

    public Property<CellState> getCurrentDrawMode() { return currentDrawMode; }
    public Property<CellPosition> getCursorPosition() { return cursorPosition; }
}
