package org.example.verification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.verification.dto.MessageInfo;
import org.example.verification.dto.UserInfo;
import org.example.verification.service.EmailService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationRabbitListener {

  private final ObjectMapper objectMapper;
  private final EmailService emailService;

  @RabbitListener(queues = "${register.rabbitmq.queue}")
  public void receive(Message message) throws IOException {
    UserInfo userInfo = objectMapper.readValue(message.getBody(), UserInfo.class);
    String status = new Random().nextBoolean() ? "GOOD" : "BAD";
    String messageBody = String.format("Dear %s, You request was %s", userInfo.getFullName(), status);
    log.info(messageBody);

    MessageInfo messageInfo = new MessageInfo(userInfo.getEmail(), "Notification", messageBody);
    emailService.sendEmail(messageInfo);
  }
}
