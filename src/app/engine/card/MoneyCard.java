package app.engine.card;

import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.board.Board;

public class MoneyCard extends Card {
    private double amount;

    public MoneyCard(String desc, Board b, double value){
        super(desc, b);
        this.amount = value;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        Bank bank = getBoard().getBank();

        if(amount >= 0){
            bank.giveMoney(currentOccupant, amount);
        }
        else{
            currentOccupant.giveMoney(bank, amount*-1);
        }

        putSelfBackInPile();
    }
}
