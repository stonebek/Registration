package org.example.email.service.dto;

import lombok.Value;

@Value
public class MessageInfo {
  String receiver;
  String subject;
  String body;
}
