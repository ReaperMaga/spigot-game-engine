package team.necro.game.module.countdown.template;

import lombok.Setter;
import org.bukkit.Bukkit;
import team.necro.game.Game;
import team.necro.game.module.countdown.CountdownListener;
import team.necro.game.module.countdown.impl.SpigotCountdown;

@Setter
public class LobbyCountdown extends SpigotCountdown implements CountdownListener {

    private final long timer;


    private boolean waiting;

    public LobbyCountdown(long timer) {
        super(20, 20);
        this.timer = timer;
        this.waiting = true;
        setRemaining(5);
        addListener(this);
    }

    @Override
    public void onStart(Game game) {
    }

    @Override
    public boolean onCall(Game game) {
        if(waiting) {
            if(game.getParticipantRegistry().getParticipants().size() < game.getBootstrap().getMinPlayers()) {
                if(getRemaining() == 1) {
                    game.getLanguageProvider().broadcastMessage("lobby.countdown.broadcast.waiting");
                    setRemaining(5);
                }
                return true;
            }
            setRemaining(timer);
            setWaiting(false);
            game.getLanguageProvider().broadcastMessage("lobby.countdown.broadcast.start");
        } else {
            if(game.getParticipantRegistry().getParticipants().size() < game.getBootstrap().getMinPlayers()) {
                game.getLanguageProvider().broadcastMessage("lobby.countdown.broadcast.reset");
                setRemaining(5);
                setWaiting(true);
                return true;
            }
        }
        switch (Math.toIntExact(getRemaining())) {
            case 60: case 45: case 30: case 15: case 10: case 5: case 4: case 3: case 2: case 1:
                game.getLanguageProvider().broadcastMessage("lobby.countdown.broadcast.counter", getRemaining());
                break;
        }
        return true;
    }

    @Override
    public void onEnd(Game game) {
        game.getLanguageProvider().broadcastMessage("lobby.countdown.broadcast.game.begin");
        int nextState = game.getInfo().getState() + 1;
        game.getInfo().setState(nextState);
    }

    @Override
    public void onStop() {

    }
}
