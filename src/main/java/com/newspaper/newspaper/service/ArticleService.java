package com.newspaper.newspaper.service;

import java.util.List;

import com.newspaper.newspaper.dto.ArticleDTO;

public interface ArticleService {

    ArticleDTO createArticle(ArticleDTO articleDTO);

    List<ArticleDTO> getAllArticles();

    ArticleDTO getArticleById(Long id);

    ArticleDTO updateArticle(Long id, ArticleDTO articleDTO);

    void deleteArticle(Long id);
}
