package app.engine.space;

import app.engine.agent.Player;
import app.engine.card.Card;

import java.util.Queue;
public class CardSpace extends Space {
    private Queue<Card> cardType;
    private String imageName;


    public CardSpace(String name, Queue<Card> whichPile) {
        super(name);
        cardType = whichPile;
    }

    public CardSpace(String name, Queue<Card> whichPile, String imageName){
        this(name, whichPile);
        this.imageName = imageName;
    }

    @Override
    protected void invokeAction(Player occupant) {
        getBoard().drawCard(occupant, cardType);

    }

    public Queue<Card> getCardType() {
        return cardType;
    }

    public String getImageName() {
        return imageName;
    }
}