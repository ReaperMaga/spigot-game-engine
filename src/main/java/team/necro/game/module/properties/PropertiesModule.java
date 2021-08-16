package team.necro.game.module.properties;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import team.necro.game.Game;
import team.necro.game.module.AbstractLoadingStateChangeModule;
import team.necro.game.module.GameModule;
import team.necro.game.module.properties.listener.PlayerBlockBreakListener;
import team.necro.game.module.properties.listener.PlayerBlockPlaceListener;
import team.necro.game.module.properties.listener.PlayerFoodLevelChangeListener;

@Getter
public class PropertiesModule extends AbstractLoadingStateChangeModule<GameProperties> {

    @Override
    public void init(Game game) {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerFoodLevelChangeListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerBlockBreakListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerBlockPlaceListener(game, this), game.getPlugin());
    }

    @Override
    public Class<? extends GameModule>[] getDependencies() {
        return new Class[0];
    }


    @Override
    public GameProperties load(int state) {
        return new GameProperties();
    }
}
