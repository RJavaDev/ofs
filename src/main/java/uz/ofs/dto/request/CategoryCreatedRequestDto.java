package uz.ofs.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CategoryCreatedRequestDto {

    @NotNull(message = "name must not be empty")
    private String name;

    private Long parentId;
}
