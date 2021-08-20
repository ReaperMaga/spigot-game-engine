package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class EntityDamageByEntityListener implements Listener {

    private Game game;
    private PropertiesModule module;

    @EventHandler
    public void onCall(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            Player player = getDamager(event);
            if(player == null) {
                return;
            }
            if(isParticipant(player)) {
                GameProperties properties = module.getEntity(game.getInfo().getState());
                if(!properties.isAllowEntityDamage()) {
                    event.setCancelled(true);
                }
            }
        } else {
            Player player = (Player) event.getEntity();
            Player damager = getDamager(event);
            if(damager == null) {
                return;
            }
            if(isParticipant(player) && isParticipant(damager)) {
                GameProperties properties = module.getEntity(game.getInfo().getState());
                if(!properties.isAllowPvP()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private Player getDamager(EntityDamageByEntityEvent event) {
        Player damager = null;
        if(event.getDamager() instanceof Player) {
            damager = (Player) event.getDamager();
        } else if(event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if(projectile.getShooter() instanceof Player) {
                damager = (Player) projectile.getShooter();
            }
        }
        return damager;
    }

    private boolean isParticipant(Player player) {
        if(game.getParticipantRegistry().existsParticipant(player.getUniqueId())) {
            return true;
        }
        return false;
    }
}
