package team.necro.game.module.properties.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import team.necro.game.Game;
import team.necro.game.bootstrap.GameScope;

@AllArgsConstructor
public class PlayerQuitListener implements Listener {

    private Game game;

    @EventHandler
    public void onCall(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(game.getBootstrap().getScope() == GameScope.SERVER) {
            game.getParticipantRegistry().unregisterParticipant(player.getUniqueId());
        }

    }
}
