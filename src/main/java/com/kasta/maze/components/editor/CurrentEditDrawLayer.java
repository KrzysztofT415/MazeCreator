package com.kasta.maze.components.editor;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;
import com.kasta.maze.view.AbstractDrawLayer;
import com.kasta.maze.view.boards.Board;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CurrentEditDrawLayer extends AbstractDrawLayer {

    private EditorState editorState;

    public CurrentEditDrawLayer(EditorState editorState) {
        this.editorState = editorState;
        editorState.getCurrentEdit().listen(e -> this.invalidate());
    }

    @Override
    public void draw(Board board) {
        if (!editorState.getCurrentEdit().isPresent()) { return; }

        Edit edit = editorState.getCurrentEdit().get();

        for (Change change : edit) {
            CellPosition position = change.getPosition();
            board.setCellState(position.getX(), position.getY(), change.getNewState());
        }
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
