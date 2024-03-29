package app.engine.Config;

import app.engine.card.Card;
import app.engine.space.*;
import org.w3c.dom.ls.LSResourceResolver;

import java.util.Queue;
import java.util.ResourceBundle;

public class SpaceMaker {

    private static final String NAME_KEY = "name";
    private static final String SALE_PRICE_KEY = "salePrice";
    private static final String HOUSE_PRICE_KEY = "housePrice";
    private static final String HOTEL_PRICE_KEY = "hotelPrice";
    private static final String MORTGAGE_KEY = "mortgage";
    private static final String COLOR_KEY = "color";
    private static final String RENTS_KEY = "rents";
    private static final String MONEY_KEY = "money";

    private  Queue<Card> chance;
    private Queue<Card> communityChest;

    public SpaceMaker(Queue<Card> chance, Queue<Card> communityChest) {
        this.chance = chance;
        this.communityChest = communityChest;

    }


    private double[] stringsToDoubles(String[] strings){
        double[] toReturn = new double[strings.length];

        for(int i=0; i<strings.length; i++){
            toReturn[i] = Double.parseDouble(strings[i]);
        }

        return toReturn;
    }

    public Space makeCP(String propFile){
        ResourceBundle cpBundle = ResourceBundle.getBundle(propFile);

        String name = cpBundle.getString(NAME_KEY);

        double purchaseCost = Double.parseDouble(cpBundle.getString(SALE_PRICE_KEY));
        double housePrice = Double.parseDouble(cpBundle.getString(HOUSE_PRICE_KEY));
        double hotelPrice = Double.parseDouble(cpBundle.getString(HOTEL_PRICE_KEY));
        double mortgageValue = Double.parseDouble(cpBundle.getString(MORTGAGE_KEY));
        String colorString = cpBundle.getString(COLOR_KEY);

        String[] rentStrings = cpBundle.getString(RENTS_KEY).split(",");
        //System.out.println(name);

        return new ColorProperty(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), housePrice, hotelPrice, colorString);
    }



    public Space makeRR(String propFile){
        ResourceBundle currentBundle = ResourceBundle.getBundle(propFile);


        double purchaseCost = Double.parseDouble(currentBundle.getString(SALE_PRICE_KEY));
        double mortgageValue = Double.parseDouble(currentBundle.getString(MORTGAGE_KEY));
        String[] rentStrings = currentBundle.getString(RENTS_KEY).split(",");

        String fullName = currentBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        String name = null;
        String imageName = null;

        if(fullNameSplit.length > 1){
            name = fullNameSplit[0];
            imageName = fullNameSplit[1];

        }
        else{
            name = fullName;
        }
        // IS THERE A BETTER WAY TO DO THIS?

        if(imageName == null){
            return new Railroad(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }

        else{
            return new Railroad(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), imageName);

        }
    }

    // DEAL WITH THIS REPEATED CODE ONCE REFLECTION IS WORKING
    public Space makeUtility(String propFile){
        ResourceBundle currentBundle = ResourceBundle.getBundle(propFile);


        double purchaseCost = Double.parseDouble(currentBundle.getString(SALE_PRICE_KEY));
        double mortgageValue = Double.parseDouble(currentBundle.getString(MORTGAGE_KEY));
        String[] rentStrings = currentBundle.getString(RENTS_KEY).split(",");

        String fullName = currentBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        String name = null;
        String imageName = null;

        if(fullNameSplit.length > 1){
            name = fullNameSplit[0];
            imageName = fullNameSplit[1];

        }
        else{
            name = fullName;
        }


        // IS THERE A BETTER WAY TO DO THIS?

        if(imageName == null){
            return new Utility(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }

        else{
            return new Utility(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), imageName);

        }
    }

    public Space makeMoney(String propFile){
        ResourceBundle moneyBundle = ResourceBundle.getBundle(propFile);

        double moneyGiven = Double.parseDouble(moneyBundle.getString(MONEY_KEY));

        String fullName = moneyBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        if(fullNameSplit.length > 1){
            String name = fullNameSplit[0];
            String imageName = fullNameSplit[1];
            return new CommonSpace(name, moneyGiven, imageName);
        }
        else{
            return new CommonSpace(fullName, moneyGiven);
        }
    }

    public Space makeMoveSpace(String propFile){
        ResourceBundle moveBundle = ResourceBundle.getBundle(propFile);

        String destinationName = moveBundle.getString("destination");

        String fullName = moveBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        if(fullNameSplit.length > 1){
            String name = fullNameSplit[0];
            String imageName = fullNameSplit[1];
            return new CommonSpace(name, destinationName, imageName);
        }
        else{
            return new CommonSpace(fullName, destinationName);
        }

    }


    public Space makeCardSpace(String propFile){
        ResourceBundle myBundle = ResourceBundle.getBundle(propFile);

        String spaceName = myBundle.getString(NAME_KEY);
        String pileName = myBundle.getString("pile");

        if(pileName.equalsIgnoreCase("chance")){
            return new CardSpace(spaceName, chance);
        }

        else if(pileName.equalsIgnoreCase("communityChest")){
            return new CardSpace(spaceName, communityChest);
        }

        else{
            return null;
        }
    }

}
