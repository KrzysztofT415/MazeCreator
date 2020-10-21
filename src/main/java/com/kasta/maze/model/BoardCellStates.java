package com.kasta.maze.model;

public class BoardCellStates {

    private final int width;
    private final int height;
    private final CellState[][] board;

    public BoardCellStates(int[] dimensions) {
        this(dimensions[0], dimensions[1]);
    }

    public BoardCellStates(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new CellState[width][height];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                setState(x, y, CellState.EMPTY);
            }
        }
    }

    public BoardCellStates copy() {
        BoardCellStates copy = new BoardCellStates(this.width, this.height);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                copy.setState(x, y, this.getState(x, y));
            }
        }

        return copy;
    }

    public CellState getState(int x, int y) {
        if (x < 0 || x >= this.width) { return CellState.EMPTY; }
        if (y < 0 || y >= this.height) { return CellState.EMPTY; }
        return this.board[x][y];
    }

    public void setState(int x, int y, CellState cellState) {
        if (x < 0 || x >= this.width) { return; }
        if (y < 0 || y >= this.height) { return; }
        this.board[x][y] = cellState;
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
}
