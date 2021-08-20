package team.necro.game.module.map.repository;

import team.necro.game.common.repository.file.AbstractGsonCrudRepository;
import team.necro.game.module.map.model.GameMap;

import javax.annotation.Nullable;
import java.io.File;

public class AbstractGameMapRepository extends AbstractGsonCrudRepository<GameMap> implements GameMapRepository {

    public AbstractGameMapRepository(String path) {
        super(path.concat("maps").concat(File.separator), GameMap.class);
    }

    @Override
    public void registerMap(GameMap map) throws IllegalStateException {
        if(exists(map.getName().toLowerCase())) {
            throw new IllegalStateException("Map already exists");
        }
        create(map.getName(), map);
    }

    @Override
    public void deleteMapByName(String name) throws IllegalStateException {
        if(!existsMapByName(name)) {
            throw new IllegalStateException("Map does not exists");
        }
        delete(name.toLowerCase());
    }

    @Nullable
    @Override
    public GameMap getMapByName(String name) {
        return find(name.toLowerCase());
    }

    @Override
    public boolean existsMapByName(String name) {
        return exists(name.toLowerCase());
    }
}
