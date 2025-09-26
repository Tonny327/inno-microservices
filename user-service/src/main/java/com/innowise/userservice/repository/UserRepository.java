package com.innowise.userservice.repository;


import com.innowise.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations.
 * Provides methods for querying users using different query types:
 * derived methods, JPQL queries, and named queries.
 * 
 * @author tonny327
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email address using derived query method.
     * 
     * @param email the email address to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds users by a list of IDs using JPQL query.
     * 
     * @param ids list of user IDs to search for
     * @return list of users matching the provided IDs
     */
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    List<User> findAllByIds(List<Long> ids);

    /**
     * Finds users by surname using named query.
     * 
     * @param surname the surname to search for
     * @return list of users with the specified surname
     */
    @Query(name = "User.findBySurnameNamed")
    List<User> findBySurnameNamed(@Param("surname") String surname);
}
