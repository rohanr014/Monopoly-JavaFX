package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{

    public CommonSpace(){

    }

    @Override
    public boolean onLand(Player p) {
        return false;
    }
}
