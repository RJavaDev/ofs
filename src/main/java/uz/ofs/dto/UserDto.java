package uz.ofs.dto;

import lombok.Getter;
import lombok.Setter;
import uz.ofs.constants.Role;
import uz.ofs.dto.baseDto.BaseDto;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto {

    private String firstname;

    private String lastname;

    private String username;


    private List<Role> roleEnumList;

}
