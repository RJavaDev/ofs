package uz.ofs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ofs.constants.Role;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class UserCreateRequestDto{

    @NotBlank(message = "firstname must not be null!!!")
    private String firstname;

    private String lastname;

    @NotBlank(message = "username must not be null!!!")
    @Schema(name = "username", example = "ravshanebek9918")
    private String username;

    private List<Role> role;

    @NotBlank(message = "password must not be null!!!")
    private String password;

}
