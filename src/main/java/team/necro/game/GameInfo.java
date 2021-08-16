package team.necro.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import team.necro.game.event.GameStateChangeEvent;

@Getter
public class GameInfo {

    private int state;
    private String mapName;

    public void changeState(Game game, int state) {
        this.state = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(game, state));
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
