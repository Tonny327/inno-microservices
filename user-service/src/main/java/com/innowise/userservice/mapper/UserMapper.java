package com.innowise.userservice.mapper;

import com.innowise.userservice.model.dto.UserDto;
import com.innowise.userservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * MapStruct mapper for converting between User entity and UserDto.
 *
 * @author tonny327
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  /**
   * Converts User entity to UserDto.
   *
   * @param user the User entity
   * @return UserDto
   */
  UserDto toDto(User user);

  /**
   * Converts UserDto to User entity.
   *
   * @param userDto the UserDto
   * @return User entity
   */
  @Mapping(target = "cards", ignore = true)
  User toEntity(UserDto userDto);

  /**
   * Updates existing User entity with data from UserDto.
   *
   * @param userDto the UserDto with updated data
   * @param user the existing User entity to update
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "cards", ignore = true)
  void updateEntity(UserDto userDto, @MappingTarget User user);
}
