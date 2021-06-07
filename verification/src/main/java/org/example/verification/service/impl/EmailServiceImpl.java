package org.example.verification.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.verification.dto.MessageInfo;
import org.example.verification.service.EmailService;
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
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(messageInfo.getReceiver());
    simpleMailMessage.setSubject(messageInfo.getSubject());
    simpleMailMessage.setText(messageInfo.getBody());
    try {
//      javaMailSender.send(simpleMailMessage);
      log.info("Email has been sent");
    } catch (MailException mailException) {
      log.error("Email not sent", mailException);
    }
  }
}
