package uz.ofs.dto.dtoUtil;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class LocalDateTimeUtil {

    public LocalDateTime parseLocalDateTimeDMY(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.parse(dateString + "T00:00:00", formatter);
    }
}
