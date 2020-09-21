package com.kasta.mazegen;

import com.kasta.model.HexState;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox {

    private static String cursorPosFormat = "Cursor : (q:%d, r:%d, s:%d)";
    private static String drawModeFormat = "Mode : %s";

    private Label cursor;
    private Label editingTool;

    public InfoBar() {
        this.cursor = new Label();
        this.editingTool = new Label();

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.cursor, spacer , this.editingTool);
    }

    public void setDrawMode(HexState drawMode) {
        String drawModeS;
        if (drawMode == HexState.EMPTY) { drawModeS = "Erasing"; }
        else if (drawMode == HexState.WALL) { drawModeS = "Drawing"; }
        else { drawModeS = "unknown"; }
        this.editingTool.setText(String.format(drawModeFormat, drawModeS));
    }

    public void setCursorPosition(int q, int r) {
        this.cursor.setText(String.format(cursorPosFormat, q , r, - q - r));
    }
}
