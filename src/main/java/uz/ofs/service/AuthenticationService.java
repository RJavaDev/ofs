package uz.ofs.service;

import uz.ofs.dto.request.LoginRequestDto;
import uz.ofs.dto.response.TokenResponseDto;
import uz.ofs.entity.UserEntity;

public interface AuthenticationService {


    TokenResponseDto register(UserEntity userEntity);

    TokenResponseDto authenticate(LoginRequestDto request);
}
