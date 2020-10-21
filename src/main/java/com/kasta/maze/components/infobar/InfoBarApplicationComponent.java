package com.kasta.maze.components.infobar;

import com.kasta.maze.ApplicationComponent;
import com.kasta.maze.ApplicationContext;
import com.kasta.maze.components.editor.EditorState;

public class InfoBarApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);

        InfoBarViewModel viewModel = new InfoBarViewModel();
        editorState.getCursorPosition().listen(viewModel.getCursorPosition()::set);
        editorState.getDrawMode().listen(viewModel.getCurrentDrawMode()::set);

        InfoBar infoBar = new InfoBar(viewModel);
        context.getMainView().setBottom(infoBar);
    }

    @Override
    public void initState(ApplicationContext context) {}
}
