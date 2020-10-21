package com.kasta.maze.view.cells;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public interface Cell {
    boolean compare(int q, int r);
    void makePassage(int w);
    Point2D getCenter();
    CellPosition getCoordinates();
    CellPosition getGridCoordinates();
    Line[] getEdges();
    boolean contain(int x, int y);

    void setState(CellState state, boolean animate);
    CellState getState();

    void setVis(boolean visible);
    void setOnMouseDragEnter(EventHandler<? super MouseDragEvent> eventHandler);
    Paint getFil();
    void setFil(Color color);
}
