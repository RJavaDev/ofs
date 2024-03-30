package uz.ofs.dto;

import lombok.Getter;
import lombok.Setter;
import uz.ofs.dto.baseDto.BaseDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodProductDto extends BaseDto {

    private String name;

    private Double quantity;

    private LocalDateTime storagePeriod;

    private CategoryDto category;

}
