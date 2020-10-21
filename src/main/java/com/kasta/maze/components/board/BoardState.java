package com.kasta.maze.components.board;

import com.kasta.app.observable.Property;
import com.kasta.maze.model.BoardCellStates;

public class BoardState {

    private final Property<BoardCellStates> board = new Property<>();

    public BoardState(BoardCellStates boardCellStates) { this.board.set(boardCellStates); }
    public Property<BoardCellStates> getBoard() { return board; }
}
