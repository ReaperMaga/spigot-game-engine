package team.necro.game.location.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationWrapper {

    private Map<String, Location> locations;
}
