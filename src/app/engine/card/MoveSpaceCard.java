package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.space.Space;

import java.util.List;

public class MoveSpaceCard extends Card {
    private String destinationName;
    private String ds;

    public MoveSpaceCard(String desc, Board b, String spaceName){
        super(desc, b);
        ds = desc;

        this.destinationName = spaceName;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        Board b = getBoard();

        Space destinationSpace = findSpace(b);

        if(destinationSpace==null){
            System.out.println("Space " + destinationName + " could not be found...");
        }
        else{
            b.move(currentOccupant, destinationSpace);
        }

        putSelfBackInPile();
    }

    private Space findSpace(Board b){

        List<Space> spaces = b.getSpaces();

        for(Space space:spaces){
            if(space.getName().equalsIgnoreCase(destinationName)){
                return space;
            }
        }
        return null;
    }
}
