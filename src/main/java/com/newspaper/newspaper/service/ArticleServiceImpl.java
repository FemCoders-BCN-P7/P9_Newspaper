package com.newspaper.newspaper.service;

import com.newspaper.newspaper.entity.Article;
import com.newspaper.newspaper.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        article.setCreatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @Override
    public Article updateArticle(Long id, Article updatedArticle) {
        Article existing = getArticleById(id);
        existing.setTitle(updatedArticle.getTitle());
        existing.setContent(updatedArticle.getContent());
        existing.setCategory(updatedArticle.getCategory());
        existing.setUpdatedAt(LocalDateTime.now());
        return articleRepository.save(existing);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
