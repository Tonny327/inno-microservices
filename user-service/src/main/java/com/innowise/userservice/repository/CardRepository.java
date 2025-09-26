package com.innowise.userservice.repository;

import com.innowise.userservice.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import  java.util.List;
import java.util.Optional;

/**
 * Repository interface for Card entity operations.
 * Provides methods for querying cards using different query types:
 * derived methods, JPQL queries, and native SQL queries.
 * 
 * @author tonny327
 * @version 1.0
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * Finds cards by a list of card IDs using derived query method.
     * 
     * @param ids list of card IDs to search for
     * @return list of cards matching the provided IDs
     */
    List<Card> findByIdIn(List<Long> ids);

    /**
     * Finds cards by a list of user IDs using JPQL query.
     * 
     * @param userIds list of user IDs to search for
     * @return list of cards belonging to the specified users
     */
    @Query("SELECT c FROM Card c WHERE c.user.id IN :userIds")
    List<Card> findByUserIdIn(List<Long> userIds);

    /**
     * Finds a card by its number using named native query.
     * 
     * @param number the card number to search for
     * @return Optional containing the card if found, empty otherwise
     */
    @Query(name = "Card.findByNumberNative", nativeQuery = true)
    Optional<Card> findByNumberNative(@Param("number") String number);

    /**
     * Counts cards by holder name using native SQL query.
     * 
     * @param holder the card holder name to count
     * @return number of cards for the specified holder
     */
    @Query(value = "SELECT COUNT(*) FROM card_info WHERE holder = :holder", nativeQuery = true)
    long countByHolderNative(@Param("holder") String holder);
}
