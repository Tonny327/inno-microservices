package com.innowise.userservice.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for Card entity.
 * Used for API communication and validation.
 *
 * @author tonny327
 * @version 1.0
 */
@Data
public class CardDto {

  private Long id;

  @NotBlank(message = "Card number cannot be blank")
  @Pattern(regexp = "^\\d{16}$", message = "Card number must be exactly 16 digits")
  private String number;

  @NotBlank(message = "Card holder cannot be blank")
  @Size(min = 2, max = 255, message = "Card holder must be between 2 and 255 characters")
  private String holder;

  @NotNull(message = "Expiration date cannot be null")
  @Future(message = "Expiration date must be in the future")
  private LocalDate expirationDate;

  private Long userId;
}