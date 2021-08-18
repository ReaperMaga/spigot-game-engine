package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class PlayerBlockBreakListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
            GameProperties properties = module.getEntity(game.getInfo().getState());
            if(!properties.isAllowBlockBreak()) {
                event.setCancelled(true);
            }
        }
    }
}
