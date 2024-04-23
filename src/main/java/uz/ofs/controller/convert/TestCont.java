package uz.ofs.controller.convert;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.dto.dtoUtil.ApiResponse;

@RestController
@RequestMapping("/test")
public class TestCont {

    @GetMapping("/")
    public ApiResponse<Object> get(){
        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(Boolean.TRUE)
                .message(ResponseMessage.OK);
    }

    @PostMapping("/")
    public ApiResponse<Object> post(@RequestBody @Valid TestDto dto){
        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto.getName())
                .message(ResponseMessage.OK);
    }
}
