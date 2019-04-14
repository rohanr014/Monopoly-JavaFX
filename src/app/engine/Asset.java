package app.engine;

import app.engine.agent.Player;

public interface Asset {
    boolean sellToBank(Player player);
    double getValue();
}
