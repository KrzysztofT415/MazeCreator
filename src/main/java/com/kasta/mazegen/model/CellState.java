package com.kasta.mazegen.model;

import javafx.scene.paint.Color;

public enum CellState {
    EMPTY(Color.rgb(144,224,239), false),
    WALL(Color.rgb(44, 30, 17), true),
    VISITED(Color.rgb(202, 240, 248), true),
    FINISHED(Color.rgb(112,231,255), true);

    private final Color inside;
    private final boolean walls;

    CellState(Color inside, boolean walls) {
        this.inside = inside;
        this.walls = walls; }

    public Color getInsideColor() { return inside; }
    public boolean isWalls() { return walls; }
}
