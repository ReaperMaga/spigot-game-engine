package team.necro.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import team.necro.game.event.GameStateChangeEvent;

@Getter
public class GameInfo {

    private final Game game;

    private int state;
    private String mapName;

    public GameInfo(Game game) {
        this.game = game;
        this.state = 0;
        this.mapName = "";
    }

    public void setState(int state) {
        this.state = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(game, state));
    }

    public void changeMap(String mapName) {
        this.mapName = mapName;
    }
}
