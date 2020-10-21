package com.kasta.maze.components.editor;

import com.kasta.app.observable.Property;
import com.kasta.maze.model.BoardCellStates;
import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;

public class EditorState {

    private Property<CellState> drawMode = new Property<>(CellState.WALL);
    private Property<CellPosition> cursorPosition = new Property<>();
    private Property<BoardCellStates> editorBoard = new Property<>();

    private Property<Boolean> editInProgress = new Property<>(false);
    private Property<Edit> currentEdit = new Property<>();

    public EditorState(BoardCellStates boardCellStates) {
        editorBoard.set(boardCellStates);
    }

    public Property<CellState> getDrawMode() {
        return drawMode;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Property<BoardCellStates> getEditorBoard() {
        return editorBoard;
    }

    public Property<Boolean> getEditInProgress() {
        return editInProgress;
    }

    public Property<Edit> getCurrentEdit() {
        return currentEdit;
    }
}
