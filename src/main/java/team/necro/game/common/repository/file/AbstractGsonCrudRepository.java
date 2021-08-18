package team.necro.game.common.repository.file;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import team.necro.game.common.file.GsonFileEntity;
import team.necro.game.common.repository.CrudRepository;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractGsonCrudRepository<T> implements CrudRepository<T> {

    private Map<String, GsonFileEntity<T>> cache;

    private Class<T> genericClass;
    private File directory;

    public AbstractGsonCrudRepository(String path, Class<T> genericClass) {
        this.genericClass = genericClass;
        this.directory = new File(path);
        this.directory.mkdirs();

        /*this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, GsonFileEntity<T>>() {
            @Override
            public GsonFileEntity<T> load(String key) throws Exception {
                GsonFileEntity<T> file = new GsonFileEntity<T>(path, key);
                return file;
            }
        });*/
        this.cache = Maps.newHashMap();
    }
    
    public GsonFileEntity<T> getOrLoad(String key) {
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        GsonFileEntity<T> file = new GsonFileEntity<T>(getCorrectDirectoryPath(), key, genericClass);
        cache.put(key, file);
        return file;
    }
    
    public String getCorrectDirectoryPath() {
        return this.directory.getPath().concat(File.separator);
    }

    @Override
    public List<T> findAll() {
        List<T> list = Lists.newArrayList();
        for(File file : directory.listFiles()) {
            GsonFileEntity<T> fileEntity = new GsonFileEntity<>(getCorrectDirectoryPath(), file.getName().replace(".json", ""), genericClass);
            list.add(fileEntity.getEntity());
        }
        return list;
    }

    @Override
    public T find(String primaryKey) {
        try {
            GsonFileEntity<T> file = getOrLoad(primaryKey);
            return file.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(String primaryKey, T entity) {
        try {
            GsonFileEntity<T> file = getOrLoad(primaryKey);
            file.setEntity(entity);
            file.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String primaryKey) {
        if(cache.containsKey(primaryKey)) {
            return true;
        }
        try {
            GsonFileEntity<T> file = getOrLoad(primaryKey);
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(String primaryKey, T entity) {
        try {
            GsonFileEntity<T> file = getOrLoad(primaryKey);
            file.setEntity(entity);
            file.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String primaryKey) {
        try {
            GsonFileEntity<T> file = getOrLoad(primaryKey);
            file.delete();
            cache.remove(primaryKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<GsonFileEntity<T>> findAllCached() {
        return this.cache.values();
    }
}
