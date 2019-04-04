# Introduction
**Primary design goals of the project (i.e., where is it most flexible)**
The primary design goal of our project is to be able to implement a basic game of Monopoly in such a way that we can
easily expand, build upon, or modify any of the aspects in our game that a player would want to change. For instance,
in addition to being able to customize cosmetic options and the names/appearances of properties, we also want to allow
players to change various rules to fit their preferences, such as limitless cash and housing, doubling the reward for landing
on Go, or even playing with auctions or not. Put simply, we want to make anything that a player would want to change to be
flexible, so that we can implement as many different kinds of games as possible.

**Primary architecture of the design (i.e., what is closed and what is open)**
The fundamental parts of the game that are common to all Monopoly games will most likely remain closed throughout our
development process (like Wallet and Dice), as will the many abstract classes that we will build to make variations 
of the game that we write early on (like Engine.Property and Engine.Space). Along those same lines, the only open components will be
the larger classes that will be responsible for managing newer features like Engine.Board and Engine.Player, as we start to add interactions
like trading and auctioning, as we would need added functionality to handle those. Whether we decide to add that to existing
classes or to make new classes that take care of those new features, we will have to change certain classes that manage
the interactions between certain pairs of entities in order to accommodate those new features.

---

# Introduction (cont)
**Discuss the design at a high-level (i.e., without referencing specific classes, data structures, or code).**
To start our design out, we plan to implement just about everything abstractly, which will give us a good sense on what should
be able to be customized/changed for different games, and what will remain constant. Doing so will also allow us to organize
our hierarchies early on, which is definitely going to be essential as the project grows and more features are added. We
plan to use an MVC approach as we did for the Simulation project, in which we have a game engine that will handle the 
interactions between different objects and the resulting changes in game state/data, a view in which users will directly
interact with the game to prompt those changes, and a controller to delegate actions from the user to the model. The game
engine will consist of both abstract and concrete classes and will be the backbone of the project. It will contain the
components that will advance the game as well as the ones that will be responsible for producing different playable
variations of the game. The view will be built on top of the abstractions defined by these classes to make sure it is
as flexible as possible and so that the use of new instances that extend our abstract classes will not require changes to
the view in order to change our game.

---

# Overview
**Map of your design for other programmers to gain a general understanding of how and why the program was divided up, and 
how the individual parts work together to provide the desired functionality.**
The biggest/main class of our model is the Engine.Board class, which will contain the players playing the game (including the
bank, which we are treating as a player since they too are an entity that is simply dishing out money to others), the Engine.Space
objects, and the Engine.Card objects. The board will be responsible for moving the different players and holding their positions.
Each player will possess a wallet variable to keep track of their money and handle monetary transactions, as well as a set of
assets that will store the properties they own and any other holdable cards they may possess, like get out of jail free.
There will be two kinds of spaces - properties, and common spaces. Properties can either
be SetProps (like Railroads and Utilities, which can't be built upon and the rent that is charged to them depends heavily
upon how many are owned), and ColorProps (all the other properties in the game that can be built upon). The other kind
of Engine.Space is Engine.CommonSpace, which will represent all the un-ownable spaces on the board, like Chance, Community Chest,
Super Tax, and Go. 

In order to create different game variations, we will also use various Properties files to easily customize the readable
values of different aspects of the game, like names of properties for the board, the image files for pieces, and different
properties files for different combinations of rules. Each theme of Monopoly will have an appropriate set of properties, 
game pieces, and community chest/chance cards, and when the player decides to make a new game, they will be shown a 
series of options they can pick for different rules. According to their selection, a new properties file will be made with 
the specific rules policies and will be fed into the game on startup.


**Include a picture of how the modules are related (these pictures can be hand drawn and scanned in, created with a standard 
drawing program, or screen shots from a UML design program).**
https://drive.google.com/file/d/1oFcJ97zcJgBx959OACngZ-PsGC_90v-J/view?usp=sharing
 
**Discuss specific classes, methods, and data structures, but not individual lines of code.**
As was said before, the board class will be responsible or moving the player and handling the changing of turns between
players. It will have a callNextPlayer() function to change whoever's turn it is, a move(Engine.Player p, Engine.Space s) function to
move a player to a specific state, and a move(Engine.Player p, int steps) function to move a player a specified number of spaces
to be used after the player rolls their dice. When a player lands on a space, the Engine.Space object will call an onLand(Engine.Player p)
function to call a set of actions on that player which will change from space to space. For instance, if it is a property
owned by another player, onLand will call that player's giveMoneyTo(double m, Engine.Agent a) function to give money to the owner
of that property. Each card that can get picked up will also invoke an action on the player that picks it up. 

---

# User Interface
**This section describes what components the user will interact with in your program (keep it simple to start) and how a game 
is represented to the designer and what support is provided to make it easy to create a game.**

Initially, user will be able to:
* Roll Dice
* Mortgage/Unmortgage property
* Buy/sell assets to/from the bank
* Build on property
* End Turn

The game is primarily interacted with through the Engine.Board class, which has instances of every object in the 
game as state variables.

It should be relatively easy on the View end to support creating a game because the view elements should largely stay the same beyond
possibly additional buttons for the user to interact with. On the model end, we've taken pains to make everything quite modular, so if, say
there's a new type of Engine.Space that has different functions it's very easy to slot in a new class in the hierarchy.

 
**Include design goals for the implementation of the UI that shows the kind of abstractions you plan to build on top of OpenJFX's 
standard components and how they can be flexibly exchanged within the GUI itself**

We plan to use the composite and observer/observable patterns which will allow a lot of GUI flexibility. The leaves of the
composite will be the various elements view (buttons/map/info panels/etc), which will allow us flexibility in manipulting
both some and all of the elements. The observer (view) and observable (model) cleanly delineates roles and gives us a
framework to implement a view of the game engine.

**Describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.).**
There should be no situations in which there is bad input data or empty data, as the resource files will be pre-written 
for each particular game type--the user will not be able to make errors. Certainly though, there will be checks for if
the data file is incorrectly written for the game type or if a saved properties file is created incorrectly. In model, 
we have instituted boolean return types instead of error-checking, which will function in similar ways (if the player
tries to buy something but doesn't have enough money, the buy method will fail and return false)

---

# Design Details 
**This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed 
but are not significant to include in a high-level description of the program)**

Engine.Board - contains the players playing the game (including bank, which we are treating as a player since they too are an 
entity that is simply dishing out money to others) as well as the game's dice, the Engine.Space and Engine.Card objects. The board
is primarily responsible for moving players, holding their positions, and regulating turn order.

Engine.Card - (abstract) Engine.Card that invokes an action                

Engine.ImmediateCard - Invokes an action immediately when drawn

Engine.HoldableCard - can be stored and used later

Engine.Space - (Abstract) Superclass for all spaces on board 

Engine.CommonSpace - Spaces like Chance/CommunityChest/Jail/FreeParking, etc that are not properties

---

# Design Details (cont.)

Engine.Property - Superclass for all types of properties

Engine.SetProperty - Class for Utilities and Railroads, properties who's rent is a function of how many of the set the player owns

Engine.ColorProperty - Subclass of Engine.SetProperty, consists of the colored properties that can be built on once a monopoly is reached

Engine.Agent - abstract class for all game agents. May eventually include CPUs

Engine.Bank - Essentially a player with a wallet who never acts

Engine.Player - Engine.Player of game with full functionality to play (buy/sell, mortgage, build, everything)

---

# Design Details (cont.)

**Describe how each module handles specific features given in the assignment specification, what resources it might use, how 
it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment 
specification or discussed by your team). Note, each sub-team should have its own API(s).**


Funds - Handled in Engine.Agent classes, accessible only through agents
Dice - Handled in Engine.Board, extendable through Boards' interpretations of RNG
Data - Hierarchy of resources files: Master file -> Rules, Properties, TokenTypes

**Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions.**

Currently it's only divided into the most basic modules of View, Controller, and Model. This is sufficient for us right
now but may change.

---

# Example games
**Describe three example games in detail that differ significantly. Clearly identify how the functional differences in these 
games is supported by your design. Use these examples to help clarify the abstractions in your design.**
Each game mode will differ from others in two primary ways: the rules ResourceBundle used for the game and the theme of that
game (which will be shown in the properties ResourceBundle, the game piece ResourceBundle, and the Chance/Community Chest
cards ResourceBundles).
One game will be the vanilla, standard Monopoly game. This game will feature a list of properties, game pieces, and chance/
community chest cards from the standard monopoly game we all know and love.
Another game can be vanilla monopoly, but with rules modifications such as all the properties being dealt on start, double
reward for landing on go, and auctions. Cosmetically, this wouldn't be any different from the first version, but the game
play itself would be very different, as the properties file generated for this set of rules would suggest.
A third game would be Golf Monopoly, a not-so-popular-yet-enjoyable version of the game where the properties are famous
golf courses and the chance/community chest cards are based on the game of golf, like hitting a ball into the clubhouse
or having your set robbed while you went to the bathroom. If this game were played with vanilla rules, the game play and
interactions between different elements in the model would practically be exactly the same as in the first example in this
response, but cosmetically, the game would look very different.

---

# Design Considerations 
**This section describes any issues which need to be addressed or resolved before attempting to devise a complete design 
solution.**

None that we can think of. More work needs to go into View for sure, but there are no particular issues other than general
planning that is needed.

**Include any design decisions that each sub-team discussed at length (include pros and cons from all sides of the discussion) 
as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.**

We decided not to have Agents' wallets be a separate class because the encapsulation benefits weren't enough to justify
certain design headaches regarding the Engine.Player's access to and knowledge of their own money.

In View, we decided to put all game configuration on the game screen (Save, Load, Sound on, etc) into a seperate popup
menu, so that everything the user interacts with on the Game Screen has a direct impact on the play of the game. Seemed 
cleaner.

NOTE: Sample data files in data folder
