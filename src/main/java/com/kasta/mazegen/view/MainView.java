package com.kasta.mazegen.view;

import com.kasta.mazegen.components.infobar.InfoBar;
import com.kasta.mazegen.model.*;
import com.kasta.mazegen.model.boards.Board;
import com.kasta.mazegen.model.boards.SquareBoard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainView extends VBox {

    private final Toolbar toolbar;
    private final Board board;
    private final InfoBar infoBar;

    private final Visualization visualization;

    private CellState drawMode;

    public MainView() {

        this.toolbar = new Toolbar(this);
        this.board = new SquareBoard(this);
        this.board.addEventFilter(MouseEvent.DRAG_DETECTED , mouseEvent -> {
            this.board.setEditionMode(drawMode);
            this.board.startFullDrag();
        });

        this.visualization = new Visualization(this.board);

        this.drawMode = CellState.WALL;

        this.infoBar = new InfoBar();
        this.infoBar.setDrawMode(CellState.WALL);
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
    public Visualization getVisualization() { return visualization; }

    public void setDrawMode(CellState drawMode) {
        this.drawMode = drawMode;
        this.infoBar.setDrawMode(drawMode);
    }
}
