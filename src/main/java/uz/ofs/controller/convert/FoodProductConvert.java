package uz.ofs.controller.convert;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import uz.ofs.dto.FoodProductDto;
import uz.ofs.dto.dtoUtil.LocalDateTimeUtil;
import uz.ofs.dto.request.FoodProductCreateDto;
import uz.ofs.dto.request.FoodProductUpdateDto;
import uz.ofs.entity.FoodProductEntity;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class FoodProductConvert {

    public FoodProductEntity convertToEntity(FoodProductCreateDto dto){
        FoodProductEntity entity = new FoodProductEntity();

        BeanUtils.copyProperties(dto, entity,"storagePeriod");

        String storagePeriod = dto.getStoragePeriod();
        LocalDateTime localDateTime = LocalDateTimeUtil.parseLocalDateTimeDMY(storagePeriod);
        entity.setStoragePeriod(localDateTime);

        return entity;
    }

    public FoodProductEntity convertToEntity(FoodProductUpdateDto dto){
        FoodProductEntity entity = new FoodProductEntity();

        BeanUtils.copyProperties(dto, entity,"storagePeriod");

        String storagePeriod = dto.getStoragePeriod();
        LocalDateTime localDateTime = LocalDateTimeUtil.parseLocalDateTimeDMY(storagePeriod);
        entity.setStoragePeriod(localDateTime);

        return entity;
    }

    public FoodProductDto convertToDto(FoodProductEntity entity) {
        FoodProductDto dto = new FoodProductDto();

        BeanUtils.copyProperties(entity, dto, "category");

        dto.setCategory(CategoryConvert.fromNoChild(entity.getCategory()));

        return dto;
    }

    public List<FoodProductDto> convertToDto(List<FoodProductEntity> entityList){
        return entityList.stream().map(FoodProductConvert::convertToDto).toList();
    }

    public static List<FoodProductDto> convertToDto(Page<FoodProductEntity> entity) {
        return entity.stream().map(FoodProductConvert::convertToDto).toList();
    }
}
