package com.newspaper.newspaper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "articles", ignore = true)
    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);

    @Mapping(target = "articles", ignore = true)
    void updateEntityFromDto(UserDTO dto, @MappingTarget User entity);
}
