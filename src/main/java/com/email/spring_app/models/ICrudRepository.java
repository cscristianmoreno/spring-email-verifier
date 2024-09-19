package com.email.spring_app.models;

import java.util.Optional;

public interface ICrudRepository<T> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(int id);

    void deleteById(int id);
}
