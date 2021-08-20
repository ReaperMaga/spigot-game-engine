package team.necro.game;

import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.bootstrap.GameBootstrap;
import team.necro.game.bootstrap.GameScope;
import team.necro.game.module.countdown.CountdownModule;
import team.necro.game.module.countdown.template.LobbyCountdown;
import team.necro.game.module.map.MapModule;
import team.necro.game.module.properties.PropertiesModule;

import java.util.Map;

public class TestGame extends JavaPlugin {

    @Override
    @SneakyThrows
    public void onEnable() {
        CountdownModule countdownModule = new CountdownModule(Map.of(0, new LobbyCountdown(20)));

        GameBootstrap bootstrap = new GameBootstrap(this, "Test", GameScope.SERVER)
                .useModules(new PropertiesModule(), new MapModule(), countdownModule);
        Game game = new Game(bootstrap);
        game.init();

        game.getInfo().setState(0);

    }
}
