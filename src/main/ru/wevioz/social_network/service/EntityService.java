package wevioz.social_network.service;

import wevioz.social_network.exception.NotFoundException;

import java.util.Optional;

public interface EntityService<T> {
    T findById(int id) throws NotFoundException;
    void add(T item);
    void remove(T item);
}