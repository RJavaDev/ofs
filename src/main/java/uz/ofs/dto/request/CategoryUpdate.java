package uz.ofs.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryUpdate {
    @NotBlank(message = "category ID must not be empty")
    private Long id;
    private Long parentId;
    private String name;
}
