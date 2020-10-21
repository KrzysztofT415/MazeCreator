//package com.kasta.maze.model.algorithms;
//
//import com.kasta.maze.view.boards.Board;
//import com.kasta.mazegen.model.*;
//import com.kasta.mazegen.model.boards.Board;
//import com.kasta.mazegen.model.boards.Cell;
//import javafx.geometry.Point2D;
//import javafx.util.Pair;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class KruskalsAlgorithm implements com.kasta.mazegen.model.algorithms.MazeGeneratingAlgorithm {
//    ArrayList<ArrayList<Cell>> sets;
//    ArrayList<Pair<Pair<Cell, Integer>,Pair<Cell, Integer>>> walls;
//
//    private final Random random = new Random();
//
//    public void init(Board board) {
//
//        sets = new ArrayList<>();
//        for (int x = 0; x < board.getW(); ++x) {
//            for (int y = 0; y < board.getH(); ++y) {
//                if (board.getBoard()[x][y].getState() == CellState.WALL) {
//                    ArrayList<Cell> hs = new ArrayList<>();
//                    hs.add(board.getBoard()[x][y]);
//                    sets.add(hs);
//                }
//                else {
//                    board.getBoard()[x][y].setState(CellState.EMPTY);
//                    board.getBoard()[x][y].setVis(false); }
//            }
//        }
//
//        walls = new ArrayList<>();
//        for (ArrayList<Cell> currentSet : sets) {
//            for (Cell currentCell : currentSet) {
//                for (Pair<Point2D,Integer> direction : board.getDirections()) {
//                    int neighbourQ = (int) (currentCell.getCoordinates().getX() + direction.getKey().getX());
//                    int neighbourR = (int) (currentCell.getCoordinates().getY() + direction.getKey().getY());
//                    Cell hex = board.getCell(neighbourQ, neighbourR);
//                    if (hex != null && hex.getState() != CellState.EMPTY) {
//                        Pair<Pair<Cell, Integer>,Pair<Cell, Integer>> wall1 = new Pair<>(new Pair<>(hex, (direction.getValue() + board.getDirections().size() / 2) % board.getDirections().size()), new Pair<>(currentCell, direction.getValue()));
//                        Pair<Pair<Cell, Integer>,Pair<Cell, Integer>> wall2 = new Pair<>(new Pair<>(currentCell, direction.getValue()), new Pair<>(hex, (direction.getValue() + board.getDirections().size() / 2) % board.getDirections().size()));
//                        if (!walls.contains(wall1) && !walls.contains(wall2)) { walls.add(wall1); }
//                    }
//                }
//            }
//        }
//    }
//
//    public void step(Board board) {
//
//        if (sets.size() == 1) { return null; }
//
//        else if (walls.size() == 0) {
//            for (ArrayList<Cell> set : sets) {
//                if (set.size() == 1) { set.get(0).setState(CellState.FINISHED); }
//            }
//        }
//
//        else {
//            Pair<Pair<Cell, Integer>,Pair<Cell, Integer>> wall = walls.get(random.nextInt(walls.size()));
//            ArrayList<Cell> ws1 = null;
//            ArrayList<Cell> ws2 = null;
//            for (ArrayList<Cell> set : sets) {
//                if (set.contains(wall.getKey().getKey())) {
//                    ws1 = set;
//                    break; }
//            }
//            for (ArrayList<Cell> set : sets) {
//                if (set.contains(wall.getValue().getKey())) {
//                    ws2 = set;
//                    break; }
//            }
//            if (ws1 != ws2 && ws1 != null) {
//                ws1.addAll(ws2);
//                sets.remove(ws2);
//                wall.getKey().getKey().setState(CellState.FINISHED);
//                wall.getValue().getKey().setState(CellState.FINISHED);
//                wall.getKey().getKey().getEdges()[wall.getKey().getValue()].setVisible(false);
//                wall.getValue().getKey().getEdges()[wall.getValue().getValue()].setVisible(false);
//            }
//            walls.remove(wall);
//        }
//        return null;
//    }
//}
