package uz.ofs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.controller.convert.UserConvert;
import uz.ofs.dto.UserDto;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.dto.request.UserUpdateDto;
import uz.ofs.entity.UserEntity;
import uz.ofs.service.UserService;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@RestController
@EnableMethodSecurity
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "The method", description = "Get user by id")
    @GetMapping("/get/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id){
        UserEntity entity = service.getById(id);
        UserDto dto = UserConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "The post", description = "Get user filter firstname and username")
    @PostMapping("/filter/page")
    public ApiResponse<Object> get(@RequestBody FilterForm filter){

        Page<UserEntity> entity = service.getFilterPage(filter);
        List<UserDto> dtoList = UserConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dtoList)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "This method Patch", description = "Update user")
    @PatchMapping("/update")
    public ApiResponse<Object> update(@RequestBody @Validated UserUpdateDto updateDto){

        UserEntity userEntity = UserConvert.convertToEntity(updateDto);
        boolean isUpdate = service.update(userEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "This method Delete", description = "Delete user by user id")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id){

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(ResponseMessage.DELETE_SUCCESS_MESSAGE)
                .message(ResponseMessage.OK);
    }


}
