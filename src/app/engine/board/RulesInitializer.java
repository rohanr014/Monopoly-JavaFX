package app.engine.board;

import java.util.ResourceBundle;

public class RulesInitializer {
    private final int numDoublesTilGoToJail;
    private final double goMoney;
    private final double sellToBankMult;
    private final int goIndex;
    private final int jailIndex;
    private final double unmortgageMult;
    private final double jailFee;
    private final int maxTurnsInJail;
    private final double holdableCardSellValue;
    private ResourceBundle myBundle;

    public RulesInitializer(WHATEVER ARGUMENTN) {
        myBundle = ResourceBundle.getBundle(WHATEVER RULES FILE);
        numDoublesTilGoToJail = Integer.parseInt(myBundle.getString("DoublesForJail"));
        goMoney = Double.parseDouble(myBundle.getString("GoMoney"));
        sellToBankMult = Double.parseDouble(myBundle.getString("SaleToBankMultiplier"));
        goIndex = Integer.parseInt(myBundle.getString("GoIndex"));
        jailIndex = Integer.parseInt(myBundle.getString("JailIndex"));
        unmortgageMult = Double.parseDouble(myBundle.getString("UnmortgageMultiplier"));
        jailFee = Double.parseDouble(myBundle.getString("GetOutOfJailFee"));
        maxTurnsInJail = Integer.parseInt(myBundle.getString("MaxTurnsInJail"));
        holdableCardSellValue = Double.parseDouble(myBundle.getString("HoldableCardSaleValue"));
    }


    public int getNumDoublesTilGoToJail() {
        return numDoublesTilGoToJail;
    }

    public double getGoMoney() {
        return goMoney;
    }

    public double getSellToBankMultiplier() {
        return sellToBankMult;
    }

    public int getGoIndex() {
        return goIndex;
    }

    public int getJailIndex() {
        return jailIndex;
    }

    public double getUnmortgageMultiplier() {
        return unmortgageMult;
    }

    public double getJailFee() {
        return jailFee;
    }

    public int getMaxTurnsInJail() {
        return maxTurnsInJail;
    }

    public double getHoldableCardSellValue() {
        return holdableCardSellValue;
    }
}
