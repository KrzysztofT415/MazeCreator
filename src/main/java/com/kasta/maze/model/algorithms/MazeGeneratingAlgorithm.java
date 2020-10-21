package com.kasta.maze.model.algorithms;

import com.kasta.maze.view.boards.Board;

public interface MazeGeneratingAlgorithm {
    void init(Board board);
    void step(Board board) throws Exception;
}
