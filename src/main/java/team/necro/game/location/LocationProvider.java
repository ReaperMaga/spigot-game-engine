package team.necro.game.location;

import org.bukkit.Location;

import javax.annotation.Nullable;

public interface LocationProvider {

    void setLocation(String key, Location location);

    @Nullable
    Location getLocation(String key);

    boolean existsLocation(String key);
}
