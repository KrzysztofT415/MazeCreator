package com.kasta.model.algorithms;

import com.kasta.model.Board;
import com.kasta.model.Hex;
import com.kasta.model.HexState;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackingAlgorithm implements MazeGeneratingAlgorithm {
    private ArrayList<Hex> unvisited;
    private Stack<Hex> stack;
    private Hex currentHex;

    private final Random random = new Random();

    public void init(Board board) {
        unvisited = new ArrayList<>();
        stack = new Stack<>();

        for (int x = 0; x < board.getW(); ++x) {
            for (int y = 0; y < board.getH(); ++y) {
                if (board.getBoard()[x][y].getState() == HexState.WALL) { unvisited.add(board.getBoard()[x][y]); }
                else {
                    board.getBoard()[x][y].setState(HexState.EMPTY);
                    board.getBoard()[x][y].setVisible(false); }
            }
        }

        currentHex = unvisited.get(random.nextInt(unvisited.size()));
        currentHex.setFill(Color.rgb(162, 191,46, 0.5));
        unvisited.remove(currentHex);
    }

    public void step(Board board) throws Exception {
        if (unvisited.size() + stack.size() == 0) { throw new Exception(); }

        ArrayList<Pair<Hex, Integer>> neighbours = new ArrayList<>();

        for (Pair<Point2D, Integer> direction : Hex.directions) {
            int neighbourQ = (int) (currentHex.getCoordinates().getX() + direction.getKey().getX());
            int neighbourR = (int) (currentHex.getCoordinates().getY() + direction.getKey().getY());

            Hex hex = board.getHex(neighbourQ, neighbourR);
            if (unvisited.contains(hex)) {
                neighbours.add(new Pair<>(hex, direction.getValue())); }
        }

        if (neighbours.size() > 0) {
            Pair<Hex, Integer> randomNeighbour = neighbours.get(random.nextInt(neighbours.size()));
            Hex nextHex = randomNeighbour.getKey();
            nextHex.setFill(Color.rgb(162, 191,46, 0.5));
            int value = randomNeighbour.getValue();
            stack.push(currentHex);

            nextHex.makePassage((value + 3) % 6);
            currentHex.makePassage(value);

            currentHex.setState(HexState.VISITED);
            currentHex = nextHex;

            unvisited.remove(currentHex); }

        else if (stack.size() > 0) {
            currentHex.setState(HexState.FINISHED);
            currentHex = stack.pop(); }

        else if (unvisited.size() > 0) {
            currentHex.setState(HexState.FINISHED);
            currentHex = unvisited.get(random.nextInt(unvisited.size()));
            unvisited.remove(currentHex);
        }
        else {
            currentHex.setState(HexState.FINISHED);
        }
    }
}
