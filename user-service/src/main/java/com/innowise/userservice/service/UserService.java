package com.innowise.userservice.service;

import com.innowise.userservice.model.dto.UserDto;
import com.innowise.userservice.model.entity.User;
import com.innowise.userservice.repository.UserRepository;
import com.innowise.userservice.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing User entities.
 * Provides business logic for user operations including CRUD operations.
 * 
 * @author tonny327
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  /**
   * Constructs a UserService with the specified UserRepository.
   * 
   * @param userRepository the repository for user data access
   * @param userMapper the mapper for converting between User and UserDto
   */
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  /**
   * Creates a new user in the system.
   *
   * @param userDto the user DTO to create
   * @return the created user DTO with generated ID
   */
  @Transactional
  public UserDto createUser(UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }
  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id the user ID to search for
   * @return Optional containing the user DTO if found, empty otherwise
   */
  public Optional<UserDto> getUserById(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto);
  }

  /**
   * Retrieves multiple users by their IDs.
   *
   * @param ids list of user IDs to search for
   * @return list of user DTOs matching the provided IDs
   */
  public List<UserDto> getUsersByIds(List<Long> ids) {
    return userRepository.findAllByIds(ids)
        .stream()
        .map(userMapper::toDto)
        .toList();
  }
  /**
   * Retrieves a user by their email address.
   *
   * @param email the email address to search for
   * @return Optional containing the user DTO if found, empty otherwise
   */
  public Optional<UserDto> getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(userMapper::toDto);
  }

  /**
   * Updates an existing user with new information.
   *
   * @param id the ID of the user to update
   * @param updatedUserDto the user DTO with updated information
   * @return the updated user DTO
   * @throws RuntimeException if user with the specified ID is not found
   */
  @Transactional
  public UserDto updateUser(Long id, UserDto updatedUserDto) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    userMapper.updateEntity(updatedUserDto, existingUser);
    User savedUser = userRepository.save(existingUser);
    return userMapper.toDto(savedUser);
  }


  /**
   * Deletes a user by their ID.
   * This operation will also delete all associated cards due to cascade configuration.
   *
   * @param id the ID of the user to delete
   */
  @Transactional
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
