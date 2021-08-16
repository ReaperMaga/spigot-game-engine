package team.necro.game;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import team.necro.game.bootstrap.GameBootstrap;
import team.necro.game.bootstrap.GameScope;
import team.necro.game.module.countdown.CountdownModule;

public class TestGame extends JavaPlugin {

    @Override
    @SneakyThrows
    public void onEnable() {
        GameBootstrap bootstrap = new GameBootstrap(this, "Test", GameScope.SERVER)
                .useModules(new CountdownModule());
        Game game = new Game(bootstrap);
        game.init();


        CountdownModule countdownModule = game.getModule(CountdownModule.class);

    }
}
