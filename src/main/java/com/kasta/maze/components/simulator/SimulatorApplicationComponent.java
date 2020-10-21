package com.kasta.maze.components.simulator;

import com.kasta.maze.ApplicationComponent;
import com.kasta.maze.ApplicationContext;
import com.kasta.maze.components.board.BoardState;
import com.kasta.maze.components.editor.EditorState;
import com.kasta.maze.model.BoardCellStates;

public class SimulatorApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        SimulatorState simulatorState = context.getStateRegistry().getState(SimulatorState.class);
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Simulator simulator = new Simulator(simulatorState, context.getCommandExecutor(), context.getMainView());
        context.getEventBus().listenFor(SimulatorEvent.class, simulator::handle);

        editorState.getEditorBoard().listen(simulatorState.getBoard()::set);

        simulatorState.getBoard().listen(simulationBoard -> {
            if (simulatorState.getSimulating().get()) {
                boardState.getBoard().set(simulationBoard);
            }
        });
    }

    @Override
    public void initState(ApplicationContext context) {
        BoardCellStates boardState = new BoardCellStates(context.getMainView().getBoardDimensions());
        SimulatorState simulatorState = new SimulatorState(boardState);
        context.getStateRegistry().registerState(SimulatorState.class, simulatorState);
    }
}
