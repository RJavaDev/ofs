package uz.ofs.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TestDto {

    @NotNull(message = "idddd")
    private Long name;
}
