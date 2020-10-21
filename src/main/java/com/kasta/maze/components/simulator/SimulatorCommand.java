package com.kasta.maze.components.simulator;

import com.kasta.app.command.Command;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);

    @Override
    default Class<SimulatorState> getStateClass() {
        return SimulatorState.class;
    }
}
