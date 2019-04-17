package app.engine.card;

import app.engine.Asset;
import app.engine.agent.Player;
import app.engine.board.Board;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HoldableCard extends Card implements Asset, Holdable {
    String funcName;
    Object[] processedArguments;

    public HoldableCard(String desc, Board b, String[] funcWithArgs) throws Exception {
        super(desc, b);
        this.funcName = funcWithArgs[0];
        processArguments(Arrays.copyOfRange(funcWithArgs, 1, funcWithArgs.length));

        if(funcWithArgs.length % 2 != 1){
            throw new Exception("Invalid input for funcWithArgs");
        }
    }

    private void processArguments(String[] arguments) {
        processedArguments = new Object[arguments.length/2];

        for(int i=0; i<arguments.length; i+=2){

            if(arguments[i].equalsIgnoreCase("boolean")){
                processedArguments[i/2] = Boolean.parseBoolean(arguments[i+1]);
            }

            else if(arguments[i].equalsIgnoreCase("double")){
                processedArguments[i/2] = Double.parseDouble(arguments[i+1]);
            }

            else if(arguments[i].equalsIgnoreCase("int")){
                processedArguments[i/2] = Integer.parseInt(arguments[i+1]);
            }

            else{
                processedArguments[i/2] = arguments[i+1];
            }
        }
    }

    public void useCard(Player currentOwner) {
        Class playerClass = currentOwner.getClass();

        try {
            if(processedArguments.length == 0) {
                Method funcToCall = playerClass.getDeclaredMethod(funcName);
                funcToCall.invoke(currentOwner);
            }

            if (processedArguments.length == 1) {
                Method funcToCall = playerClass.getDeclaredMethod(funcName, getPrimitiveType(processedArguments[0]));
                funcToCall.invoke(currentOwner, processedArguments[0]);
            }

            else if(processedArguments.length == 2){
                Method funcToCall = playerClass.getDeclaredMethod(funcName, getPrimitiveType(processedArguments[0]), getPrimitiveType(processedArguments[1]));
                funcToCall.invoke(currentOwner, processedArguments[0], processedArguments[1]);
            }
        }

            catch (NoSuchMethodException e) {
                System.out.println("Couldn't find appropriate method for card " + getDescription() +
                        ". Please check function name in properties file");
                e.printStackTrace();
            }

            catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            catch (InvocationTargetException e) {
                System.out.println("Invocation target exception - check arguments");
                e.printStackTrace();
        }

        putSelfBackInPile();
        currentOwner.getCards().remove(this);
    }

    private Class getPrimitiveType(Object b){
        boolean testBool = false;

        if(b instanceof Boolean){
            return boolean.class;
        }

        else if(b instanceof Integer){
            return int.class;
        }

        else if(b instanceof Double){
            return double.class;
        }

        else{
            System.out.println("Couldn't determine class");
            return null;
        }
    }

    @Override
    public void invokeAction(Player newOwner) {
        newOwner.addCard(this);
    }

    public boolean sellToBank(Player player) {
        return putSelfBackInPile() && getBoard().getBank().giveMoney(player, getValue());
    }

    @Override
    public double getValue() {
        return getBoard().getRules().getHoldableCardSellValue();
    }

    public String getFuncName() {
        return funcName;
    }
}
