package org.example.verification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.verification.dto.UserInfo;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationRabbitListener {

  private final ObjectMapper objectMapper;
  private final RabbitTemplate rabbitTemplate;

  @Value("${email.rabbitmq.exchange}")
  private String exchange;

  @Value("${email.rabbitmq.routingkey}")
  private String routingkey;

  @RabbitListener(queues = "${register.rabbitmq.queue}")
  public void receive(Message message) {
    try {
      UserInfo userInfo = objectMapper.readValue(message.getBody(), UserInfo.class);
      log.info("Received message {}", userInfo);
      String status = new Random().nextBoolean() ? "GOOD" : "BAD";
      userInfo.setStatus(status);
      String rabbitMessage = objectMapper.writeValueAsString(userInfo);
      rabbitTemplate.convertAndSend(exchange, routingkey, rabbitMessage);
    } catch (IOException e) {
      log.error("Failed to deserialized message {}", e.getMessage());
    } catch (AmqpException e) {
      log.error("Failed to send rabbit message {}", e.getMessage());
    }
  }
}
