package uz.ofs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ofs.constants.ResponseCode;
import uz.ofs.controller.convert.UserConvert;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.dto.request.LoginRequestDto;
import uz.ofs.dto.request.UserCreateRequestDto;
import uz.ofs.dto.response.TokenResponseDto;
import uz.ofs.entity.UserEntity;
import uz.ofs.service.AuthenticationService;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "This Controller for login and register")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "User Registration", description = "This method is used for user registration")
    @PostMapping("/register")
    public ApiResponse<Object> register(@RequestBody UserCreateRequestDto userDto) {

        UserEntity userEntity = UserConvert.convertToEntity(userDto);
        TokenResponseDto register = service.register(userEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(register)
                .message("successfully!!!");
    }

    @Operation(summary = "User Login", description = "This method is used for user authentication and login")
    @PostMapping("/login")
    public ApiResponse<Object> authenticate(@RequestBody LoginRequestDto request) {

        TokenResponseDto authenticate = service.authenticate(request);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(authenticate)
                .message("successfully!!!");

    }


}
