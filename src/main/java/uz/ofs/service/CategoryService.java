package uz.ofs.service;

import uz.ofs.entity.CategoryEntity;
import uz.ofs.entity.FoodProductEntity;

import java.util.List;

public interface CategoryService extends BaseService<CategoryEntity>{

    boolean add(CategoryEntity entity);
    List<CategoryEntity> getAll();
}
