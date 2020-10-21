package com.kasta.maze.model.algorithms;

import com.kasta.maze.model.CellState;
import com.kasta.maze.view.boards.Board;
import com.kasta.maze.view.cells.Cell;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public class RecursiveBacktrackingAlgorithm implements MazeGeneratingAlgorithm {
    private ArrayList<Cell> unvisited;
    private Stack<Cell> stack;
    private Cell nextCell;

    private final Random random = new Random();
    private boolean initiated = false;

    public void init(Board board) {
        unvisited = new ArrayList<>();
        stack = new Stack<>();

        for (int x = 0; x < board.getW(); ++x) {
            for (int y = 0; y < board.getH(); ++y) {
                if (board.getBoard()[x][y].getState() == CellState.WALL) { unvisited.add(board.getBoard()[x][y]); }
                else {
                    board.getBoard()[x][y].setState(CellState.EMPTY, true);
                    board.getBoard()[x][y].setVis(false); }
            }
        }

        nextCell = unvisited.get(random.nextInt(unvisited.size()));
    }

    public void step(Board board) throws Exception {
        if (!initiated) {
            init(board);
            initiated = true;
        }

        Cell currentCell = nextCell;
        unvisited.remove(currentCell);

        if (unvisited.size() + stack.size() == 0) {
            initiated = false;
            throw new TimeoutException(); }

        ArrayList<Pair<Cell, Integer>> neighbours = new ArrayList<>();
        for (Pair<Point2D, Integer> direction : board.getDirections()) {
            int neighbourQ = (int) (currentCell.getCoordinates().getX() + direction.getKey().getX());
            int neighbourR = (int) (currentCell.getCoordinates().getY() + direction.getKey().getY());

            Cell cell = board.getCell(neighbourQ, neighbourR);
            if (cell == null) { throw new IllegalArgumentException(); }
            if (unvisited.contains(cell)) {
                neighbours.add(new Pair<>(cell, direction.getValue())); }
        }

        if (neighbours.size() > 0) {
            Pair<Cell, Integer> randomNeighbour = neighbours.get(random.nextInt(neighbours.size()));
            nextCell = randomNeighbour.getKey();
            nextCell.setState(CellState.CHOSEN, false);
            int value = randomNeighbour.getValue();
            stack.push(currentCell);

            nextCell.makePassage((value + board.getDirections().size() / 2) % board.getDirections().size());
            currentCell.makePassage(value);

            currentCell.setState(CellState.VISITED, false); }

        else if (stack.size() > 0) {
            nextCell = stack.pop();
            currentCell.setState(CellState.FINISHED, false); }

        else if (unvisited.size() > 0) {
            nextCell = unvisited.get(random.nextInt(unvisited.size()));
            currentCell.setState(CellState.FINISHED, false); }

        else { currentCell.setState(CellState.FINISHED, false); }
    }
}
