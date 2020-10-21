//package com.kasta.maze.model.algorithms;
//
//import com.kasta.maze.view.boards.Board;
//import com.kasta.mazegen.model.Change;
//import com.kasta.mazegen.model.boards.Board;
//import com.kasta.mazegen.model.boards.Cell;
//import javafx.geometry.Point2D;
//import javafx.scene.paint.Color;
//import javafx.util.Pair;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class EllersAlgorithm implements com.kasta.mazegen.model.algorithms.MazeGeneratingAlgorithm {
//
//    ArrayList<ArrayList<Cell>> sets;
//
//    private final Random random = new Random();
//
//    @Override
//    public void init(Board board) {
//        Cell randomCell = board.getRandomCell();
//        sets = new ArrayList<>();
//        ArrayList<Cell> set = new ArrayList<>();
//        set.add(randomCell);
//        sets.add(set);
//
//        Pair<Point2D,Integer> direction = board.getDirections().get(random.nextInt(board.getDirections().size()));
//        int neighbourQ = (int) (randomCell.getCoordinates().getX() + direction.getKey().getX());
//        int neighbourR = (int) (randomCell.getCoordinates().getY() + direction.getKey().getY());
//
//        Cell cell = board.getCell(neighbourQ, neighbourR);
//        cell.setFil(Color.rgb(162, 191,46, 0.5));
//
//        cell.makePassage((direction.getValue() + board.getDirections().size() / 2) % board.getDirections().size());
//        randomCell.makePassage(direction.getValue());
//    }
//
//    @Override
//    public void step(Board board) {
//        return null;
//    }
//}
