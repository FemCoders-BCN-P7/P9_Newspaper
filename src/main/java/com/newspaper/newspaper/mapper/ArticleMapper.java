package com.newspaper.newspaper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.newspaper.newspaper.dto.ArticleDTO;
import com.newspaper.newspaper.entity.Article;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)        
    @Mapping(target = "createdAt", ignore = true)  
    @Mapping(target = "updatedAt", ignore = true)  
    Article toEntity(ArticleDTO dto);

    ArticleDTO toDTO(Article entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ArticleDTO dto, @MappingTarget Article entity);
}
