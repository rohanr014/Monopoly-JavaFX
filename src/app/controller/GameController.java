package app.controller;

import app.engine.agent.Agent;
import app.engine.board.Board;
import app.engine.space.Space;
import app.views.IViews.AgentView;
import app.views.IViews.MainView;
import app.views.spaces.SpaceView;

import java.util.List;

public class GameController {
    private Board myBoard;
    private MainView myMainView;

    //need to add observers from view to board
    //add space observers to space object

    //roll dice,


    public GameController(Board board, MainView mainView) {
        myBoard = board;
        myMainView = mainView;
        registerBoardObservers();
        registerDiceObservers();
        registerSpaceObservers();
        registerAgentObservers();
        initialize();
    }

    public void initialize(){
        myBoard.startTurn();
    }

    private void registerAgentObservers(){
        List<AgentView> agentViewList = myMainView.getMyAssetView().getMyAgentViewList();
        List<Agent>  agentList = myBoard.getMyAgentList();

        for(int i= 0; i<agentViewList.size();i++){
            agentList.get(i).addAgentObserver(agentViewList.get(i));
        }
    }

    public void registerDiceObservers(){
        myBoard.addDiceObserver(myMainView.getMyControlView());
    }

    private void registerSpaceObservers(){
        List<SpaceView> tempView = myMainView.getMyVanillaBoardView().getMySpaceViews();
        List<Space> tempSpace = myBoard.getSpaces();
        for(int i = 0; i<tempView.size(); i++){
            tempSpace.get(i).addSpaceObserver(tempView.get(i));
        }

    }


    private void registerBoardObservers(){
        myBoard.addBoardObserver(myMainView.getMyVanillaBoardView());
        myBoard.addBoardObserver(myMainView.getMyControlView());

    }


}
