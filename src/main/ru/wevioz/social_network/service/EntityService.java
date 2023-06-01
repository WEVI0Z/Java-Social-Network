package wevioz.social_network.service;

import java.util.Optional;

public interface EntityService<T> {
    Optional<T> findById(int id);
    void add(T item);
    void remove(T item);
}