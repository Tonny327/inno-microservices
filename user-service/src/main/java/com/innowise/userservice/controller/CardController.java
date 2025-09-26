package com.innowise.userservice.controller;


import com.innowise.userservice.model.entity.Card;
import com.innowise.userservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Card entities.
 * Provides HTTP endpoints for card CRUD operations.
 * 
 * @author tonny327
 * @version 1.0
 */
@RestController
@RequestMapping("/cards")
public class CardController {

  private final CardService cardService;

  /**
   * Constructs a CardController with the specified CardService.
   * 
   * @param cardService the service for card business logic
   */
  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  /**
   * Creates a new card.
   * 
   * @param card the card entity to create
   * @return ResponseEntity containing the created card
   */
  @PostMapping
  public ResponseEntity<Card> createCard(@RequestBody Card card) {
    return ResponseEntity.ok(cardService.createCard(card));
  }

  /**
   * Retrieves a card by ID.
   * 
   * @param id the card ID to search for
   * @return ResponseEntity containing the card if found, 404 if not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<Card> getCardById(@PathVariable Long id) {
    return cardService.getCardById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves cards belonging to specified users.
   * 
   * @param userIds list of user IDs to search for
   * @return ResponseEntity containing the list of cards belonging to the specified users
   */
  @GetMapping
  public ResponseEntity<List<Card>> getCardsByUserIds(@RequestParam List<Long> userIds) {
    return ResponseEntity.ok(cardService.getCardsByUserIds(userIds));
  }

  /**
   * Retrieves multiple cards by their IDs.
   * 
   * @param ids list of card IDs to search for
   * @return ResponseEntity containing the list of cards matching the provided IDs
   */
  @GetMapping("/by-ids")
  public ResponseEntity<List<Card>> getCardsByIds(@RequestParam List<Long> ids) {
    return ResponseEntity.ok(cardService.getCardsByIds(ids));
  }

  /**
   * Updates an existing card.
   * 
   * @param id the ID of the card to update
   * @param card the card entity with updated information
   * @return ResponseEntity containing the updated card
   */
  @PutMapping("/{id}")
  public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
    return ResponseEntity.ok(cardService.updateCard(id, card));
  }

  /**
   * Deletes a card by ID.
   * 
   * @param id the ID of the card to delete
   * @return ResponseEntity with no content (204) if successful
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
    cardService.deleteCard(id);
    return ResponseEntity.noContent().build();
  }
}
