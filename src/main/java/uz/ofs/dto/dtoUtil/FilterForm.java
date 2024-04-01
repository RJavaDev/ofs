package uz.ofs.dto.dtoUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class FilterForm implements Serializable {

    private Integer start;

    private Integer length;

    @Schema(name = "filter", example = "\"name\":\"ravshan\" or \"id\":123 or \"date\":\"12-04-2023\"")
    private Map<String, Object> filter;
}
