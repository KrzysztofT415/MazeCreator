package com.kasta.maze.components.editor;

import com.kasta.app.command.CommandExecutor;
import com.kasta.maze.Log;
import com.kasta.maze.components.simulator.SimulatorEvent;
import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;

public class Editor {

    private EditorState state;

    private boolean drawingEnabled = true;
    private CommandExecutor commandExecutor;

    public Editor(EditorState state, CommandExecutor commandExecutor) {
        this.state = state;
        this.commandExecutor = commandExecutor;
    }

    public void handle(DrawModeEvent drawModeEvent) {
        DrawModeCommand command = new DrawModeCommand(drawModeEvent.getDrawMode());
        commandExecutor.execute(command);
    }

    public void handle(BoardEvent boardEvent) {
        cursorPositionChanged(boardEvent.getCursorPosition());

        switch (boardEvent.getEventType()) {
            case PRESSED:
                beginEdit();
                handleEdit(boardEvent.getCursorPosition());
                break;
            case CURSOR_MOVED:
                if (state.getEditInProgress().get()) {
                    handleEdit(boardEvent.getCursorPosition());
                }
                break;
            case RELEASED:
                handleEdit(boardEvent.getCursorPosition());
                endEdit();
                break;
        }
    }

    private void beginEdit() {
        Log.log("Starting edit");
        state.getEditInProgress().set(true);
        state.getCurrentEdit().set(new Edit());
    }

    private void endEdit() {
        BoardEditCommand editCommand = new BoardEditCommand(state.getCurrentEdit().get());
        commandExecutor.execute(editCommand);
        state.getEditInProgress().set(false);
        state.getCurrentEdit().set(null);
        Log.log("Edit ended");
    }

    public void handleSimulatorEvent(SimulatorEvent event) {
        if (event.getEventType() == SimulatorEvent.Type.RESET) {
            drawingEnabled = true;
        } else if (event.getEventType() == SimulatorEvent.Type.START || event.getEventType() == SimulatorEvent.Type.STEP) {
            drawingEnabled = false;
        }
    }

    private void handleEdit(CellPosition cursorPosition) {
        if (drawingEnabled) {
            CellState currentState = this.state.getEditorBoard().get().getState(cursorPosition.getX(), cursorPosition.getY());
            CellState newState = this.state.getDrawMode().get();

            Change change = new Change(cursorPosition, newState, currentState);
            state.getCurrentEdit().get().add(change);
        }
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        EditorCommand command = (state) -> state.getCursorPosition().set(cursorPosition);
        commandExecutor.execute(command);
    }
}
