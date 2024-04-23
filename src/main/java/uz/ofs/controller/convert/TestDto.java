package uz.ofs.controller.convert;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TestDto {

    @NotNull(message = "null bolmasligi kerak")
    private String name;
}
