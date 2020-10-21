package com.kasta.maze;

import com.kasta.app.command.CommandExecutor;
import com.kasta.app.event.EventBus;
import com.kasta.app.state.StateRegistry;
import com.kasta.maze.components.board.BoardApplicationComponent;
import com.kasta.maze.components.editor.EditorApplicationComponent;
import com.kasta.maze.components.infobar.InfoBarApplicationComponent;
import com.kasta.maze.components.simulator.SimulatorApplicationComponent;
import com.kasta.maze.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Log.log("Starting Application");
        stage.initStyle(StageStyle.UNDECORATED);

        EventBus eventBus = new EventBus();
        StateRegistry stateRegistry = new StateRegistry();
        CommandExecutor commandExecutor = new CommandExecutor(stateRegistry);

        MainView mainView = new MainView(eventBus, commandExecutor);

        ApplicationContext context = new ApplicationContext(eventBus, commandExecutor, stateRegistry, mainView);

        List<ApplicationComponent> components = new LinkedList<>();
        components.add(new EditorApplicationComponent());
        components.add(new SimulatorApplicationComponent());
        components.add(new BoardApplicationComponent());
        components.add(new InfoBarApplicationComponent());

        for (ApplicationComponent component : components) { component.initState(context); }
        for (ApplicationComponent component : components) { component.initComponent(context); }

        Scene scene;
        scene = new Scene(mainView, Screen.getPrimary().getBounds().getWidth() - Log.getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        Log.log("Application started successfully");
    }

    public static void main(String[] args) { launch(args); }
}
