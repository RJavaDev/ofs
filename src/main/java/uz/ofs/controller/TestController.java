package uz.ofs.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/post")
    public Boolean post(@RequestBody @Valid TestDto dto){
        return Boolean.TRUE;
    }

    @GetMapping("/get")
    public Boolean get(@RequestBody @Valid TestDto dto){
        return Boolean.TRUE;
    }
}
