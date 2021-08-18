package team.necro.game.bootstrap;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.exception.MissingDependencyException;
import team.necro.game.module.GameModule;

import java.io.File;
import java.util.Map;

@Getter
public class GameBootstrap {

    private final JavaPlugin plugin;
    private final String name;
    private final GameScope scope;

    private String directory;
    private boolean allowReconnect;
    private int minPlayers;
    private int maxPlayers;

    private Map<Class<? extends GameModule>, GameModule> modules;

    public GameBootstrap(JavaPlugin plugin, String name, GameScope scope) {
        this.plugin = plugin;
        this.name = name;
        this.scope = scope;
        this.modules = Maps.newHashMap();

        this.directory = new StringBuilder("plugins").append(File.separator).append(plugin.getDescription().getName()).toString();
        this.allowReconnect = true;
        this.minPlayers = 2;
        this.maxPlayers = 8;
    }

    public GameBootstrap useModules(GameModule... modules) throws MissingDependencyException {
        for(GameModule module : modules) {
            this.modules.put(module.getClass(), module);
        }
        for(GameModule module : modules) {
            for(Class<? extends GameModule> dependency : module.getDependencies()) {
                if(!this.modules.containsKey(dependency)) {
                    throw new MissingDependencyException(module, dependency.getSimpleName());
                }
            }
        }
        return this;
    }

    public GameBootstrap minPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
        return this;
    }

    public GameBootstrap maxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }


}

