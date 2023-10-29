package os.psy.research.spotlight.infrastructure.persistence.doubles;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import os.psy.research.spotlight.core.entity.AbstractEntity;
import os.psy.research.spotlight.core.repository.GenericRepository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class InMemoryGenericRepository<T extends AbstractEntity> implements GenericRepository<T> {

    private final Map<String, T> entities = new HashMap<>();

    public void flush() {
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    public void deleteAllInBatch(Iterable<T> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    public void deleteAllInBatch() {

    }

    public T getOne(String s) {
        return null;
    }

    public T getById(String s) {
        return null;
    }

    public T getReferenceById(String s) {
        return null;
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }

    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    public <S extends T> S save(S entity) {
        if (entity.getEntityId() == null) {
            // That's very ugly but enough for test propouse.
            entity.setEntityId(UUID.randomUUID().toString());
        }
        return (S) putCloneInMap(entity);
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> save(entity));
        return (List<S>) entities;
    }

    public Optional<T> findById(String s) {
        return Optional.empty();
    }

    public boolean existsById(String s) {
        return entities.containsKey(s);
    }


    public List<T> findAll() {
        return List.copyOf(entities.values());
    }

    public List<T> findAllById(Iterable<String> strings) {
        // Retrieve only the FocusUnits with matching IDs from the in-memory map
        return entities.values()
                .stream()
                .filter(T -> containsId(strings, T.getEntityId()))
                .toList();
    }

    public long count() {
        return entities.size();
    }

    public void deleteById(String s) {
        entities.remove(s);
    }

    public void delete(T entity) {
        entities.remove(entity.getEntityId());

    }

    public void deleteAllById(Iterable<? extends String> strings) {
        strings.forEach(entities::remove);
    }

    public void deleteAll(Iterable<? extends T> entities) {

    }

    public void deleteAll() {
        entities.clear();
    }

    public List<T> findAll(Sort sort) {
        return null;
    }

    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    private boolean containsId(Iterable<String> ids, String id) {
        for (String currentId : ids) {
            if (currentId.equals(id)) {
                return true;
            }
        }
        return false;
    }

    protected List<T> getEntities() {
        List<T> result = new ArrayList<T>();
        for (T entity : entities.values()) {
            addCloneToList(result, entity);
        }
        return result;
    }

    private T putCloneInMap(T entity) {
        try {
            return entities.put(entity.getEntityId(), (T) entity.clone());

        } catch (CloneNotSupportedException e) {
            throw new UnclonnableEntity();
        }
    }

    private void addCloneToList(List<T> result, T entity) {
        try {
            result.add((T) entity.clone());
        } catch (CloneNotSupportedException e) {
            throw new UnclonnableEntity();
        }
    }

    private static class UnclonnableEntity extends RuntimeException {
    }
}
