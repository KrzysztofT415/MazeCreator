package com.kasta.model.algorithms;

import com.kasta.model.Board;

public interface MazeGeneratingAlgorithm {
    void init(Board board);
    void step(Board board) throws Exception;
}
