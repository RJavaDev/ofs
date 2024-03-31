package uz.ofs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.controller.convert.UserConvert;
import uz.ofs.dto.UserDto;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.entity.UserEntity;
import uz.ofs.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/get/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id){
        UserEntity entity = service.getById(id);
        UserDto dto = UserConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }
}
