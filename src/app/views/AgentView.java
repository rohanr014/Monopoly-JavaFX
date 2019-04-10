package app.views;

import app.engine.agent.IAgentObserver;
import app.engine.board.Board;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AgentView implements IAgentObserver, IView {
    private Pane myRoot;

    public AgentView(){
        myRoot = new Pane();
        setRoot();
    }

    private void setRoot(){
        var tempPane = new HBox();
        myRoot.getChildren().add(new Text("hi"));
    }


    @Override
    public void agentUpdate(double wallet, Board board) {//why does this need board as an input?

    }

    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
