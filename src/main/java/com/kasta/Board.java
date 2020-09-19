package com.kasta;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Board extends Pane {

    int width;
    int height;
    Hex[][] board;

    ArrayList<Hex> unvisited;
    ArrayList<Hex> stack;
    Hex currentHex;

    Random random = new Random();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Hex[width][height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int x2 = x - width / 2;
                int y2 = y - height / 2 - x2 / 2 + (x2 < 0 && -x2 % 2 == 1 ? 1 : 0);
                board[x][y] = new Hex(x2, y2, this);
            }
        }
    }

    public void start() {
        unvisited = new ArrayList<>();
        stack = new ArrayList<>();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (board[x][y].getState() == HexState.WALL) { unvisited.add(board[x][y]); }
                else {
                    board[x][y].setState(HexState.EMPTY);
                    board[x][y].setVisible(false); }
            }
        }

        currentHex = unvisited.get(random.nextInt(unvisited.size()));
        unvisited.remove(currentHex);
    }

    public void step() {
        ArrayList<Pair<Hex, Integer>> neighbours = new ArrayList<>();

        for (Pair<Point2D, Integer> direction : Hex.directions) {
            int neighbourQ = (int) (currentHex.getCoordinates().getX() + direction.getKey().getX());
            int neighbourR = (int) (currentHex.getCoordinates().getY() + direction.getKey().getY());

            Hex hex = getHex(neighbourQ, neighbourR);
            if (unvisited.contains(hex)) {
                neighbours.add(new Pair<>(hex, direction.getValue())); }
        }

        if (neighbours.size() > 0) {
            Pair<Hex, Integer> randomNeighbour = neighbours.get(random.nextInt(neighbours.size()));
            Hex nextHex = randomNeighbour.getKey();
            int value = randomNeighbour.getValue();
            stack.add(currentHex);

            nextHex.makePassage((value + 3) % 6);
            currentHex.makePassage(value);

            currentHex.setState(HexState.VISITED);
            currentHex = nextHex;

            unvisited.remove(currentHex); }

        else if (stack.size() > 0) {
            currentHex.setState(HexState.FINISHED);
            currentHex = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1); }

        System.out.println(unvisited.size() + " " + stack.size());
    }

    Hex getHex(int q, int r) {
        for (Hex[] row : board) {
            for (Hex hex : row) {
                if (hex.compare(q, r)) return hex;
            }
        }
        return null;
    }
}
