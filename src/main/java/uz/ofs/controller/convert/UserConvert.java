package uz.ofs.controller.convert;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import uz.ofs.constants.Role;
import uz.ofs.dto.UserDto;
import uz.ofs.dto.request.UserCreateRequestDto;
import uz.ofs.dto.request.UserUpdateDto;
import uz.ofs.entity.UserEntity;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class UserConvert {


    public UserDto convertToDto(UserEntity user) {
        UserDto userDto = new UserDto();

        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setId(user.getId());
        userDto.setStatus(user.getStatus());
        userDto.setCreatedBy(user.getCreatedBy());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setRoleEnumList(user.getRoleList());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public UserEntity convertToEntity(UserCreateRequestDto dto) {
        UserEntity user = new UserEntity();

        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoleList(setRoleEnum(dto.getRole()));

        return user;
    }

    public UserEntity convertToEntity(UserUpdateDto dto) {
        UserEntity user = new UserEntity();

        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoleList(setRoleEnum(dto.getRoleList()));

        return user;
    }


    public List<UserDto> convertToDto(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(UserConvert::convertToDto).toList();
    }

    private List<Role> setRoleEnum(List<Role> roleEnums) {
        return roleEnums == null ? Collections.singletonList(Role.USER) : roleEnums;
    }


    public List<UserDto> convertToDto(Page<UserEntity> entity) {
        return entity.stream().map(UserConvert::convertToDto).toList();
    }
}
