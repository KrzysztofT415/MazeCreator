package com.kasta.maze.components.board;

import com.kasta.maze.ApplicationComponent;
import com.kasta.maze.ApplicationContext;
import com.kasta.maze.model.BoardCellStates;

public class BoardApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        BoardState state = context.getStateRegistry().getState(BoardState.class);

        BoardDrawLayer boardDrawLayer = new BoardDrawLayer(state);
        context.getMainView().addDrawLayer(boardDrawLayer);
    }

    @Override
    public void initState(ApplicationContext context) {
        BoardCellStates board = new BoardCellStates(context.getMainView().getBoardDimensions());
        BoardState boardState = new BoardState(board);
        context.getStateRegistry().registerState(BoardState.class, boardState);
    }
}
