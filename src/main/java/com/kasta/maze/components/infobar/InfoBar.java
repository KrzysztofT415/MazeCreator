package com.kasta.maze.components.infobar;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private final Label cursor;
    private final Label editingTool;

    public InfoBar(InfoBarViewModel infoBarViewModel) {
        this.editingTool = new Label();
        this.cursor = new Label();

        infoBarViewModel.getCurrentDrawMode().listen(this::setDrawMode);
        infoBarViewModel.getCursorPosition().listen(this::setCursorPosition);

        Pane spacer = new Pane();
        spacer.setMinSize(0, 0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.editingTool, spacer, this.cursor);
    }

    private void setDrawMode(CellState drawMode) {
        String drawModeString;
        if (drawMode == CellState.WALL) { drawModeString = "Drawing"; }
        else { drawModeString = "Erasing"; }

        String drawModeFormat = "Draw Mode: %s";
        this.editingTool.setText(String.format(drawModeFormat, drawModeString));
    }

    private void setCursorPosition(CellPosition cursorPosition) {
        String cursorPosFormat = "Cursor: (%d, %d)";
        this.cursor.setText(String.format(cursorPosFormat, cursorPosition.getX(), cursorPosition.getY()));
    }

}
