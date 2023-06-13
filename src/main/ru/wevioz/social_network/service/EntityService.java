package wevioz.social_network.service;

import wevioz.social_network.exception.NotFoundException;

import java.util.Optional;

public interface EntityService<T> {
    void add(T item);
    void remove(T item);
}