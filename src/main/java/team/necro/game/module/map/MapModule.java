package team.necro.game.module.map;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import team.necro.game.Game;
import team.necro.game.module.GameModule;
import team.necro.game.module.map.listener.GameMapChangeListener;
import team.necro.game.location.LocationProvider;
import team.necro.game.location.file.LocationFileAdapter;
import team.necro.game.module.map.repository.AbstractGameMapRepository;
import team.necro.game.module.map.repository.GameMapRepository;
import team.necro.game.module.map.strategy.GameMapStrategy;
import team.necro.game.module.map.strategy.impl.RoundRobinGameMapStrategy;

@Getter
public class MapModule implements GameModule {

    private LocationProvider locationProvider;
    private GameMapRepository repository;
    private GameMapStrategy strategy;

    public MapModule() {
        this.strategy = new RoundRobinGameMapStrategy();
    }

    public MapModule(GameMapStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void init(Game game) {
        this.repository = new AbstractGameMapRepository(game.getBootstrap().getDirectory());
        this.locationProvider = new LocationFileAdapter(game.getBootstrap().getDirectory());

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new GameMapChangeListener(this), game.getPlugin());
    }

    @Override
    public Class<? extends GameModule>[] getDependencies() {
        return new Class[0];
    }


}
