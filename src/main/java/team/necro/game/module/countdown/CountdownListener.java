package team.necro.game.module.countdown;

public interface CountdownListener {

    void onStart();
    boolean onCall();
    void onEnd();
    void onStop();
}
