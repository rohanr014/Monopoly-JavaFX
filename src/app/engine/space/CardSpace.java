package app.engine.space;

import app.engine.agent.Player;
import app.engine.card.Card;

import java.util.Queue;
public class CardSpace extends Space {
    private Queue<Card> cardType;

    public CardSpace(String name, Queue<Card> cardType) {
        super(name);
        this.cardType = cardType;
    }

    @Override
    protected void invokeAction(Player occupant) {
        getBoard().drawCard(occupant, cardType);
    }

    public Queue<Card> getCardType() {
        return cardType;
    }
}