package team.necro.game.common.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonConstants {

    public static final Gson PRETTY_JSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();
}
