package com.kasta.maze.components.board;

import com.kasta.maze.view.AbstractDrawLayer;
import com.kasta.maze.view.boards.Board;

public class BoardDrawLayer extends AbstractDrawLayer {

    private BoardState boardState;

    public BoardDrawLayer(BoardState boardState) {
        this.boardState = boardState;
        boardState.getBoard().listen(b -> this.invalidate());
    }

    @Override
    public void draw(Board board) {
//        BoardCellState boardCellState = this.boardState.getBoard().get();
//        for (int x = 0; x < boardCellState.getWidth(); x++) {
//            for (int y = 0; y < boardCellState.getHeight(); y++) {
//                if (boardCellState.getState(x, y) == CellState.WALL) {
//                    g.fillRect(x, y, 1, 1);
//                }
//            }
//        }
    }

    @Override
    public int getLayer() {
        return 0;
    }
}
