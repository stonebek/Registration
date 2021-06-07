package org.example.email.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.email.service.dto.MessageInfo;
import org.example.email.service.service.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender javaMailSender;

  @Override
  public void sendEmail(MessageInfo messageInfo) {
    if(messageInfo == null) {
      throw new IllegalArgumentException();
    }
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(messageInfo.getReceiver());
    simpleMailMessage.setSubject(messageInfo.getSubject());
    simpleMailMessage.setText(messageInfo.getBody());
    try {
//      javaMailSender.send(simpleMailMessage);
//      заглушка для отправки email с результатами проверки от verification сервиса
      log.info("Email has been sent");
    } catch (MailException mailException) {
      log.error("Email not sent", mailException);
    }
  }
}
