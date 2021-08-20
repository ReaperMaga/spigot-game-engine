package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerInteractListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getEntity(game.getInfo().getState());
            if(!properties.isAllowInteract()) {
                event.setCancelled(true);
            }
        }
    }
}
