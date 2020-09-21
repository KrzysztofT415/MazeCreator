package com.kasta;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class MainView extends VBox {

    private final Toolbar toolbar;
    private final Board board;
    private InfoBar infoBar;

    private HexState drawMode;

    public MainView() {

        this.toolbar = new Toolbar(this);
        this.board = new Board();
        this.board.addEventFilter(MouseEvent.DRAG_DETECTED , mouseEvent -> {
            this.board.startFullDrag();
            this.board.setEditionMode(drawMode);
        });

        this.drawMode = HexState.WALL;

        this.infoBar = new InfoBar();
        this.infoBar.setDrawMode(HexState.WALL);
        this.infoBar.setCursorPosition(0,0);

        Pane spacer = new Pane();
        spacer.setMinSize(0,0);
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(this.toolbar, this.board, spacer , this.infoBar);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(144,224,239), null, null)));
    }

    private void handleMoved(MouseEvent mouseEvent) {
    }

    public Board getBoard() { return board; }

    public void setDrawMode(HexState drawMode) {
        this.drawMode = drawMode;
        this.infoBar.setDrawMode(drawMode);
    }
}
