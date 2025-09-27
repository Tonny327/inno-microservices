package com.innowise.userservice.controller;

import com.innowise.userservice.model.dto.CardDto;
import com.innowise.userservice.service.CardService;
import jakarta.validation.Valid;
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
   * @param cardDto the card DTO to create
   * @return ResponseEntity containing the created card DTO
   */
  @PostMapping
  public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardDto cardDto) {
    return ResponseEntity.ok(cardService.createCard(cardDto));
  }

  /**
   * Retrieves a card by ID.
   *
   * @param id the card ID to search for
   * @return ResponseEntity containing the card DTO if found, 404 if not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
    return cardService.getCardById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves cards belonging to specified users.
   *
   * @param userIds list of user IDs to search for
   * @return ResponseEntity containing the list of card DTOs belonging to the specified users
   */
  @GetMapping
  public ResponseEntity<List<CardDto>> getCardsByUserIds(@RequestParam List<Long> userIds) {
    return ResponseEntity.ok(cardService.getCardsByUserIds(userIds));
  }

  /**
   * Retrieves multiple cards by their IDs.
   *
   * @param ids list of card IDs to search for
   * @return ResponseEntity containing the list of card DTOs matching the provided IDs
   */
  @GetMapping("/by-ids")
  public ResponseEntity<List<CardDto>> getCardsByIds(@RequestParam List<Long> ids) {
    return ResponseEntity.ok(cardService.getCardsByIds(ids));
  }

  /**
   * Updates an existing card.
   *
   * @param id the ID of the card to update
   * @param cardDto the card DTO with updated information
   * @return ResponseEntity containing the updated card DTO
   */
  @PutMapping("/{id}")
  public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @Valid @RequestBody CardDto cardDto) {
    return ResponseEntity.ok(cardService.updateCard(id, cardDto));
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
