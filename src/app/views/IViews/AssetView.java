package app.views.IViews;

import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.views.IViews.AgentView;
import app.views.IViews.IView;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AssetView implements IView {
    private Pane myRoot;
    private Board myBoard;

    private List<AgentView> myAgentViewList;


    public AssetView(Board board){
        myRoot = new Pane();
        myBoard = board;
        myAgentViewList = new ArrayList<>();
        setRoot();
    }

    public List<AgentView> getMyAgentViewList(){
        return myAgentViewList;
    }

    private void setRoot(){
        var tempPane = new VBox();
        AgentView bankAgentView = new AgentView(myBoard.getBank());
        tempPane.getChildren().add(bankAgentView.getMyRoot());
        myAgentViewList.add(bankAgentView);
        Queue<Player> players = myBoard.getPlayers();
        for (Player player: players) {
            AgentView agentView = new AgentView(player);
            myAgentViewList.add(agentView);
            tempPane.getChildren().add(agentView.getMyRoot());
        }
        myRoot.getChildren().add(tempPane);
    }





    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
