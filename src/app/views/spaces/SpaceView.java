package app.views.spaces;

import app.engine.space.ISpaceObserver;
import javafx.scene.layout.Pane;

public abstract class SpaceView implements ISpaceObserver {
    private Pane myRoot;
    protected String myName;

    public abstract Pane initialize();
    public Pane getMyRoot(){return myRoot;}
    public String getMyName(){return myName;}
}