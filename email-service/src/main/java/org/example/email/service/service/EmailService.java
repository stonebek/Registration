package org.example.email.service.service;


import org.example.email.service.dto.MessageInfo;

public interface EmailService {

  void sendEmail(MessageInfo messageInfo);
}
