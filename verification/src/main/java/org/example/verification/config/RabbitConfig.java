package org.example.verification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {

  @Value("${email.rabbitmq.queue}")
  private String queueName;

  @Value("${email.rabbitmq.exchange}")
  String exchange;

  @Value("${email.rabbitmq.routingkey}")
  private String routingkey;

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingkey);
  }
}
