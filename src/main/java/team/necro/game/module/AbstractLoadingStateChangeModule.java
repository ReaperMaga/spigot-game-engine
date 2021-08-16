package team.necro.game.module;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class AbstractLoadingStateChangeModule<T> implements GameModule {

    private Map<Integer, T> entities;

    public AbstractLoadingStateChangeModule() {
        this.entities = Maps.newHashMap();
    }

    public AbstractLoadingStateChangeModule(Map<Integer, T> entities) {
        this.entities = entities;
    }


    public T getEntity(int state) {
        if(this.entities.containsKey(state)) {
            T entity = load(state);
            registerEntity(state, entity);
            return entity;
        }
        return this.entities.get(state);
    }

    public void registerEntity(int state, T entity) {
        this.entities.put(state, entity);
    }

    public boolean existsEntity(int state) {
        return this.entities.containsKey(state);
    }

    public abstract T load(int state);
}
