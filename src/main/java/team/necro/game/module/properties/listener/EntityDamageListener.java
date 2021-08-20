package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class EntityDamageListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
                GameProperties properties = module.getEntity(game.getInfo().getState());
                if(!properties.isAllowDamage()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
