package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ofs.entity.FoodProductEntity;
import uz.ofs.repository.FoodProductRepository;
import uz.ofs.service.FoodProductService;
import uz.ofs.validation.CommonValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodProductServiceImpl implements FoodProductService {

    private final FoodProductRepository repository;

    private final CommonValidation commonValidation;

    @Override
    public boolean add(FoodProductEntity product) {

        commonValidation.validateFoodProduct(product);
        commonValidation.validateCategoryId(product.getCategoryId());

        repository.save(product);

        return true;
    }

    @Override
    public FoodProductEntity getById(Long id) {
        return commonValidation.validateFoodProduct(id);
    }

    @Override
    public List<FoodProductEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(FoodProductEntity updateEntity) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }


}
