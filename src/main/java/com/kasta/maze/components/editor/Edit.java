package com.kasta.maze.components.editor;

import java.util.HashSet;

public class Edit extends HashSet<Change> {

    public Edit() {

    }

    public Edit(Edit edit) {
        super(edit);
    }

}
