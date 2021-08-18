package team.necro.game.module.countdown;

import team.necro.game.Game;

public interface CountdownListener {

    void onStart(Game game);
    boolean onCall(Game game);
    void onEnd(Game game);
    void onStop();
}
