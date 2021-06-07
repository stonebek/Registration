package org.example.email.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.email.service.dto.MessageInfo;
import org.example.email.service.dto.UserInfo;
import org.example.email.service.service.EmailService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailRabbitListener {

  private final ObjectMapper objectMapper;
  private final EmailService emailService;

  @RabbitListener(queues = "${email.rabbitmq.queue}")
  public void receive(Message message) throws IOException {
    try {
      UserInfo userInfo = objectMapper.readValue(message.getBody(), UserInfo.class);
      log.info("Received message {}", userInfo);

      String messageBody = String
          .format("Dear %s, Your request was %s", userInfo.getFullName(), userInfo.getStatus());
      MessageInfo messageInfo = new MessageInfo(userInfo.getEmail(), "Notification", messageBody);
      emailService.sendEmail(messageInfo);
    } catch  (IOException e) {
      log.error("Failed to deserialized message {}", e.getMessage());
    }
  }
}
