package team.necro.game.module.properties.listener;


import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import team.necro.game.Game;
import team.necro.game.module.properties.GameProperties;
import team.necro.game.module.properties.PropertiesModule;

@AllArgsConstructor
public class WorldPhysicsListener implements Listener {

    private Game game;
    private PropertiesModule module;


    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        GameProperties properties = module.getEntity(game.getInfo().getState());
        if(!properties.isAllowWeather()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeaves(LeavesDecayEvent event) {
        GameProperties properties = module.getEntity(game.getInfo().getState());
        if(!properties.isAllowLeavesDecay()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        GameProperties properties = module.getEntity(game.getInfo().getState());
        if(!properties.isAllowLiquidPhysics()) {
            event.setCancelled(true);
        }
    }
}
