package team.necro.game.module.map.strategy;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import team.necro.game.Game;
import team.necro.game.module.map.model.GameMap;

import javax.annotation.Nullable;

public interface GameMapStrategy {

    void load(Game game, GameMap map);

    @Nullable
    Location get(Player player);
}
