package com.kasta.mazegen.model.boards;

import com.kasta.mazegen.model.CellState;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public interface Cell {
    boolean compare(int q, int r);
    void makePassage(int w);
    Point2D getCenter();
    Point2D getCoordinates();
    public Line[] getEdges();

    void setState(CellState state);
    CellState getState();

    void setVis(boolean visible);
    void setOnMouseDragEnter(EventHandler<? super MouseDragEvent> eventHandler);
    void setFil(Color color);
}
