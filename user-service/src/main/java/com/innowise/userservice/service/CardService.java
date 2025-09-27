package com.innowise.userservice.service;

import com.innowise.userservice.model.dto.CardDto;
import com.innowise.userservice.model.entity.Card;
import com.innowise.userservice.repository.CardRepository;
import com.innowise.userservice.mapper.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class CardService {

  private final CardRepository cardRepository;
  private final CardMapper cardMapper;

  /**
   * Constructs a CardService with the specified CardRepository and CardMapper.
   *
   * @param cardRepository the repository for card data access
   * @param cardMapper the mapper for converting between Card and CardDto
   */
  public CardService(CardRepository cardRepository, CardMapper cardMapper) {
    this.cardRepository = cardRepository;
    this.cardMapper = cardMapper;
  }

  /**
   * Creates a new card in the system.
   *
   * @param cardDto the card DTO to create
   * @return the created card DTO with generated ID
   */
  @Transactional
  public CardDto createCard(CardDto cardDto) {
    Card card = cardMapper.toEntity(cardDto);
    Card savedCard = cardRepository.save(card);
    return cardMapper.toDto(savedCard);
  }

  /**
   * Retrieves a card by its unique identifier.
   *
   * @param id the card ID to search for
   * @return Optional containing the card DTO if found, empty otherwise
   */
  public Optional<CardDto> getCardById(Long id) {
    return cardRepository.findById(id)
        .map(cardMapper::toDto);
  }

  /**
   * Retrieves multiple cards by their IDs.
   *
   * @param ids list of card IDs to search for
   * @return list of card DTOs matching the provided IDs
   */
  public List<CardDto> getCardsByIds(List<Long> ids) {
    return cardRepository.findByIdIn(ids)
        .stream()
        .map(cardMapper::toDto)
        .toList();
  }

  /**
   * Retrieves cards belonging to specified users.
   *
   * @param userIds list of user IDs to search for
   * @return list of card DTOs belonging to the specified users
   */
  public List<CardDto> getCardsByUserIds(List<Long> userIds) {
    return cardRepository.findByUserIdIn(userIds)
        .stream()
        .map(cardMapper::toDto)
        .toList();
  }

  /**
   * Updates an existing card with new information.
   *
   * @param id the ID of the card to update
   * @param updatedCardDto the card DTO with updated information
   * @return the updated card DTO
   * @throws RuntimeException if card with the specified ID is not found
   */
  @Transactional
  public CardDto updateCard(Long id, CardDto updatedCardDto) {
    Card existingCard = cardRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Card not found"));

    cardMapper.updateEntity(updatedCardDto, existingCard);
    Card savedCard = cardRepository.save(existingCard);
    return cardMapper.toDto(savedCard);
  }

  /**
   * Deletes a card by its ID.
   *
   * @param id the ID of the card to delete
   */
  @Transactional
  public void deleteCard(Long id) {
    cardRepository.deleteById(id);
  }
}
