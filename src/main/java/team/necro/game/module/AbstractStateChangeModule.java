package team.necro.game.module;

import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class AbstractStateChangeModule<T> implements GameModule {

    private Map<Integer, T> entities;

    public AbstractStateChangeModule() {
        this.entities = Maps.newHashMap();
    }

    public AbstractStateChangeModule(Map<Integer, T> entities) {
        this.entities = entities;
    }

    @Nullable
    public T getEntity(int state) {
        return entities.get(state);
    }


    public void registerEntity(int state, T entity) {
        this.entities.put(state, entity);
    }

    public boolean existsEntity(int state) {
        return this.entities.containsKey(state);
    }

}
