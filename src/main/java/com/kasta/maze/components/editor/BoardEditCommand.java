package com.kasta.maze.components.editor;

import com.kasta.maze.model.BoardCellStates;
import com.kasta.maze.model.CellPosition;

public class BoardEditCommand implements UndoableEditorCommand {

    private Edit edit;

    public BoardEditCommand(Edit edit) {
        this.edit = new Edit(edit);
    }

    @Override
    public void execute(EditorState editorState) {
        BoardCellStates boardCellStates = editorState.getEditorBoard().get();

        for (Change change : edit) {
            CellPosition position = change.getPosition();
            boardCellStates.setState(position.getX(), position.getY(), change.getNewState());
        }

        editorState.getEditorBoard().set(boardCellStates);
    }

    @Override
    public void undo(EditorState editorState) {
        BoardCellStates boardCellStates = editorState.getEditorBoard().get();

        for (Change change : edit) {
            CellPosition position = change.getPosition();
            boardCellStates.setState(position.getX(), position.getY(), change.getPrevState());
        }

        editorState.getEditorBoard().set(boardCellStates);
    }
}
