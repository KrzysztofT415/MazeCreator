module com.kasta {
    requires javafx.controls;
    exports com.kasta.app.command;
    exports com.kasta.app.event;
    exports com.kasta.app.observable;
    exports com.kasta.app.state;
    exports com.kasta.maze.components.board;
    exports com.kasta.maze.components.editor;
    exports com.kasta.maze.components.infobar;
    exports com.kasta.maze.components.simulator;
    exports com.kasta.maze.model;
    exports com.kasta.maze.model.algorithms;
    exports com.kasta.maze;
    exports com.kasta.maze.view;
    exports com.kasta.maze.view.boards;
    exports com.kasta.maze.view.cells;
}