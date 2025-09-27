package com.innowise.userservice.controller;

import com.innowise.userservice.model.dto.UserDto;
import com.innowise.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing User entities.
 * Provides HTTP endpoints for user CRUD operations.
 *
 * @author tonny327
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  /**
   * Constructs a UserController with the specified UserService.
   *
   * @param userService the service for user business logic
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Creates a new user.
   *
   * @param userDto the user DTO to create
   * @return ResponseEntity containing the created user DTO
   */
  @PostMapping
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.createUser(userDto));
  }

  /**
   * Retrieves a user by ID.
   *
   * @param id the user ID to search for
   * @return ResponseEntity containing the user DTO if found, 404 if not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return userService.getUserById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves multiple users by their IDs.
   *
   * @param ids list of user IDs to search for
   * @return ResponseEntity containing the list of user DTOs
   */
  @GetMapping
  public ResponseEntity<List<UserDto>> getUsersByIds(@RequestParam List<Long> ids) {
    return ResponseEntity.ok(userService.getUsersByIds(ids));
  }

  /**
   * Retrieves a user by email address.
   *
   * @param email the email address to search for
   * @return ResponseEntity containing the user DTO if found, 404 if not found
   */
  @GetMapping("/email")
  public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
    return userService.getUserByEmail(email)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Updates an existing user.
   *
   * @param id the ID of the user to update
   * @param userDto the user DTO with updated information
   * @return ResponseEntity containing the updated user DTO
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.updateUser(id, userDto));
  }

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user to delete
   * @return ResponseEntity with no content (204) if successful
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }
}
