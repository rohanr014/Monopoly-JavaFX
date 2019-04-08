package app.engine;

public class Player extends Agent{

    public Player(){

    }


    //player mortgages p, returns false if method fails
    boolean mortgage(Property p){
        return false;
    }

    //player unmortgages p, returns false if method fails
    boolean unmortgage(Property p){
        return false;
    }

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty cp){
        return false;
    }

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty cp){
        return false;
    }

    //player sells house on cp, returns false if method fails
    boolean sellHouse(ColorProperty cp){
        return false;
    }

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty cp){
        return false;
    }

    //player sells p to bank, returns false if method fails
    boolean sell(Property p){
        return false;
    }

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard hc){
        return false;
    }



}