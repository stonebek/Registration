package org.example.verification.dto;

import lombok.Value;

@Value
public class MessageInfo {
  String receiver;
  String subject;
  String body;
}
