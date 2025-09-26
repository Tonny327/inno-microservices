package com.innowise.userservice.service;

import com.innowise.userservice.model.entity.User;
import com.innowise.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

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
public class UserService {

  private final UserRepository userRepository;

  /**
   * Constructs a UserService with the specified UserRepository.
   * 
   * @param userRepository the repository for user data access
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Creates a new user in the system.
   * 
   * @param user the user entity to create
   * @return the created user with generated ID
   */
  public User createUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Retrieves a user by their unique identifier.
   * 
   * @param id the user ID to search for
   * @return Optional containing the user if found, empty otherwise
   */
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  /**
   * Retrieves multiple users by their IDs.
   * 
   * @param ids list of user IDs to search for
   * @return list of users matching the provided IDs
   */
  public List<User> getUsersByIds(List<Long> ids) {
    return userRepository.findAllByIds(ids);
  }

  /**
   * Retrieves a user by their email address.
   * 
   * @param email the email address to search for
   * @return Optional containing the user if found, empty otherwise
   */
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  /**
   * Updates an existing user with new information.
   * 
   * @param id the ID of the user to update
   * @param updatedUser the user entity with updated information
   * @return the updated user entity
   * @throws RuntimeException if user with the specified ID is not found
   */
  public User updateUser(Long id, User updatedUser){
    return userRepository.findById(id)
        .map(user -> {
          user.setName(updatedUser.getName());
          user.setSurname(updatedUser.getSurname());
          user.setBirthDate(updatedUser.getBirthDate());
          user.setEmail(updatedUser.getEmail());
          return userRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  /**
   * Deletes a user by their ID.
   * This operation will also delete all associated cards due to cascade configuration.
   * 
   * @param id the ID of the user to delete
   */
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
