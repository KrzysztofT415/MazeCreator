package com.kasta.mazegen.components.infobar;

import com.kasta.mazegen.model.CellState;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private static final String cursorPosFormat = "Cursor : (q:%d, r:%d, s:%d)";
    private static final String drawModeFormat = "Mode : %s";

    private final Label cursor;
    private final Label editingTool;

    public InfoBar() {
        this.cursor = new Label();
        this.editingTool = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.cursor, spacer , this.editingTool);
    }

    public void setDrawMode(CellState drawMode) {
        String drawModeS;
        if (drawMode == CellState.EMPTY) { drawModeS = "Erasing"; }
        else if (drawMode == CellState.WALL) { drawModeS = "Drawing"; }
        else { drawModeS = "unknown"; }
        this.editingTool.setText(String.format(drawModeFormat, drawModeS));
    }

    public void setCursorPosition(int q, int r) {
        this.cursor.setText(String.format(cursorPosFormat, q , r, - q - r));
    }
}
