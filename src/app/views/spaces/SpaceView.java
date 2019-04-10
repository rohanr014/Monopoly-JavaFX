package app.views.spaces;

import app.engine.space.ISpaceObserver;
import javafx.scene.layout.Pane;

public abstract class SpaceView implements ISpaceObserver {
    private Pane root;

    public abstract Pane initialize();
    public Pane root() { return root; }
}