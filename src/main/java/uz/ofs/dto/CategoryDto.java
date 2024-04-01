package uz.ofs.dto;

import lombok.Getter;
import lombok.Setter;
import uz.ofs.dto.baseDto.BaseDto;

import java.util.List;

@Getter
@Setter
public class CategoryDto extends BaseDto {

    private String name;

    private Long parentId;

    private CategoryDto parent;

    private List<CategoryDto> children;

}
