package team.necro.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import team.necro.game.event.GameStateChangeEvent;
import team.necro.game.module.map.MapModule;
import team.necro.game.module.map.event.GameMapChangeEvent;
import team.necro.game.module.map.model.GameMap;

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

    public void setMap(String mapName) throws IllegalStateException {
        this.mapName = mapName;

        if(game.hasModule(MapModule.class)) {
            MapModule mapModule = game.getModule(MapModule.class);
            if(mapModule.getRepository().existsMapByName(mapName)) {
                GameMap map = mapModule.getRepository().getMapByName(mapName);
                Bukkit.getPluginManager().callEvent(new GameMapChangeEvent(game, map));
            }
        }
    }
}
