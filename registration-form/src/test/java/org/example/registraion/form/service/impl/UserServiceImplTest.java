package org.example.registraion.form.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.registraion.form.dao.entity.User;
import org.example.registraion.form.dao.repository.UserRepository;
import org.example.registraion.form.dto.UserDto;
import org.example.registraion.form.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  public void registerWithNullArgument() {
    assertThrows(IllegalArgumentException.class, () -> userService.register(null));
  }

  @Test
  public void registerWithValidArgument() throws JsonProcessingException {
    doNothing().when(rabbitTemplate).convertAndSend(any(), any(), anyString());
    when(userRepository.save(any(User.class))).thenReturn(new User());
    when(objectMapper.writeValueAsString(any(UserInfo.class))).thenReturn("test value");

    UserDto userDto = new UserDto("login", "password", "email@gmail.com", "John Smith");
    userService.register(userDto);

    verify(rabbitTemplate, times(1)).convertAndSend(any(), any(), anyString());
    verify(userRepository, times(1)).save(any());
  }
}