package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerFoodLevelChangeListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        if(game.existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getProperties(game.getInfo().getState());
            if(!properties.isAllowHunger()) {
                player.setSaturation(20f);
                event.setCancelled(true);
            }
        }
    }
}
