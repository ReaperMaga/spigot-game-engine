package team.necro.game.module.map.repository;

import team.necro.game.common.repository.CrudRepository;
import team.necro.game.module.map.model.GameMap;

import javax.annotation.Nullable;


public interface GameMapRepository extends CrudRepository<GameMap> {

    void registerMap(GameMap map) throws IllegalStateException;

    @Nullable
    GameMap getMapByName(String name);

    boolean existsMapByName(String name);

    void deleteMapByName(String name) throws IllegalStateException;
}
