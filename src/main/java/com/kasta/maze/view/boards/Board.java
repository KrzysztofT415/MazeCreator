package com.kasta.maze.view.boards;

import com.kasta.maze.model.CellState;
import com.kasta.maze.view.cells.Cell;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Random;

public abstract class Board {
    private Random random;

    private int width;
    private int height;
    private Cell[][] board;

    public Cell getCell(int q, int r) {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.compare(q, r)) { return cell; }
            }
        }
        return null;
    }
    public void setCell(int x, int y, Cell cell) { board[x][y] = cell; }
    public void setCellState(int x, int y, CellState newState) {
        if (x < 0 || x >= this.width) { return; }
        if (y < 0 || y >= this.height) { return; }
        board[x][y].setState(newState, false);
    }

    public abstract ArrayList<Pair<Point2D,Integer>> getDirections();

    public int getW() { return width; }
    public void setW(int width) { this.width = width; }

    public int getH() { return height; }
    public void setH(int height) { this.height = height; }

    public Cell[][] getBoard() { return board; }
    public void setBoard(Cell[][] board) { this.board = board; }

    public int[] getDimensions() { return new int[] {width, height}; }

    public Cell getRandomCell() {
        while (true) {
            Cell cell = board[random.nextInt(width)][random.nextInt(height)];
            if (cell != null) { return cell; }
        }
    }
}
