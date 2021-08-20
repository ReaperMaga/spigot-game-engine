package team.necro.game.common;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SpigotUtil {

    /**
     * Serialize String to location
     **/
    public static Location stringToLocation(String stringLocation) {
        String[] args = stringLocation.split(";");
        World world = Bukkit.getWorld(args[0]);
        double x = Double.valueOf(args[1]);
        double y = Double.valueOf(args[2]);
        double z = Double.valueOf(args[3]);
        double yaw = Double.valueOf(args[4]);
        double pitch = Double.valueOf(args[5]);
        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

    /**
     * Deserialize Location to string
     **/
    public static String locationToString(Location location) {
        StringBuilder builder = new StringBuilder()
                .append(location.getWorld().getName()).append(";")
                .append(location.getX()).append(";")
                .append(location.getY()).append(";")
                .append(location.getZ()).append(";")
                .append(location.getYaw()).append(";")
                .append(location.getPitch());
        return builder.toString();
    }
}
