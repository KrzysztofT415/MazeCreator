package com.kasta.maze.components.editor;

import com.kasta.maze.ApplicationComponent;
import com.kasta.maze.ApplicationContext;
import com.kasta.maze.Log;
import com.kasta.maze.components.board.BoardState;
import com.kasta.maze.components.simulator.SimulatorEvent;
import com.kasta.maze.model.BoardCellStates;

public class EditorApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Editor editor = new Editor(editorState, context.getCommandExecutor());
        context.getEventBus().listenFor(DrawModeEvent.class, editor::handle);
        context.getEventBus().listenFor(BoardEvent.class, editor::handle);
        context.getEventBus().listenFor(SimulatorEvent.class, editor::handleSimulatorEvent);

        context.getEventBus().listenFor(SimulatorEvent.class, event -> {
            if (event.getEventType() == SimulatorEvent.Type.RESET) {
                boardState.getBoard().set(editorState.getEditorBoard().get());
            }
        });

        editorState.getEditorBoard().listen(boardState.getBoard()::set);

        ToolDrawLayer toolDrawLayer = new ToolDrawLayer(editorState);
        context.getMainView().addDrawLayer(toolDrawLayer);

        CurrentEditDrawLayer editDrawLayer = new CurrentEditDrawLayer(editorState);
        context.getMainView().addDrawLayer(editDrawLayer);
    }

    @Override
    public void initState(ApplicationContext context) {
        BoardCellStates boardState = new BoardCellStates(context.getMainView().getBoardDimensions());
        EditorState editorState = new EditorState(boardState);
        context.getStateRegistry().registerState(EditorState.class, editorState);
        Log.log("EditorApplicationComponent initiated successfully");
    }
}
