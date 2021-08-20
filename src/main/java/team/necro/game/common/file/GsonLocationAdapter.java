package team.necro.game.common.file;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Location;
import team.necro.game.common.SpigotUtil;

import java.io.IOException;

public class GsonLocationAdapter extends TypeAdapter<Location> {

    @Override
    public void write(JsonWriter jsonWriter, Location location) throws IOException {
        jsonWriter.value(SpigotUtil.locationToString(location));
    }

    @Override
    public Location read(JsonReader jsonReader) throws IOException {
        return SpigotUtil.stringToLocation(jsonReader.nextString());
    }


}
