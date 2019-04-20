package app.engine.board;

import app.engine.agent.Player;
import app.engine.space.Property;

import java.util.List;

public class ButtonPermissions {
    private final Board board;

    public ButtonPermissions(Board gameBoard) {
        board = gameBoard;
    }

    public boolean canUseGetOutOfJailCard(Player player){
        return (player.findGetOutOfJailCard() != null);
    }

    public boolean canPayJailFee(Player player){
        return canPay(player, board.getRules().getJailFee());
    }

    public boolean canPay(Player player, double amount){
        return (player.getWallet()>=amount);
    }


    //    if player has rolled and the roll wasn't doubles
    public boolean canEndTurn(Player player){
        if (board.getLastRollArray() ==null){
            return false;
        }
        return !(board.isDoubles(board.getLastRollArray()) && board.getDoublesCounter() < board.getRules().getNumDoublesTilGoToJail());
    }

    //    This could be a Player method, but some of the other CanDoXX() methods can't be in player so for now I'm keeping them together
    public boolean canSell(Player player){
        return (!player.getProperties().isEmpty() || !player.getCards().isEmpty() || player.hasBuildings());
    }

    public boolean canMortgage(Player player){
        if (player.getProperties().isEmpty()){
            return false;
        }
        for (Property prop: player.getProperties()){
            if (!prop.isMortgaged()){
                return true;
            }
        }
        return false;
    }

    public boolean canUnmortgage(Player player){
        if (player.getProperties().isEmpty()){
            return false;
        }
        for (Property prop: player.getProperties()){
            if (prop.isMortgaged() && player.getWallet()>=prop.getUnmortgageValue()){
                return true;
            }
        }
        return false;
    }

    public boolean canRoll(Player player){
        if (board.getLastRollArray() == null){
            return true;
        }
//        MAGIC VALUE
        return (board.getDoublesCounter() > 0 && board.getDoublesCounter() < 3 && board.isDoubles(board.getLastRollArray()));
    }


    public boolean canBuy(Player player, List<? extends Property> prop){
        return (player.getWallet()>=prop.get(0).getPurchaseCost());
    }
}
