package app.views;

import app.engine.agent.IAgentObserver;
import javafx.scene.layout.Pane;

public class AgentView implements IAgentObserver, IView {
    private Pane myRoot;

    public AgentView(){
        myRoot = new Pane();
    }

    @Override
    public void agentUpdate() {

    }

    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
