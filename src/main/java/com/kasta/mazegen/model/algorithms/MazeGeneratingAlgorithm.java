package com.kasta.mazegen.model.algorithms;

import com.kasta.mazegen.model.boards.Board;

public interface MazeGeneratingAlgorithm {
    void init(Board board);
    void step(Board board) throws Exception;
}
