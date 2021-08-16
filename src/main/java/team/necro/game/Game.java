package team.necro.game;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.bootstrap.GameBootstrap;
import team.necro.game.module.GameModule;
import team.necro.game.participant.GameParticipant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Game {

    private final GameBootstrap bootstrap;

    private GameInfo info;
    private Map<UUID, GameParticipant> participants;

    public Game(GameBootstrap bootstrap) {
        this.bootstrap = bootstrap;
        this.info = new GameInfo();
        this.participants = new HashMap<>();
    }

    public void init() {
        for(GameModule module : bootstrap.getModules().values()) {
            module.init(this);
        }
    }

    public void registerParticipant(UUID uuid, GameParticipant participant) {
        this.participants.put(uuid, participant);
    }

    public boolean existsParticipant(UUID uuid) {
        return this.participants.containsKey(uuid);
    }

    public boolean unregisterParticipant(UUID uuid)  {
        if(!existsParticipant(uuid)) {
            return false;
        }
        this.participants.remove(uuid);
        return true;
    }

    public <T extends GameParticipant> T getParticipant(UUID uuid) {
        return (T) this.participants.get(uuid);
    }

    public <G extends GameModule> G getModule(Class<? extends GameModule> module) {
        return (G) this.bootstrap.getModules().get(module);
    }

    public JavaPlugin getPlugin() {
        return this.bootstrap.getPlugin();
    }
}
