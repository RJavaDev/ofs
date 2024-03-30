package uz.ofs.service;

import java.util.List;

public interface BaseService<T>{

    boolean add(T entity);

    T getById(Long id);

    List<T> getAll();

    boolean update(T updateEntity);

    void delete(Long id);
}
