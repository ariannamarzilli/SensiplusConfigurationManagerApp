package it.unicas.project.dao;

import java.util.List;

/**
 * Generic interface that extends the DAO generic interface
 * and adds CRUD operations.
 *
 * @param <Entity> The entity.
 */
public interface CrudDAO<Entity> extends DAO {

    /**
     * Creates the new entity passed in.
     *
     * @param entity The entity to be created.
     */
    void create(Entity entity);

    /**
     * Deletes the entity passed in.
     *
     * @param entity The entity to be delated.
     */
    void delete(Entity entity);

    /**
     * Updates the entity passed in.
     *
     * @param entity The entity to be updated.
     */
    void update(Entity entity);

    /**
     * Fetches and returns all the entities.
     *
     * @return All the entities fetched.
     */
    List<Entity> fetchAll();
}
