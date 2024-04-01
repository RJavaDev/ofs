package uz.ofs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
import org.springframework.validation.annotation.Validated;


@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "This Controller for login and register")
public class AuthenticationController {

    private final AuthenticationService service;


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "User Registration", description = "This method is used for user registration")
    @PostMapping("/register")
    public ApiResponse<Object> register(@RequestBody @Validated UserCreateRequestDto userDto) {

        UserEntity userEntity = UserConvert.convertToEntity(userDto);
        TokenResponseDto register = service.register(userEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(register)
                .message("successfully!!!");
    }

    @Operation(summary = "User Login", description = "This method is used for user authentication and login")
    @PostMapping("/login")
    public ApiResponse<Object> authenticate(@RequestBody @Validated LoginRequestDto request) {

        TokenResponseDto authenticate = service.authenticate(request);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(authenticate)
                .message("successfully!!!");

    }


}
