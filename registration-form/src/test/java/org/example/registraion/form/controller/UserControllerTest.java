package org.example.registraion.form.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

  @Autowired
  private UserController controller;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  void whenValidInput_thenReturns200() throws Exception {
    mockMvc.perform(post("/user/register")
    .contentType("application/json")).
    andExpect(status().isOk());
  }
}