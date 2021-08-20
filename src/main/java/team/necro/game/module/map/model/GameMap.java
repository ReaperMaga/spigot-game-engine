package team.necro.game.module.map.model;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

@Data
public class GameMap {

    private String name;
    private Material material;

    private Map<String, Location> spawns;
    private Map<String, Location> locations;

    public GameMap(String name, Material material) {
        this.name = name;
        this.material = material;
        this.spawns = new HashMap<>();
        this.locations = new HashMap<>();
    }
}
