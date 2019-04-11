package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{

    String myName;

    public CommonSpace(String name){
        this.myName = name;
    }

    @Override
    protected void invokeAction(Player p) {

    }

    @Override
    public String getMyPropertyName(){
        return myName;
    }
}
