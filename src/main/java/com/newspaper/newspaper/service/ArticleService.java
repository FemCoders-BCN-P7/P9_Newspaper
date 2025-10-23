package com.newspaper.newspaper.service;

import java.util.List;

import com.newspaper.newspaper.entity.Article;

public interface ArticleService {

    Article createArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long id);

    Article updateArticle(Long id, Article updatedArticle);

    void deleteArticle(Long id);
}
