package uz.ofs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodProductUpdateDto {

    @NotNull(message = "user id must not be null")
    private Long id;

    private String name;

    private Double quantity;

    @Schema(name = "storagePeriod", example = "\"date\":\"yyyy-MM-dd HH:mm\"")
    private String storagePeriod;

    private Long categoryId;
}
