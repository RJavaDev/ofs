package uz.ofs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class FoodProductCreateDto {

    @NotBlank(message = "name must not null")
    private String name;

    @NotNull(message = "quantity must not be null")
    private Double quantity;

    @NotBlank(message = "storage period must not null")
    @Schema(name = "storagePeriod", example = "\"date\":\"yyyy-MM-dd HH:mm\"}")
    private String storagePeriod;

    @NotNull(message = "categoryId must not null")
    private Long categoryId;
}
