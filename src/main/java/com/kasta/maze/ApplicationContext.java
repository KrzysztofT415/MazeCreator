package com.kasta.maze;

import com.kasta.app.command.CommandExecutor;
import com.kasta.app.event.EventBus;
import com.kasta.app.state.StateRegistry;
import com.kasta.maze.view.MainView;

public class ApplicationContext {

    private final EventBus eventBus;
    private final CommandExecutor commandExecutor;
    private final StateRegistry stateRegistry;
    private final MainView mainView;

    public ApplicationContext(EventBus eventBus, CommandExecutor commandExecutor, StateRegistry stateRegistry, MainView mainView) {
        this.eventBus = eventBus;
        this.commandExecutor = commandExecutor;
        this.stateRegistry = stateRegistry;
        this.mainView = mainView;
    }

    public EventBus getEventBus() { return eventBus; }
    public CommandExecutor getCommandExecutor() { return commandExecutor; }
    public StateRegistry getStateRegistry() { return stateRegistry; }
    public MainView getMainView() { return mainView; }
}
