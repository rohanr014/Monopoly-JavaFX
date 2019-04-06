**A player lands on Chance, draws "Engine.Bank pays you a dividend of $50", your funds are updated appropriately, and the card is returned to the bottom of the deck.**

Engine.Board moves player, board calls the space that the player lands on, calls board to draw the card from a collection of cards, the drawn card object performs transaction of giving $50 to the player, last part of Engine.Board.draw() would be to put the card object back at the bottom of the stack.

**A player lands on Chance, draws "Advance to Go, collect $200", your token is moved and your funds are updated appropriately, and the card is returned to the bottom of the deck.**

After the board object moves the player to chance, the board object will call chanceSpace.invokeAction(), which will call Engine.Board.drawChance() function to draw a chance card from its collection of Engine.Card objects (ChanceCards). The card will then use the Engine.Board.movePlayer(Engine.Player p, ) to move the player to Go, and Go is, with regular rules, the one ?vanilla? space that doesn?t hold anything. Rather, once the player has gone to Go, the board object will see that the move() function?s final destination is either at or after Go, so the move will also take care of the special case when the player gets moved to or after Go, and give the player $200.

**A player lands on Chance, draws "Get Out of Jail Free", and it is saved with your inventory, and the card is returned to the bottom of the deck.**

Engine.Board moves player, board says that player lands on space. Chance is drawn, and ?get out of jail free? card object is returned. ?Get out of jail free? attribute is added to player?s assets. Assets can be drawn upon in future turns. Last part of board.draw() puts the card back to the bottom of the deck.

**A player lands on Chance, draws "Go to Jail, Go directly to Jail", your token is moved (passing Go, but not collecting any money) and placed in jail, and the card is returned to the bottom of the deck.**

Engine.Board moves Engine.Player onto Chance. Engine.Board calls Chance?s actions. Chance draws() Engine.Card from Engine.Board. Engine.Card?s invokeAction() calls Engine.Board to move Engine.Player into jail. At the end of Engine.Board?s draw(), the card is returned to the bottom of the deck.

**A new rule is added that lets a player draw a Chance card when landing on Free Parking space.**

Nothing changes except that we?d have to add the logic of free parking to basically be classified as a chance card space. We set the action to draw chance card.

**A new theme for the game is designed, creating cards in a different language.**

Engine.Board moves Engine.Player onto Chance. Engine.Board calls Chance actions. Chance draws Engine.Card from Engine.Board. Chance executes Engine.Card Rules. This execution will access all Cards through board and change the property file from which they get their Names/Instructions
