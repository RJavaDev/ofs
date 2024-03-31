package uz.ofs.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodProductUpdateDto {

    @NotNull(message = "id not null")
    private Long id;

    private String name;

    private Double quantity;

    private String storagePeriod;

    private Long categoryId;
}
