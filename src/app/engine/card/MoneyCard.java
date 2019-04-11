package app.engine.card;

import app.engine.agent.Player;

public class MoneyCard extends ImmediateCard {
    private double money;

    public MoneyCard(double value){
        this.money = value;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        // add/remove money to/from player
    }

    @Override
    public void useCard(Player currentOccupant) {

    }
}
