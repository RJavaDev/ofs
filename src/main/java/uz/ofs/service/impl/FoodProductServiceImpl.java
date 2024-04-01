package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.entity.FoodProductEntity;
import uz.ofs.repository.FoodProductRepository;
import uz.ofs.service.FoodProductService;
import uz.ofs.validation.CommonValidation;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FoodProductServiceImpl implements FoodProductService {

    private final FoodProductRepository repository;

    private final CommonValidation commonValidation;

    @Override
    public boolean add(FoodProductEntity product) {

        commonValidation.validateFoodProduct(product);
        commonValidation.validateCategoryId(product.getCategoryId());

        product.forCreate();

        repository.save(product);

        return true;
    }

    @Override
    public FoodProductEntity getById(Long id) {
        return commonValidation.validateFoodProduct(id);
    }

    @Override
    public boolean update(FoodProductEntity updateEntity) {

        FoodProductEntity updateEntityDB = commonValidation.validateFoodProductUpdate(updateEntity);
        repository.save(updateEntityDB);

        return true;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commonValidation.validateFoodProductId(id);
        repository.delete(id);
    }

    @Override
    public Page<FoodProductEntity> getFilterPage(FilterForm filter) {

        Sort sort = orderSortField("id");

        Pageable pageable = pageable(sort, filter);
        Map<String, Object> filterMap = filter.getFilter();

        String name = null;
        Long categoryId = null;
        Double quantity = null;

        if (filterMap != null) {

            if (filterMap.containsKey("categoryId")) {
                categoryId = MapUtils.getLong(filterMap, "categoryId");
            }
            if(filterMap.containsKey("name")){
                name=MapUtils.getString(filterMap, "name");
            }

        }

        return repository.getPageFoodProduct(categoryId, name,pageable);
    }

    @Override
    public Integer amountInCategory(Long id) {
        List<FoodProductEntity> allByCategoryAmount = repository.findAllByCategoryAmount(id);
        return allByCategoryAmount.size();
    }


    public Sort orderSortField(String field) {
        return Sort.by(Sort.Order.by(field));
    }

    public Pageable pageable(Sort sort, FilterForm filterForm) {
        return PageRequest.of(filterForm.getStart() / filterForm.getLength(), filterForm.getLength(), sort);
    }
}
