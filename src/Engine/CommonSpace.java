package Engine;

public class CommonSpace extends Space{

    public CommonSpace(){

    }

    @Override
    public boolean onLand(Player p) {
        return false;
    }
}
