package app.views.IViews;

import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.IAgentObserver;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.views.popups.PlayerDetailsView;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class AgentView implements IAgentObserver, IView {
    private Pane myRoot;
    private double myCash;
    private String myName;
    private int myHotelNum;
    private int myHouseNum;
    private String myPiece;
    protected Player myPlayer;
    private LogHistoryView myLogHistoryView;
    private Bank myBank;

    private VBox vbox;
    private HBox hbox1;
    private HBox hbox2;

    private Text nameText;
    private Text cashText;

    private Text hotelText;
    private Text houseText;

    public AgentView(Player agent, LogHistoryView logHistoryView){//take in correct paramaters
        myPlayer = agent;
        setMyCash(myPlayer.getWallet());
        myLogHistoryView = logHistoryView;
        initializePlayer();

    }

    public AgentView(Bank bank, LogHistoryView logHistoryView){//take in correct paramaters
        myBank = bank;
        setMyCash(myBank.getWallet());
        myLogHistoryView = logHistoryView;
        initializeBank();
    }

    private void setPlayerRoot(){
        vbox = new VBox();
        hbox1 = new HBox();
        hbox2 = new HBox();
        nameText = new Text(myPlayer.getName());
        hbox1.getChildren().add(nameText);
        //add "piece: "+piece image
        cashText = new Text("$" + Integer.toString((int) myCash) );
        hbox2.getChildren().add(cashText);
        Button info = new Button();
        info.setOnAction(e-> new PlayerDetailsView(myPlayer));
        hbox1.getChildren().add(info);
        hotelText = new Text("Number of Hotels : " + Integer.toString(myHotelNum));
        houseText = new Text("Number of Houses : " + Integer.toString(myHouseNum));
        hbox2.getChildren().add(hotelText);
        hbox2.getChildren().add(houseText);
        vbox.getChildren().add(hbox1);
        vbox.getChildren().add(hbox2);
        vbox.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        myRoot.getChildren().add(vbox);

    }

    private void initializePlayer(){
        initialize();
        setPlayerRoot();
    }

    private void initializeBank(){
        initialize();
        setBankRoot();
    }

    private void initialize(){
        myRoot = new Pane();
    }

    private void setBankRoot(){
        var tempPane = new VBox();
        tempPane.getChildren().add(new Text("Bank"));
        tempPane.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        myRoot.getChildren().add(tempPane);
    }



    public double getMyCash() { return myCash; }

    public void setMyCash(double cash) { this.myCash = cash; }

    @Override
    public void agentUpdate(String logAction) {
        myLogHistoryView.addTransactionLog(logAction);
        if (myPlayer!=null) {
            setMyCash(myPlayer.getWallet());
        } else if (myBank!=null){
            setMyCash(myBank.getWallet());
        }
        updateCashText();

    }

    private void updateCashText(){
        if (myBank != null){
            return;
        }

        hbox2.getChildren().removeAll(cashText,hotelText,houseText);
        cashText = new Text("$" + Integer.toString((int) myCash) );
        hbox2.getChildren().add(cashText);
        hotelText = new Text("Number of Hotels : " + Integer.toString(myHotelNum));
        houseText = new Text("Number of Houses : " + Integer.toString(myHouseNum));
        hbox2.getChildren().addAll(hotelText,houseText);
    }



    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
