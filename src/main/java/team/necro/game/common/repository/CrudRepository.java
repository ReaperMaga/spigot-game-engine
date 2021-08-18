package team.necro.game.common.repository;

import java.util.List;

public interface CrudRepository<T> {

    void create(String primaryKey, T entity);

    T find(String primaryKey);

    List<T> findAll();

    void update(String primaryKey, T entity);

    void delete(String primaryKey);

    boolean exists(String primaryKey);
}
