package uz.ofs.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodProductCreateDto {

    @NotNull(message = "name must not null")
    private String name;

    @NotBlank(message = "quantity must not null")
    private Double quantity;

    @NotNull(message = "storage period must not null")
    private LocalDateTime storagePeriod;

    @NotBlank(message = "quantity must not null")
    private Long categoryId;
}
