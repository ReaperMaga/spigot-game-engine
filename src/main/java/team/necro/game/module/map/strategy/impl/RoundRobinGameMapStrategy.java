package team.necro.game.module.map.strategy.impl;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import team.necro.game.Game;
import team.necro.game.module.map.model.GameMap;
import team.necro.game.module.map.strategy.GameMapStrategy;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinGameMapStrategy implements GameMapStrategy {

    private List<Location> locations;
    private int current;

    @Override
    public void load(Game game, GameMap map) {
        this.locations = new ArrayList<>(map.getSpawns().values());
    }

    @Override
    public Location get(Player player) {
        if((current) >= locations.size()) {
            current = 0;
        }
        return locations.get(current);
    }

}
