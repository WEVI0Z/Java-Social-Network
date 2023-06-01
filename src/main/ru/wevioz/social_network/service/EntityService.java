package wevioz.social_network.service;

public interface EntityService<T> {
    T findById(int id);
    void add(T item);
    void remove(T item);
}