package org.example.registraion.form.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.registraion.form.dao.entity.User;
import org.example.registraion.form.dao.repository.UserRepository;
import org.example.registraion.form.dto.UserDto;
import org.example.registraion.form.dto.UserInfo;
import org.example.registraion.form.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  @Value("${register.rabbitmq.exchange}")
  private String exchange;

  @Value("${register.rabbitmq.routingkey}")
  private String routingkey;

  @Transactional
  @Override
  public void register(UserDto userDto) {
    if (userDto == null)  {
      throw new IllegalArgumentException();
    }
    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setFullName(userDto.getFullName());
    user.setLogin(userDto.getLogin());
    user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userRepository.save(user);

    UserInfo userInfo = new UserInfo(user.getLogin(), user.getEmail(), user.getFullName());
    try {
      String message = objectMapper.writeValueAsString(userInfo);
      rabbitTemplate.convertAndSend(exchange, routingkey, message);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
    }
  }
}
