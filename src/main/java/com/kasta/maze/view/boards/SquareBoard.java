package com.kasta.maze.view.boards;

import com.kasta.maze.Log;
import com.kasta.maze.view.cells.Square;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Pair;
import java.util.ArrayList;

public class SquareBoard extends Board {
    public SquareBoard(Pane board) {

        super.setW((int) ((Screen.getPrimary().getBounds().getWidth() * 0.8 - Log.getWidth()) / (Square.getRadius() * 2) + 10));
        super.setW(super.getW() + super.getW() % 2 + 1);
        super.setH((int) (Screen.getPrimary().getBounds().getHeight() * 0.8 / (Square.getRadius() * Math.sqrt(3))));
        super.setH(super.getH() - super.getH() % 2 - 1);
        super.setBoard(new Square[super.getW()][super.getH()]);

        for (int y = 0; y < super.getH(); ++y) {
            for (int x = 0; x < super.getW(); ++x) {
                int q = x - super.getW() / 2;
                int r = y - super.getH() / 2;
                super.setCell(x, y, new Square(x, y, q, r, board));
            }
        }
    }

    @Override
    public ArrayList<Pair<Point2D,Integer>> getDirections() { return Square.directions; }
}
