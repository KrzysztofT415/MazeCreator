package com.kasta.maze.view;

import com.kasta.app.command.CommandExecutor;
import com.kasta.app.event.EventBus;
import com.kasta.maze.components.editor.DrawModeEvent;
import com.kasta.maze.model.CellState;
import com.kasta.maze.view.boards.Board;
import com.kasta.maze.view.boards.HexBoard;
import com.kasta.maze.view.boards.SquareBoard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

    private final EventBus eventBus;
    private final CommandExecutor commandExecutor;
    private final BoardPane board;
    private final Toolbar toolbar;

    public MainView(EventBus eventBus, CommandExecutor commandExecutor) {
        this.eventBus = eventBus;
        this.commandExecutor = commandExecutor;

        board = new BoardPane(eventBus);
        this.setCenter(board);

        toolbar = new Toolbar(eventBus);
        this.setTop(toolbar);

        this.setOnKeyPressed(this::onKeyPressed);
        this.setStyle("-fx-background-color: #90E0EF");
    }

    public void addDrawLayer(DrawLayer drawLayer) { board.addDrawLayer(drawLayer); }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.eventBus.emit(new DrawModeEvent(CellState.WALL));
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.eventBus.emit(new DrawModeEvent(CellState.EMPTY));
        } else if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.Z) {
            commandExecutor.undo();
        } else if (keyEvent.getCode() == KeyCode.H) {
            this.board.setBoard(new HexBoard(this.board));
        } else if (keyEvent.getCode() == KeyCode.S) {
            this.board.setBoard(new SquareBoard(this.board));
        }
    }

    public int[] getBoardDimensions() { return this.board.getBoardDimensions(); }

    public Board getBoard() { return this.board.get(); }
}
