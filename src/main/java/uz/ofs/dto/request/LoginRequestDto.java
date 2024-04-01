package uz.ofs.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {

  @NotBlank(message = "username must not be empty")
  private String username;

  @NotBlank(message = "password must not be empty")
  String password;
}
