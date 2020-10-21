package com.kasta.maze.view;

import com.kasta.app.event.EventBus;
import com.kasta.maze.components.editor.BoardEvent;
import com.kasta.maze.model.CellPosition;
import com.kasta.maze.view.boards.Board;
import com.kasta.maze.view.boards.HexBoard;
import com.kasta.maze.view.cells.Cell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BoardPane extends Pane {

    private final EventBus eventBus;
    private final List<DrawLayer> drawLayers = new LinkedList<>();
    private Board board;

    public BoardPane(EventBus eventBus) {
        this.eventBus = eventBus;

        this.board = new HexBoard(this);

        this.setOnMousePressed(this::handlePressed);
        this.setOnMouseReleased(this::handleReleased);
        this.setOnMouseDragged(this::handleCursorMoved);
        this.setOnMouseMoved(this::handleCursorMoved);
    }

    public void addDrawLayer(DrawLayer drawLayer) {
        drawLayers.add(drawLayer);
        drawLayers.sort(Comparator.comparingInt(DrawLayer::getLayer));
        drawLayer.addInvalidationListener(this::draw);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        draw();
    }

    private void handleCursorMoved(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        if (cursorPosition != null) {
            BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.CURSOR_MOVED, cursorPosition);
            eventBus.emit(boardEvent);
        }
    }

    private void handlePressed(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        if (cursorPosition != null) {
            BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.PRESSED, cursorPosition);
            eventBus.emit(boardEvent);
        }
    }

    private void handleReleased(MouseEvent event) {
        CellPosition cursorPosition = this.getSimulationCoordinates(event);
        if (cursorPosition != null) {
            BoardEvent boardEvent = new BoardEvent(BoardEvent.Type.RELEASED, cursorPosition);
            eventBus.emit(boardEvent);
        }
    }

    private CellPosition getSimulationCoordinates(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        for (Cell[] row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.contain((int) mouseX, (int) mouseY)) {
                    return new CellPosition(cell.getGridCoordinates().getX(), cell.getGridCoordinates().getY());
                }
            }
        }
        return null;
    }

    private void draw() {
        for (Cell[] row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getFil() != cell.getState().getInsideColor()) { cell.setState(cell.getState(), true); }
            }
        }

        for (DrawLayer drawLayer : drawLayers) {
            drawLayer.draw(board);
        }

    }

    public void setBoard(Board board) { this.board = board; }
    public int[] getBoardDimensions() { return this.board.getDimensions(); }
    public Board get() { return board; }
}
