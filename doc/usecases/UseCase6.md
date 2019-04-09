### Use Case 6
- The process of drawing card from the top of the deck and when used they are put back to the bottom of the deck.
- The card “deck” will be represented using a List.
- The card will be removed from the head
    - card = chanceCards.get(0);
    - chanceCards.remove(0);
- Then logic will happen depending on the action of the card
- Then there will be a call to add it back to the end of the list
    - chanceCards.add(card);