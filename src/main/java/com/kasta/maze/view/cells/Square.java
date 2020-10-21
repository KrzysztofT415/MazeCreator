package com.kasta.maze.view.cells;

import com.kasta.maze.model.CellPosition;
import com.kasta.maze.model.CellState;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Square extends Rectangle implements Cell {

    static private final int radius = 50;
    static private final int spacing = 0;
    static private final Rectangle2D screenSize = Screen.getPrimary().getBounds();
    static private final Point2D boardCenter = new Point2D(screenSize.getWidth() * 0.5, screenSize.getHeight() * 0.5);

    public static final ArrayList<Pair<Point2D,Integer>> directions = new ArrayList<>(Arrays.asList(
            new Pair<>(new Point2D(1,0), 0), //E
            new Pair<>(new Point2D(0,1), 1), //S
            new Pair<>(new Point2D(-1,0), 2), //W
            new Pair<>(new Point2D(0,-1), 3) //N
    ));

    private final CellPosition coordinates;
    private final CellPosition gridCoordinates;
    private final Point2D center;
    private final Point2D[] corners = new Point2D[4];
    private final Line[] edges = new Line[4];
    private CellState state;

    public Square(int gridX, int gridY, int q, int r, Pane root) {
        //Setting cube coordinates
        gridCoordinates = new CellPosition(gridX, gridY);
        coordinates = new CellPosition(q, r);

        //Calculating hex center
        double x2 = boardCenter.getX() + q * (radius + spacing);
        double y2 = boardCenter.getY() + r * (radius + spacing);
        center = new Point2D(x2, y2);

        //Calculating hex corners
        corners[0] = new Point2D(x2 + radius / 2f, y2 - radius / 2f);
        corners[1] = new Point2D(x2 + radius / 2f, y2 + radius / 2f);
        corners[2] = new Point2D(x2 - radius / 2f, y2 + radius / 2f);
        corners[3] = new Point2D(x2 - radius / 2f, y2 - radius / 2f);

        //Calculating hex edges
        for (int i = 0; i < 4; ++i) {
            edges[i] = new Line(corners[i].getX(), corners[i].getY(), corners[(i + 1) % 4].getX(), corners[(i + 1) % 4].getY());
            edges[i].setStrokeWidth(1);
            edges[i].setStroke(Color.rgb(87,72,56)); }

        state = CellState.EMPTY;

        setX(center.getX() - radius / 2f);
        setY(center.getY() - radius / 2f);
        setWidth(radius);
        setHeight(radius);
        setFill(state.getInsideColor());
        for (Line edge : edges) {
            edge.setVisible(state.isWalls()); }

        //Adding to root
        root.getChildren().add(this);
        for (Line line : edges) {
            root.getChildren().add(line); }
    }

    @Override
    public boolean compare(int q, int r) {
        return this.coordinates.getX() == q && this.coordinates.getY() == r; }

    @Override
    public void makePassage(int w) { edges[w].setVisible(false); }
    @Override
    public Point2D getCenter() { return center; }
    @Override
    public CellPosition getCoordinates() { return coordinates; }
    @Override
    public CellPosition getGridCoordinates() { return gridCoordinates; }
    @Override
    public Line[] getEdges() { return edges; }

    @Override
    public void setState(CellState state, boolean animate) {
        if (!this.state.isWalls() && state.isWalls()) {
            for (int i = 0; i < 4; ++i) {
                edges[i].setVisible(true); }
        }
        if (state == CellState.EMPTY) {
            for (int i = 0; i < 4; ++i) {
                edges[i].setVisible(false); }
        }
        this.state = state;
        setFill(state.getInsideColor());
    }
    @Override
    public Paint getFil() { return this.getFill(); }

    @Override
    public CellState getState() { return state; }

    @Override
    public boolean contain(int x, int y) { return this.contains(x, y); }

    public static int getRadius() { return radius; }

    public void setVis(boolean visible) { this.setVisible(visible); }
    public void setOnMouseDragEnter(EventHandler<? super MouseDragEvent> eventHandler) { this.setOnMouseDragEntered(eventHandler); }
    public void setFil(Color color) { this.setFill(color); }
}