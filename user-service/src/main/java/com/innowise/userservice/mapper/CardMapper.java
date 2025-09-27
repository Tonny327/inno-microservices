package com.innowise.userservice.mapper;

import com.innowise.userservice.model.dto.CardDto;
import com.innowise.userservice.model.entity.Card;
import com.innowise.userservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * MapStruct mapper for converting between Card entity and CardDto.
 *
 * @author tonny327
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface CardMapper {

  /**
   * Converts Card entity to CardDto.
   *
   * @param card the Card entity
   * @return CardDto
   */
  @Mapping(source = "user.id", target = "userId")
  CardDto toDto(Card card);

  /**
   * Converts CardDto to Card entity.
   *
   * @param cardDto the CardDto
   * @return Card entity
   */
  @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
  Card toEntity(CardDto cardDto);

  /**
   * Updates existing Card entity with data from CardDto.
   *
   * @param cardDto the CardDto with updated data
   * @param card the existing Card entity to update
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
  void updateEntity(CardDto cardDto, @MappingTarget Card card);

  /**
   * Helper method to create User entity from userId.
   *
   * @param userId the user ID
   * @return User entity with only ID set
   */
  @Named("userIdToUser")
  default User userIdToUser(Long userId) {
    if (userId == null) {
      return null;
    }
    User user = new User();
    user.setId(userId);
    return user;
  }
}