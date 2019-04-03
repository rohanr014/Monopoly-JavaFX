public interface Player extends Agent{


    //player mortgages p, returns false if method fails
    boolean mortgage(Property p);

    //player unmortgages p, returns false if method fails
    boolean unmortgage(Property p);

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty cp);

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty cp);

    //player sells house on cp, returns false if method fails
    boolean sellHouse(ColorProperty cp);

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty cp);

    //player sells p to bank, returns false if method fails
    boolean sell(Property p);

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard hc);



}