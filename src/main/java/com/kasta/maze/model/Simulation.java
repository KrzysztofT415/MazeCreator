package com.kasta.maze.model;

import com.kasta.maze.Log;
import com.kasta.maze.components.simulator.Simulator;
import com.kasta.maze.components.simulator.SimulatorEvent;
import com.kasta.maze.model.algorithms.MazeGeneratingAlgorithm;
import com.kasta.maze.view.boards.Board;

import java.util.concurrent.TimeoutException;

public class Simulation {

    private Simulator simulator;
    private Board board;
    private BoardCellStates simulationBoardCellStates;
    private MazeGeneratingAlgorithm algorithm;

    public Simulation(Simulator simulator, Board board, BoardCellStates simulationBoardCellStates, MazeGeneratingAlgorithm algorithm) {
        this.simulator = simulator;
        this.board = board;
        this.simulationBoardCellStates = simulationBoardCellStates;
        this.algorithm = algorithm;
    }

    public void step() {
        BoardCellStates nextState = simulationBoardCellStates.copy();

        try {
            algorithm.step(board);
        }
        catch (TimeoutException e) {
            Log.log("Visualization ended");
            this.simulator.handle(new SimulatorEvent(SimulatorEvent.Type.STOP));
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.simulationBoardCellStates = nextState;
    }

    public BoardCellStates getBoard() {
        return simulationBoardCellStates;
    }
}
