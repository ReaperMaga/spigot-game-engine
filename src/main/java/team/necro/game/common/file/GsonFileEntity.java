package team.necro.game.common.file;

import com.google.common.reflect.TypeToken;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import team.necro.game.common.reflection.Reflections;

import java.io.*;
import java.lang.reflect.ParameterizedType;

@Getter
public class GsonFileEntity<T> {


    @Setter
    private T entity;
    private File file;

    private Class<T> genericClass;

    public GsonFileEntity(String path, String file, Class<T> genericClass) {
        this.genericClass = genericClass;
        File directory = new File(path);
        directory.mkdirs();

        this.file = new File(path.concat(file).concat(".json"));
        load();
    }

    public boolean exists() {
        return this.file.exists();
    }

    public void delete() {
        if (this.file.exists()) {
            this.file.delete();
        }
    }

    public void load() {
        try {
            if(exists()) {
                @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                this.entity = GsonConstants.PRETTY_JSON.fromJson(reader, genericClass);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }
            String json = GsonConstants.PRETTY_JSON.toJson(this.entity);
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(file);
            @Cleanup OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, "UTF-8");
            writer.write(json);
            writer.flush();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
