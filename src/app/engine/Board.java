package app.engine;

public class Board implements IBoardObservable{

    public Board(){

    }

    /**
     * Function to give the next player their turn
     */

    public void callNextPlayer(){

    }

    /**
     * Function to move player to specific space
     */

    public void move(Player p, Space s){

    }

    /**
     * Function to move player forward a specific number of
     * steps
     */

    public void move(Player p, int steps){

    }

    //functions required for observer pattern
    @Override
    public void addObserver(IBoardObserver o) {

    }

    @Override
    public void removeObserver(IBoardObserver o) {

    }

    @Override
    public void notifyObservers() {

    }
}