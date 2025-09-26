package com.innowise.userservice.service;

import com.innowise.userservice.model.entity.Card;
import com.innowise.userservice.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Card entities.
 * Provides business logic for card operations including CRUD operations.
 * 
 * @author tonny327
 * @version 1.0
 */
@Service
public class CardService {

  private final CardRepository cardRepository;

  /**
   * Constructs a CardService with the specified CardRepository.
   * 
   * @param cardRepository the repository for card data access
   */
  public CardService(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  /**
   * Creates a new card in the system.
   * 
   * @param card the card entity to create
   * @return the created card with generated ID
   */
  public Card createCard(Card card) {
    return cardRepository.save(card);
  }

  /**
   * Retrieves a card by its unique identifier.
   * 
   * @param id the card ID to search for
   * @return Optional containing the card if found, empty otherwise
   */
  public Optional<Card> getCardById(Long id) {
    return cardRepository.findById(id);
  }

  /**
   * Retrieves multiple cards by their IDs.
   * 
   * @param ids list of card IDs to search for
   * @return list of cards matching the provided IDs
   */
  public List<Card> getCardsByIds(List<Long> ids) {
    return cardRepository.findByIdIn(ids);
  }

  /**
   * Retrieves cards belonging to specified users.
   * 
   * @param userIds list of user IDs to search for
   * @return list of cards belonging to the specified users
   */
  public List<Card> getCardsByUserIds(List<Long> userIds) {
    return cardRepository.findByUserIdIn(userIds);
  }

  /**
   * Updates an existing card with new information.
   * 
   * @param id the ID of the card to update
   * @param updatedCard the card entity with updated information
   * @return the updated card entity
   * @throws RuntimeException if card with the specified ID is not found
   */
  public Card updateCard(Long id, Card updatedCard){
    return cardRepository.findById(id)
        .map(card -> {
          card.setNumber(updatedCard.getNumber());
          card.setHolder(updatedCard.getHolder());
          card.setExpirationDate(updatedCard.getExpirationDate());
          return cardRepository.save(card);
        })
        .orElseThrow(() -> new RuntimeException("Card not found"));
  }

  /**
   * Deletes a card by its ID.
   * 
   * @param id the ID of the card to delete
   */
  public void deleteCard(Long id){
    cardRepository.deleteById(id);
  }
}
