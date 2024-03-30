package uz.ofs.controller.convert;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import uz.ofs.constants.EntityStatus;
import uz.ofs.dto.CategoryDto;
import uz.ofs.dto.request.CategoryCreatedRequestDto;
import uz.ofs.dto.request.CategoryUpdate;
import uz.ofs.entity.CategoryEntity;

import java.util.List;

@UtilityClass
public class CategoryConvert {
    public CategoryEntity convertToEntity(CategoryCreatedRequestDto dto) {
        CategoryEntity entity = new CategoryEntity();

        entity.setName(dto.getName());
        entity.setParentId(dto.getParentId());

        return entity;
    }

    public CategoryEntity convertToEntity(CategoryUpdate dto) {
        CategoryEntity entity = new CategoryEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setParentId(dto.getParentId());

        return entity;
    }

    public CategoryDto convertToDto(CategoryEntity entity){
        CategoryDto dto = new CategoryDto();

        BeanUtils.copyProperties(dto, entity, "children");
        dto.setChildren(convertToDto(entity.getChildren()));

        return dto;
    }


    public CategoryDto fromOneLevelChild(CategoryEntity entity){
        return entity.getDto(true);
    }

    public CategoryDto fromNoChild(CategoryEntity entity){
        return entity.getDto();
    }

    public List<CategoryDto> fromOneLevelChild(List<CategoryEntity> entityList){
        return entityList.stream().map(CategoryConvert::fromOneLevelChild).toList();
    }


    public CategoryDto fromTree(CategoryEntity entity){

        CategoryDto dto = convertToDto(entity);

        dto.setChildren(fromTree(entity.getChildren()));

        return dto;
    }

    public List<CategoryDto> fromTree(List<CategoryEntity> entityList){
        return entityList.stream().map(CategoryConvert::fromTree)
                .filter(p -> p.getStatus() != EntityStatus.DELETED).toList();
    }

    public List<CategoryDto> convertToDto(List<CategoryEntity> entityList){
        return entityList.stream().map(CategoryConvert::convertToDto)
                .filter(c -> c.getStatus() != EntityStatus.DELETED).toList();
    }
}
