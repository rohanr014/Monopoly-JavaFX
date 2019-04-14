package app.views.spaces;

import app.engine.space.ISpaceObserver;
import app.engine.space.Space;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class SpaceView<M extends Space> implements ISpaceObserver {
    protected Pane myRoot;
    protected String myName;
    protected M myModel;

    public SpaceView(String name, M model) {
        myName = name;
        myModel = model;
        initialize();
        myRoot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public abstract void initialize();
    public abstract void adjustSize();

    public Pane getMyRoot(){return myRoot;}
    public String getMyName(){return myName;}
}