package com.kasta;

import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;

public class MainView extends HBox {

    private final Button stepButton, startButton;
    private final Board board;

    public MainView() {

        int width = (int) (Screen.getPrimary().getBounds().getWidth() / (Hex.getRadius() * 2));
        width = width + width % 2 + 1;
        int height = (int) (Screen.getPrimary().getBounds().getHeight() / (Hex.getRadius() * Math.sqrt(3)));
        height = height - height % 2 - 1;
        this.board = new Board(width, height);
        this.board.setOnMousePressed(this::handleDraw);

        this.startButton = new Button("start");
        this.startButton.setOnAction(actionEvent -> this.board.start());
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(actionEvent -> this.board.step());

        this.getChildren().addAll(this.startButton, this.stepButton, this.board);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(144,224,239), null, null)));
    }

    private void handleDraw(MouseEvent mouseEvent) {
        for (int x = 0; x < this.board.width; ++x) {
            for (int y = 0; y < this.board.height; ++y) {
                this.board.board[x][y].setOnMouseEntered(this.board.board[x][y]::handleDraw);
            }
        }
    }
}
