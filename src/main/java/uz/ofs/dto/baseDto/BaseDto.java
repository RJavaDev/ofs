package uz.ofs.dto.baseDto;

import lombok.Getter;
import lombok.Setter;
import uz.ofs.constants.EntityStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDto {
    private Long id;

    private EntityStatus status;

    private LocalDateTime createdDate;

    private Long createdBy;
}
