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
import java.util.Queue;

public class AssetView implements IView {
    private Pane myRoot;
    private Board myBoard;

    public AssetView(Board board){
        myRoot = new Pane();
        myBoard = board;
        setRoot();
    }

    private void setRoot(){
        var tempPane = new VBox();
        tempPane.getChildren().add(new AgentView(myBoard.getBank()).getMyRoot());
        Queue<Player> players = myBoard.getPlayers();
        for (Player player: players) {
            tempPane.getChildren().add(new AgentView(player).getMyRoot());
        }
        myRoot.getChildren().add(tempPane);
    }



    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
