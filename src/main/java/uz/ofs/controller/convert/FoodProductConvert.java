package uz.ofs.controller.convert;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import uz.ofs.dto.FoodProductDto;
import uz.ofs.dto.request.FoodProductCreateDto;
import uz.ofs.dto.request.FoodProductUpdateDto;
import uz.ofs.entity.FoodProductEntity;

import java.util.List;

@UtilityClass
public class FoodProductConvert {

    public FoodProductEntity convertToEntity(FoodProductCreateDto dto){
        FoodProductEntity entity = new FoodProductEntity();

        BeanUtils.copyProperties(entity, dto);

        return entity;
    }

    public FoodProductEntity convertToEntity(FoodProductUpdateDto dto){
        FoodProductEntity entity = new FoodProductEntity();

        BeanUtils.copyProperties(entity, dto);

        return entity;
    }

    public FoodProductDto convertToDto(FoodProductEntity entity) {
        FoodProductDto dto = new FoodProductDto();

        BeanUtils.copyProperties(dto, entity, "category");

        dto.setCategory(CategoryConvert.fromNoChild(entity.getCategory()));

        return dto;
    }

    public List<FoodProductDto> convertToDto(List<FoodProductEntity> entityList){
        return entityList.stream().map(FoodProductConvert::convertToDto).toList();
    }
}
