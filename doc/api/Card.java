public interface Card{

    /**
     * Function for card to invoke correct action on player
     */

    public void invokeAction(Player currentOccupant);

    /**
     * Function for player to use card (like get out of jail free)
     */

    public void useCard(Player currentOccupant);



}