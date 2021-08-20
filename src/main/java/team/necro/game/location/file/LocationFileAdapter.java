package team.necro.game.location.file;

import com.google.common.collect.Maps;
import org.bukkit.Location;
import team.necro.game.common.file.GsonFileEntity;
import team.necro.game.location.LocationProvider;

public class LocationFileAdapter extends GsonFileEntity<LocationWrapper> implements LocationProvider {

    public LocationFileAdapter(String path) {
        super(path, "locations", LocationWrapper.class);
        if(!exists()) {
            this.setEntity(new LocationWrapper(Maps.newHashMap()));
            save();
        }
    }

    @Override
    public void setLocation(String key, Location location) {
        getEntity().getLocations().put(key.toLowerCase(), location);
        save();
    }

    @Override
    public Location getLocation(String key) {
        return getEntity().getLocations().get(key.toLowerCase());
    }

    @Override
    public boolean existsLocation(String key) {
        return getEntity().getLocations().containsKey(key.toLowerCase());
    }
}
