package com.kasta.mazegen.model.boards;

import com.kasta.mazegen.view.MainView;
import com.kasta.mazegen.model.CellState;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.util.ArrayList;

public abstract class Board extends AnchorPane {
    private MainView mainView;

    private int width;
    private int height;
    private Cell[][] board;

    public CellState[][] copy() {
        CellState[][] copy = new CellState[this.width][this.height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                copy[x][y] = this.board[x][y].getState(); }
        }

        return copy;
    }

    public void set(CellState[][] newStates) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                this.board[x][y].setVis(true);
                for (int i = 0; i < this.mainView.getBoard().getDirections().size(); i++) {
                    this.board[x][y].getEdges()[i].setVisible(newStates[x][y].isWalls()); }
                this.board[x][y].setState(newStates[x][y]); }
        }
    }

    public Cell getCell(int q, int r) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.compare(q, r)) { return cell; }
            }
        }
        return null;
    }

    public void setCell(int q, int r, Cell cell) { board[q][r] = cell; }

    public void setEditionMode(CellState drawMode) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                cell.setOnMouseDragEnter((EventHandler<MouseEvent>) event -> cell.setState(drawMode));
            }
        }
    }

    public ArrayList<Pair<Point2D,Integer>> getDirections() { return null; }

    public MainView getMainView() { return mainView; }
    public void setMainView(MainView mainView) { this.mainView = mainView; }

    public int getW() { return width; }
    public void setW(int width) { this.width = width; }

    public int getH() { return height; }
    public void setH(int height) { this.height = height; }

    public Cell[][] getBoard() { return board; }
    public void setBoard(Cell[][] board) { this.board = board; }
}
