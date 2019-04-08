package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{

    public CommonSpace(){

    }

    @Override
    public void onLand(Player p) {
        return false;
    }
}
