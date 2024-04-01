package uz.ofs.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.ofs.constants.Role;

import java.util.List;

@Getter
@Setter
public class UserUpdateDto {

    @NotNull(message = "user id must not be null")
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private List<Role> roleList;
}
