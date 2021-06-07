package org.example.registraion.form.config;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestControllerErrorMessage {
    private Integer statusCode;
    private LocalDate timestamp;
    private String message;
    private String description;
}
