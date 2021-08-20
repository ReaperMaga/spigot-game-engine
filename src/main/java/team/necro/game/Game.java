package team.necro.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.bootstrap.GameBootstrap;
import team.necro.game.language.LanguageProvider;
import team.necro.game.language.impl.FileLanguageAdapter;
import team.necro.game.language.impl.FileLanguageRepository;
import team.necro.game.location.LocationProvider;
import team.necro.game.location.file.LocationFileAdapter;
import team.necro.game.module.GameModule;
import team.necro.game.participant.GameParticipantRegistry;

import java.util.UUID;

@Getter
public class Game {

    private final GameBootstrap bootstrap;

    private String id;
    private GameInfo info;
    private GameParticipantRegistry participantRegistry;
    @Setter
    private LanguageProvider<Player> languageProvider;
    private LocationProvider locationProvider;


    public Game(GameBootstrap bootstrap) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.bootstrap = bootstrap;
        this.info = new GameInfo(this);
        this.participantRegistry = new GameParticipantRegistry(this);
        this.locationProvider = new LocationFileAdapter(bootstrap.getDirectory());
    }


    public void init() {
        for(GameModule module : bootstrap.getModules().values()) {
            module.init(this);
        }
    }

    public boolean hasModule(Class<? extends GameModule> module) {
        return this.bootstrap.getModules().containsKey(module);
    }

    public <G extends GameModule> G getModule(Class<? extends GameModule> module) {
        return (G) this.bootstrap.getModules().get(module);
    }

    public JavaPlugin getPlugin() {
        return this.bootstrap.getPlugin();
    }

    public LanguageProvider<Player> getLanguageProvider() {
        if(languageProvider == null) {
            this.languageProvider = new FileLanguageAdapter(this, "en");
        }
        return languageProvider;
    }
}
