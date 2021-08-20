package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerInteractEntityListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getEntity(game.getInfo().getState());
            if(!properties.isAllowEntityInteract()) {
                event.setCancelled(true);
            }
        }
    }
}
