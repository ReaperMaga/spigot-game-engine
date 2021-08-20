package team.necro.game.module.map.listener;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import team.necro.game.module.map.MapModule;
import team.necro.game.module.map.event.GameMapChangeEvent;

@AllArgsConstructor
public class GameMapChangeListener implements Listener {

    private MapModule module;

    @EventHandler
    public void onCall(GameMapChangeEvent event) {
        module.getStrategy().load(event.getGame(), event.getMap());
    }
}
