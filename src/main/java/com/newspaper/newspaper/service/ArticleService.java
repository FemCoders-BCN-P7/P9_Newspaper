package com.newspaper.newspaper.service;

import com.newspaper.newspaper.model.Article;
import java.util.List;

public interface ArticleService {

    Article createArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(Long id);

    Article updateArticle(Long id, Article updatedArticle);

    void deleteArticle(Long id);
}
