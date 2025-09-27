package com.innowise.userservice.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for User entity.
 * Used for API communication and validation.
 *
 * @author tonny327
 * @version 1.0
 */
@Data
public class UserDto {

  private Long id;

  @NotBlank(message = "Name cannot be blank")
  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
  private String name;

  @NotBlank(message = "Surname cannot be blank")
  @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
  private String surname;

  @NotNull(message = "Birth date cannot be null")
  @Past(message = "Birth date must be in the past")
  private LocalDate birthDate;

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email must be valid")
  @Size(max = 255, message = "Email must not exceed 255 characters")
  private String email;

  private List<CardDto> cards;
}
