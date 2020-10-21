package com.kasta.maze.view;

import com.kasta.maze.view.boards.Board;

public interface DrawLayer {

    void draw(Board g);

    int getLayer();

    void addInvalidationListener(InvalidationListener listener);

}
