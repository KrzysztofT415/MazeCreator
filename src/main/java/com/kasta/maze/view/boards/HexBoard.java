package com.kasta.maze.view.boards;

import com.kasta.maze.Log;
import com.kasta.maze.view.cells.Hex;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Pair;

import java.util.ArrayList;

public class HexBoard extends Board {
    public HexBoard(Pane boardPane) {

        super.setW((int) ((Screen.getPrimary().getBounds().getWidth() * 0.8 - Log.getWidth()) / (Hex.getRadius() * 2) + 10));
        super.setW(super.getW() + super.getW() % 2 + 1);
        super.setH((int) (Screen.getPrimary().getBounds().getHeight() * 0.8 / (Hex.getRadius() * Math.sqrt(3))));
        super.setH(super.getH() - super.getH() % 2 - 1);
        super.setBoard(new Hex[super.getW()][super.getH()]);

        for (int y = 0; y < super.getH(); ++y) {
            for (int x = 0; x < super.getW(); ++x) {
                int x2 = x - super.getW() / 2;
                int y2 = y - super.getH() / 2 - x2 / 2 + (x2 < 0 && -x2 % 2 == 1 ? 1 : 0);
                super.setCell(x, y, new Hex(x, y, x2, y2, boardPane));
            }
        }
    }

    @Override
    public ArrayList<Pair<Point2D,Integer>> getDirections() { return Hex.directions; }
}
