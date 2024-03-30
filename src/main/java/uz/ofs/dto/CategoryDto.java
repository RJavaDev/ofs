package uz.ofs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ofs.dto.baseDto.BaseDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends BaseDto {

    @NotBlank(message = "name must not be empty")
    private String name;

    private Long parentId;

    private CategoryDto parent;

    private List<CategoryDto> children;

}
