package uz.ofs.service;

import java.util.List;

public interface BaseService<T>{

    T getById(Long id);

    boolean update(T updateEntity);

    void delete(Long id);
}
