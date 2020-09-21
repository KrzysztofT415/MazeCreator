package com.kasta.model;

import com.kasta.mazegen.MainView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

public class Board extends AnchorPane {
    private MainView mainView;

    private int width;
    private int height;
    private Hex[][] board;

    public Board(MainView mainView) {
        this.mainView = mainView;

        this.width = (int) (Screen.getPrimary().getBounds().getWidth() * 0.8 / (Hex.getRadius() * 2) + 10);
        width = width + width % 2 + 1;
        this.height = (int) (Screen.getPrimary().getBounds().getHeight() * 0.8 / (Hex.getRadius() * Math.sqrt(3)));
        height = height - height % 2 - 1;
        this.board = new Hex[width][height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int x2 = x - width / 2;
                int y2 = y - height / 2 - x2 / 2 + (x2 < 0 && -x2 % 2 == 1 ? 1 : 0);
                board[x][y] = new Hex(x2, y2, this);
            }
        }
    }

    public HexState[][] copy() {
        HexState[][] copy = new HexState[this.width][this.height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                copy[x][y] = this.board[x][y].getState(); }
        }

        return copy;
    }

    public void set(HexState[][] newStates) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                this.board[x][y].setVisible(true);
                for (int i = 0; i < 6; i++) {
                    this.board[x][y].getEdges()[i].setVisible(newStates[x][y].isWalls()); }
                this.board[x][y].setState(newStates[x][y]); }
        }
    }

    public Hex getHex(int q, int r) {
        for (Hex[] row : board) {
            for (Hex hex : row) {
                if (hex.compare(q, r)) { return hex; }
            }
        }
        return null;
    }

    public void setEditionMode(HexState drawMode) {
        for (Hex[] row : board) {
            for (Hex hex : row) {
                hex.setOnMouseDragEntered((EventHandler<MouseEvent>) event -> hex.setState(drawMode));
            }
        }
    }

    public int getW() { return width; }
    public void setW(int width) { this.width = width; }

    public int getH() { return height; }
    public void setH(int height) { this.height = height; }

    public Hex[][] getBoard() { return board; }
    public void setBoard(Hex[][] board) { this.board = board; }
}
