package uz.ofs.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodProductUpdateDto {

    private String name;

    private Double quantity;

    private LocalDateTime storagePeriod;

    private Long categoryId;
}
