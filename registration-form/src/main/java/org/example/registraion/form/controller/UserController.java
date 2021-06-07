package org.example.registraion.form.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.registraion.form.dto.UserDto;
import org.example.registraion.form.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@Valid @RequestBody UserDto userDto) {
    userService.register(userDto);
    return ResponseEntity.ok().build();
  }
}
