//package com.MazeGen;
//
//import javafx.animation.*;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Point2D;
//import javafx.scene.Scene;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.text.Text;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.util.Pair;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class HexBoard extends Application {
//
//    private final ArrayList<Hex> board = new ArrayList<>();
//    private final Random random = new Random();
//    private Hex currentHex;
//
//    @Override
//    public void start(Stage stage) {
//        random.setSeed(0);
//
//        Pane root = new Pane();
//
//        initBoard(root);
//        recursiveBacktracking(100);
////        kruskal(100);
//
//        root.setOnMouseClicked(mouseEvent -> {
//            board.add(new Hex(2, 2, root));
//        });
//
//        Scene scene = new Scene(root);
//        scene.setFill(Color.rgb(144,224,239));
//        stage.setTitle("Grid");
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.setAlwaysOnTop(true);
//
//        stage.show();
//    }
//
//    private void initBoard(Pane root) {
//        int r = 3;
////        for (int a = -r; a <= r; ++a) {
////            for (int b = -r; b <= r; ++b) {
////                for (int c = -r; c <= r; ++c) {
////                    if (a + b + c == 0) {
////                        board.add(new Hex(a, b, root));
////                    }
////                }
////            }
////        }
////        for (int a = -r; a <= r; ++a) {
////            for (int b = -r; b <= r; ++b) {
////                if (a == r || b == r || a == 0 || b == 0) {
////                    board.add(new Hex(a, b, root));
////                }
////            }
////        }
//        System.out.println(Screen.getPrimary().getBounds() + " " + Screen.getPrimary().getBounds().getHeight() / Hex.getRadius() / Math.sqrt(3));
//        int width = (int) (Screen.getPrimary().getBounds().getWidth() / (Hex.getRadius() * 2)) + 1;
//        int height = (int) (Screen.getPrimary().getBounds().getHeight() / (Hex.getRadius() * Math.sqrt(3))) - 1;
//        System.out.println(width);
//        for (int a = 0; a < width; ++a) {
//            for (int b = 0; b < height; ++b) {
//                int a2 = a - width / 2;
//                int b2 = b - height / 2 - a2/2 + (a2 < 0 && -a2 % 2 == 1 ? 1 : 0);
//                board.add(new Hex(a2, b - height / 2, root));
//            }
//        }
////        board.add(new Hex(0,0,root));
//    }
//
//    Hex getHex(int q, int r) {
//        for (Hex hex : board) {
//            if (hex.compare(q, r)) return hex; }
//        return null;
//    }
//
//    void kruskal(int time) {
//        // Creating walls
//        ArrayList<Pair<Pair<Hex, Integer>,Pair<Hex, Integer>>> walls = new ArrayList<>();
//        for (Hex currentHex : board) {
//            for (Pair<Point2D,Integer> direction : Hex.directions) {
//                for (Hex hex : board) {
//                    int neighbourQ = (int) (currentHex.getCoordinates().getX() + direction.getKey().getX());
//                    int neighbourR = (int) (currentHex.getCoordinates().getY() + direction.getKey().getY());
//                    if (hex.compare(neighbourQ, neighbourR)) {
//                        Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall1 = new Pair<>(new Pair<>(hex, (direction.getValue() + 3) % 6), new Pair<>(currentHex, direction.getValue()));
//                        Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall2 = new Pair<>(new Pair<>(currentHex, direction.getValue()), new Pair<>(hex, (direction.getValue() + 3) % 6));
//                        if (!walls.contains(wall1) && !walls.contains(wall2)) { walls.add(wall1); }
//                    }
//                }
//            }
//        }
//
//        ArrayList<Line[]> wallLines = new ArrayList<>();
//        for (Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall : walls) {
//            Line[] line = {wall.getKey().getKey().getEdges()[wall.getKey().getValue()], wall.getValue().getKey().getEdges()[wall.getValue().getValue()]};
//            wallLines.add(line); }
//
//        //Creating sets of hexes
//        ArrayList<ArrayList<Hex>> sets = new ArrayList<>();
//        for (Hex hex : board) {
//            ArrayList<Hex> hs = new ArrayList<>();
//            hs.add(hex);
//            sets.add(hs);
//        }
//
//        EventHandler<ActionEvent> kruskalAlgorithm = actionEvent -> {
//            Pair<Pair<Hex, Integer>,Pair<Hex, Integer>> wall = walls.get(random.nextInt(walls.size()));
//            ArrayList<Hex> ws1 = null;
//            ArrayList<Hex> ws2 = null;
//            for (ArrayList<Hex> set : sets) {
//                if (set.contains(wall.getKey().getKey())) {
//                    ws1 = set;
//                    break; }
//            }
//            for (ArrayList<Hex> set : sets) {
//                if (set.contains(wall.getValue().getKey())) {
//                    ws2 = set;
//                    break; }
//            }
//            if (ws1 != ws2) {
//                ws1.addAll(ws2);
//                sets.remove(ws2);
//                wall.getKey().getKey().setFill(Color.rgb(144,224,239));
//                wall.getValue().getKey().setFill(Color.rgb(144,224,239));
//                wall.getKey().getKey().getEdges()[wall.getKey().getValue()].setVisible(false);
//                wall.getValue().getKey().getEdges()[wall.getValue().getValue()].setVisible(false);
//            }
//            walls.remove(wall);
//            System.out.println(walls.size());
//        };
//
//        KeyFrame kf = new KeyFrame(Duration.millis(time),kruskalAlgorithm);
//        Timeline tl = new Timeline(kf);
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.play();
//    }
//
//    void recursiveBacktracking(int time) {
//
//        ArrayList<Hex> unvisited = new ArrayList<>(board);
//        ArrayList<Hex> stack = new ArrayList<>();
//
//        currentHex = board.get(random.nextInt(board.size()));
//        unvisited.remove(currentHex);
//        Timeline tl = null;
//        Timeline finalTl = tl;
//        tl = new Timeline(new KeyFrame(Duration.millis(time), actionEvent -> {
//
//            ArrayList<Pair<Hex, Integer>> neighbours = new ArrayList<>();
//
//            for (Pair<Point2D, Integer> direction : Hex.directions) {
//                int neighbourQ = (int) (currentHex.getCoordinates().getX() + direction.getKey().getX());
//                int neighbourR = (int) (currentHex.getCoordinates().getY() + direction.getKey().getY());
//                Hex hex = getHex(neighbourQ, neighbourR);
//
//                if (unvisited.contains(hex)) {
//                    neighbours.add(new Pair<>(hex, direction.getValue()));
//                }
//            }
//
//            if (neighbours.size() > 0) {
//                Pair<Hex, Integer> randomNeighbour = neighbours.get(random.nextInt(neighbours.size()));
//                Hex nextHex = randomNeighbour.getKey();
//                int value = randomNeighbour.getValue();
//                stack.add(currentHex);
//
//                nextHex.updateWalls((value + 3) % 6);
//                currentHex.updateWalls(value);
//
//                currentHex.setFill(Color.rgb(202,240,248));
//                currentHex = nextHex;
//
//                unvisited.remove(currentHex); }
//
//            else if (stack.size() > 0) {
//                currentHex.setFill(Color.rgb(144,224,239));
//                currentHex = stack.get(stack.size() - 1);
//                stack.remove(stack.size() - 1); }
//        }));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.setOnFinished(actionEvent -> recursiveBacktracking(time));
//        tl.play();
//    }
//
//    public static void main(String[] args) { launch(args); }
//}