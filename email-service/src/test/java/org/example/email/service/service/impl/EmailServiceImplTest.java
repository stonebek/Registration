package org.example.email.service.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

  @InjectMocks
  private EmailServiceImpl emailService;

  @Test
  void sendEmailWithNullArgumentShouldThrowException() {
    assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail(null));
  }
}