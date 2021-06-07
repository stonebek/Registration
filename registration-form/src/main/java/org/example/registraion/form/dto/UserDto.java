package org.example.registraion.form.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @NotNull(message = "login must be not null")
  private String login;

  @NotNull(message = "password must be not null")
  private String password;

  @Email
  @NotNull(message = "email must be not null")
  private String email;

  private String fullName;
}
