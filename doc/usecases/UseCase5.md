### Use Case 5
How to handle end game (will we have a give up/drop out button and how to detect that. All but one player is bankrupt)
- We will have an activePlayers list that contains all the players who are still in the game
- When a player is completely bankrupt they will be removed from the list (so they won’t get another turn, this list controls the turns)
- Our game will be based on the idea of while activePlayers.size() > 1 {} and if that condition is ever broken then we know we have a winner.