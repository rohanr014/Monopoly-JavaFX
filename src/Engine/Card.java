package Engine;

public abstract class Card{

    public Card(){

    }

    /**
     * Function for card to invoke correct action on player
     */

    public abstract void invokeAction(Player currentOccupant);

    /**
     * Function for player to use card (like get out of jail free)
     */

    public abstract void useCard(Player currentOccupant);



}