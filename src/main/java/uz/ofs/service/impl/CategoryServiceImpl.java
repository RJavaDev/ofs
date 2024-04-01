package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ofs.entity.CategoryEntity;
import uz.ofs.repository.CategoryRepository;
import uz.ofs.service.CategoryService;
import uz.ofs.util.SecurityUtils;
import uz.ofs.validation.CommonValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CommonValidation commonValidation;

    @Override
    public boolean add(CategoryEntity category) {

        commonValidation.validateCategoryParent(category);

        commonValidation.validateCategoryName(category.getName());

        category.forCreate(SecurityUtils.getUserId());

        repository.save(category);

        return true;
    }

    @Override
    public CategoryEntity getById(Long id) {
        return commonValidation.validateCategory(id);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean update(CategoryEntity entity) {
        CategoryEntity updateCategory = commonValidation.validateCategoryUpdate(entity);
        repository.save(updateCategory);
        return true;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commonValidation.validateCategoryId(id);
        repository.delete(id);
    }


}
