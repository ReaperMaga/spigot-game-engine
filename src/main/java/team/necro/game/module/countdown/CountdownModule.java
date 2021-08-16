package team.necro.game.module.countdown;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import team.necro.game.Game;
import team.necro.game.module.AbstractStateChangeModule;
import team.necro.game.module.GameModule;
import team.necro.game.module.countdown.listener.GameChangeStateListener;

import javax.annotation.Nullable;

@Getter
@Setter
public class CountdownModule extends AbstractStateChangeModule<Countdown> {

    @Nullable
    private Countdown currentCountdown;

    @Override
    public void init(Game game) {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new GameChangeStateListener(this), game.getPlugin());
    }

    @Override
    public Class<? extends GameModule>[] getDependencies() {
        return new Class[0];
    }


    public void stopCurrentCountdown() {
        if(isCurrentCountdownRunning()) {
            stopCurrentCountdown();
        }
    }

    public boolean isCurrentCountdownRunning() {
        if(currentCountdown != null) {
            return currentCountdown.isRunning();
        }
        return false;
    }


}
