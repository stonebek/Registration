package org.example.verification.service;

import org.example.verification.dto.MessageInfo;

public interface EmailService {

  void sendEmail(MessageInfo messageInfo);
}
