package uz.ofs.dto.dtoUtil;

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

    private Map<String, Object> filter;
}
