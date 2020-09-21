package com.kasta.model.algorithms;

import com.kasta.model.Board;
import com.kasta.model.Hex;
import com.kasta.model.HexState;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class KruskalsAlgorithm implements MazeGeneratingAlgorithm {
    ArrayList<ArrayList<Hex>> sets;
    ArrayList<Pair<Pair<Hex, Integer>,Pair<Hex, Integer>>> walls;

    private final Random random = new Random();

    public void init(Board board) {

        sets = new ArrayList<>();
        for (int x = 0; x < board.getW(); ++x) {
            for (int y = 0; y < board.getH(); ++y) {
                if (board.getBoard()[x][y].getState() == HexState.WALL) {
                    ArrayList<Hex> hs = new ArrayList<>();
                    hs.add(board.getBoard()[x][y]);
                    sets.add(hs);
                }
                else {
                    board.getBoard()[x][y].setState(HexState.EMPTY);
                    board.getBoard()[x][y].setVisible(false); }
            }
        }

        walls = new ArrayList<>();
        for (ArrayList<Hex> currentSet : sets) {
            for (Hex currentHex : currentSet) {
                for (Pair<Point2D,Integer> direction : Hex.directions) {
                    int neighbourQ = (int) (currentHex.getCoordinates().getX() + direction.getKey().getX());
                    int neighbourR = (int) (currentHex.getCoordinates().getY() + direction.getKey().getY());
                    Hex hex = board.getHex(neighbourQ, neighbourR);
                    if (hex != null && hex.getState() != HexState.EMPTY) {
                        Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall1 = new Pair<>(new Pair<>(hex, (direction.getValue() + 3) % 6), new Pair<>(currentHex, direction.getValue()));
                        Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall2 = new Pair<>(new Pair<>(currentHex, direction.getValue()), new Pair<>(hex, (direction.getValue() + 3) % 6));
                        if (!walls.contains(wall1) && !walls.contains(wall2)) { walls.add(wall1); }
                    }
                }
            }
        }

        System.out.println(sets.size() + " " + walls.size());
    }

    public void step(Board board) throws Exception {

        if (sets.size() == 1) { throw new Exception(); }

        else if (walls.size() == 0) {
            for (ArrayList<Hex> set : sets) {
                if (set.size() == 1) { set.get(0).setState(HexState.FINISHED); }
            }
        }

        else {
            Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall = walls.get(random.nextInt(walls.size()));
            ArrayList<Hex> ws1 = null;
            ArrayList<Hex> ws2 = null;
            for (ArrayList<Hex> set : sets) {
                if (set.contains(wall.getKey().getKey())) {
                    ws1 = set;
                    break; }
            }
            for (ArrayList<Hex> set : sets) {
                if (set.contains(wall.getValue().getKey())) {
                    ws2 = set;
                    break; }
            }
            if (ws1 != ws2 && ws1 != null) {
                ws1.addAll(ws2);
                sets.remove(ws2);
                wall.getKey().getKey().setState(HexState.FINISHED);
                wall.getValue().getKey().setState(HexState.FINISHED);
                wall.getKey().getKey().getEdges()[wall.getKey().getValue()].setVisible(false);
                wall.getValue().getKey().getEdges()[wall.getValue().getValue()].setVisible(false);
            }
            walls.remove(wall);
            System.out.println(sets.size() + " " + walls.size());
        }
    }
}
