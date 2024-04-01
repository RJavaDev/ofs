package uz.ofs.dto.dtoUtil;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class LocalDateTimeUtil {

    public LocalDateTime parseLocalDateTimeDMY(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
