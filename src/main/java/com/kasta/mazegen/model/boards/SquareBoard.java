package com.kasta.mazegen.model.boards;

import com.kasta.mazegen.view.MainView;
import javafx.geometry.Point2D;
import javafx.stage.Screen;
import javafx.util.Pair;

import java.util.ArrayList;

public class SquareBoard extends Board {
    public SquareBoard(MainView mainView) {
        super.setMainView(mainView);

        super.setW((int) (Screen.getPrimary().getBounds().getWidth() * 0.8 / (Hex.getRadius() * 2) + 10));
        super.setW(super.getW() + super.getW() % 2 + 1);
        super.setH((int) (Screen.getPrimary().getBounds().getHeight() * 0.8 / (Hex.getRadius() * Math.sqrt(3))));
        super.setH(super.getH() - super.getH() % 2 - 1);
        super.setBoard(new Square[super.getW()][super.getH()]);

        for (int y = 0; y < super.getH(); ++y) {
            for (int x = 0; x < super.getW(); ++x) {
                int x2 = x - super.getW() / 2;
                int y2 = y - super.getH() / 2;
                super.setCell(x, y, new Square(x2, y2, this));
            }
        }
    }

    @Override
    public ArrayList<Pair<Point2D,Integer>> getDirections() { return Square.directions; }
}
