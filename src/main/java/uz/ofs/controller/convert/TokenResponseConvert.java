package uz.ofs.controller.convert;

import lombok.experimental.UtilityClass;
import uz.ofs.dto.response.TokenResponseDto;
import uz.ofs.entity.UserEntity;

@UtilityClass
public class TokenResponseConvert {

    public TokenResponseDto from(String token, UserEntity user){
        return TokenResponseDto.builder()
                .token(token)
                .user(UserConvert.convertToDto(user))
                .build();
    }

}
