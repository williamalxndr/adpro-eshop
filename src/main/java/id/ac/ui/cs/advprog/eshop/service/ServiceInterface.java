package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ServiceInterface<T> {
    public T create(T item);
    public List<T> findAll();
    public T findById(String id);
    public void update(String id, T updatedItem);
    public void deleteById(String id);
}
