package com.kasta.mazegen.model.algorithms;

import com.kasta.mazegen.model.*;
import com.kasta.mazegen.model.boards.Board;
import com.kasta.mazegen.model.boards.Cell;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackingAlgorithm implements MazeGeneratingAlgorithm {
    private ArrayList<Cell> unvisited;
    private Stack<Cell> stack;
    private Cell currentCell;

    private final Random random = new Random();

    public void init(Board board) {
        unvisited = new ArrayList<>();
        stack = new Stack<>();

        for (int x = 0; x < board.getW(); ++x) {
            for (int y = 0; y < board.getH(); ++y) {
                if (board.getBoard()[x][y].getState() == CellState.WALL) { unvisited.add(board.getBoard()[x][y]); }
                else {
                    board.getBoard()[x][y].setState(CellState.EMPTY);
                    board.getBoard()[x][y].setVis(false); }
            }
        }

        currentCell = unvisited.get(random.nextInt(unvisited.size()));
        currentCell.setFil(Color.rgb(162, 191,46, 0.5));
        unvisited.remove(currentCell);
    }

    public void step(Board board) throws Exception {
        if (unvisited.size() + stack.size() == 0) {
            currentCell.setState(CellState.FINISHED);
            throw new Exception(); }

        ArrayList<Pair<Cell, Integer>> neighbours = new ArrayList<>();

        for (Pair<Point2D, Integer> direction : board.getDirections()) {
            int neighbourQ = (int) (currentCell.getCoordinates().getX() + direction.getKey().getX());
            int neighbourR = (int) (currentCell.getCoordinates().getY() + direction.getKey().getY());

            Cell cell = board.getCell(neighbourQ, neighbourR);
            if (unvisited.contains(cell)) {
                neighbours.add(new Pair<>(cell, direction.getValue())); }
        }

        System.out.println(neighbours.size());
        if (neighbours.size() > 0) {
            Pair<Cell, Integer> randomNeighbour = neighbours.get(random.nextInt(neighbours.size()));
            Cell nextCell = randomNeighbour.getKey();
            nextCell.setFil(Color.rgb(162, 191,46, 0.5));
            int value = randomNeighbour.getValue();
            stack.push(currentCell);

            nextCell.makePassage((value + board.getDirections().size() / 2) % board.getDirections().size());
            currentCell.makePassage(value);

            currentCell.setState(CellState.VISITED);
            currentCell = nextCell;

            unvisited.remove(currentCell); }

        else if (stack.size() > 0) {
            currentCell.setState(CellState.FINISHED);
            currentCell = stack.pop(); }

        else if (unvisited.size() > 0) {
            currentCell.setState(CellState.FINISHED);
            currentCell = unvisited.get(random.nextInt(unvisited.size()));
            unvisited.remove(currentCell);
        }
        else {
            currentCell.setState(CellState.FINISHED);
        }

        System.out.println(unvisited.size() + " " + stack.size());
    }
}
