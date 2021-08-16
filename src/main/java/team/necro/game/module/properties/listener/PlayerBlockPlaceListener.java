package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerBlockPlaceListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(game.existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getProperties(game.getInfo().getState());
            if(!properties.isAllowBlockPlace()) {
                event.setCancelled(true);
            }
        }
    }
}
