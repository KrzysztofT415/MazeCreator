package com.kasta.maze.components.editor;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.view.AbstractDrawLayer;
import com.kasta.maze.view.boards.Board;

public class ToolDrawLayer extends AbstractDrawLayer {

    private EditorState editorState;

    public ToolDrawLayer(EditorState editorState) {
        this.editorState = editorState;
        this.editorState.getCursorPosition().listen(cp -> this.invalidate());
    }

    @Override
    public void draw(Board board) {
        CellPosition cursor = editorState.getCursorPosition().get();

        if (cursor != null) {
        }
    }

    @Override
    public int getLayer() {
        return 9;
    }
}
