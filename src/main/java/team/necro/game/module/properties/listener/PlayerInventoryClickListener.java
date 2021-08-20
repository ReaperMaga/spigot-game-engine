package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerInventoryClickListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getEntity(game.getInfo().getState());
            if(!properties.isAllowInventoryClick()) {
                event.setResult(Event.Result.DENY);
                event.setCancelled(true);
            }
        }
    }
}
