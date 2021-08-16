package team.necro.game.module;

import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.Game;

public interface GameModule {

    void init(Game game);
    Class<? extends GameModule>[] getDependencies();
}
