package team.necro.game.module.countdown.listener;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import team.necro.game.event.GameStateChangeEvent;
import team.necro.game.module.countdown.Countdown;
import team.necro.game.module.countdown.CountdownModule;

@AllArgsConstructor
public class GameChangeStateListener implements Listener {

    private CountdownModule module;

    @EventHandler
    public void onCall(GameStateChangeEvent event) {
        if(module.existsEntity(event.getState())) {
            module.stopCurrentCountdown();
            Countdown countdown = module.getEntity(event.getState());
            if (!countdown.isRunning()) {
                countdown.start(event.getGame());
            }
        }

    }
}
