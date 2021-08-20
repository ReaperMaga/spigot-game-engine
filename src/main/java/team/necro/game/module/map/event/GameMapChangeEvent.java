package team.necro.game.module.map.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import team.necro.game.Game;
import team.necro.game.module.map.model.GameMap;

@Getter
@AllArgsConstructor
public class GameMapChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Game game;
    private GameMap map;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
