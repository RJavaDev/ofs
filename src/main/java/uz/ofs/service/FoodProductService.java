package uz.ofs.service;

import org.springframework.data.domain.Page;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.entity.FoodProductEntity;

import java.util.Map;

public interface FoodProductService extends BaseService<FoodProductEntity>{

    boolean add(FoodProductEntity entity);

    Page<FoodProductEntity> getFilterPage(FilterForm filterForm);

    Integer amountInCategory(Long id);
}
