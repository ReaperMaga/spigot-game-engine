package team.necro.game.module.properties;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import team.necro.game.Game;
import team.necro.game.bootstrap.GameScope;
import team.necro.game.module.AbstractLoadingStateChangeModule;
import team.necro.game.module.GameModule;
import team.necro.game.module.properties.listener.*;

@Getter
public class PropertiesModule extends AbstractLoadingStateChangeModule<GameProperties> {

    @Override
    public void init(Game game) {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerFoodLevelChangeListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerBlockBreakListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerBlockPlaceListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerJoinListener(game), game.getPlugin());
        manager.registerEvents(new PlayerQuitListener(game), game.getPlugin());
        manager.registerEvents(new PlayerInteractEntityListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerInteractListener(game, this), game.getPlugin());
        manager.registerEvents(new PlayerInventoryClickListener(game, this), game.getPlugin());
        manager.registerEvents(new EntityDamageByEntityListener(game, this), game.getPlugin());
        manager.registerEvents(new EntityDamageListener(game, this), game.getPlugin());

        if(game.getBootstrap().getScope() == GameScope.SERVER) {
            manager.registerEvents(new WorldPhysicsListener(game, this), game.getPlugin());
        }

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
