package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{

    String name;

    public CommonSpace(String name){
        super(name);
    }

    @Override
    protected void invokeAction(Player p) {

    }
}
