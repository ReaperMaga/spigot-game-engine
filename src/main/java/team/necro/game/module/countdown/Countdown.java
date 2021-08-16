package team.necro.game.module.countdown;

import team.necro.game.Game;

public interface Countdown {

    void start(Game game);
    void stop();

    boolean isRunning();

    void setRemaining(long remaining);
    long getRemaining();

}
