package team.necro.game.module.properties.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import team.necro.game.Game;
import team.necro.game.bootstrap.GameScope;

@AllArgsConstructor
public class PlayerJoinListener implements Listener {

    private Game game;


    @EventHandler
    public void onCall(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(game.getBootstrap().getScope() == GameScope.SERVER) {
            game.getParticipantRegistry().registerParticipant(player);
        }

    }
}
