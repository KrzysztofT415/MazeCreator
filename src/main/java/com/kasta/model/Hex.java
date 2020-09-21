package com.kasta.model;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Screen;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Hex extends Polygon implements Cell {

    static private final int radius = 25;
    static private final int spacing = 0;
    static private final Rectangle2D screenSize = Screen.getPrimary().getBounds();
    static private final Point2D boardCenter = new Point2D(screenSize.getWidth() * 0.5, screenSize.getHeight() * 0.5);

    public static final ArrayList<Pair<Point2D,Integer>> directions = new ArrayList<>(Arrays.asList(
            new Pair<>(new Point2D(1,0), 0), //E
            new Pair<>(new Point2D(0,1), 1), //S
            new Pair<>(new Point2D(-1,1), 2), //B
            new Pair<>(new Point2D(-1,0), 3), //W
            new Pair<>(new Point2D(0,-1), 4), //N
            new Pair<>(new Point2D(1,-1), 5) //F
    ));

    private final Point3D coordinates;
    private final Point2D center;
    private final Point2D[] corners = new Point2D[6];
    private final Line[] edges = new Line[6];
    private HexState state;

    Hex(int q, int r, Pane root) {
        //Setting cube coordinates
        coordinates = new Point3D(q, r, - q - r);

        //Calculating hex center
        double x = boardCenter.getX() + q * (radius + spacing) * 1.5;
        double y = boardCenter.getY() + (r + q / 2.0) * (radius + spacing) * Math.sqrt(3);
        center = new Point2D(x, y);

        //Calculating hex corners
        for (int i = 0; i < 6; ++i) {
            double angle = Math.PI  * i / 3;
            x = center.getX() + radius * (Math.cos(angle));
            y = center.getY() + radius * (Math.sin(angle));
            corners[i] = new Point2D(x, y);
            this.getPoints().add(x);
            this.getPoints().add(y); }

        //Calculating hex edges
        for (int i = 0; i < 6; ++i) {
            edges[i] = new Line(corners[i].getX(), corners[i].getY(), corners[(i + 1) % 6].getX(), corners[(i + 1) % 6].getY());
            edges[i].setStrokeWidth(1);
            edges[i].setStroke(Color.rgb(87,72,56)); }

        state = HexState.EMPTY;

        setFill(state.getInsideColor());
        for (int i = 0; i < 6; ++i) {
            edges[i].setVisible(state.isWalls()); }

        //Adding to root
        root.getChildren().add(this);
        for (Line line : edges) {
            root.getChildren().add(line); }
    }

    boolean compare(int q, int r) {
        return this.coordinates.getX() == q && this.coordinates.getY() == r; }

    public void makePassage(int w) { edges[w].setVisible(false); }

    Point2D getCenter() { return center; }
    public Point3D getCoordinates() { return coordinates; }
    public Line[] getEdges() { return edges; }

    public void setState(HexState state) {
        if (!this.state.isWalls() && state.isWalls()) {
            for (int i = 0; i < 6; ++i) {
                edges[i].setVisible(true); }
        }
        if (state == HexState.EMPTY) {
            for (int i = 0; i < 6; ++i) {
                edges[i].setVisible(false); }
        }
        this.state = state;
        setFill(state.getInsideColor());
    }
    public HexState getState() { return state; }
    public static int getRadius() { return radius; }
}