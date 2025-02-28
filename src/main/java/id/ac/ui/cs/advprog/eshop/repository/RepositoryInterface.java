package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface RepositoryInterface<T> {
    T create(T item);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T updatedItem);
    void delete(String id);
}
