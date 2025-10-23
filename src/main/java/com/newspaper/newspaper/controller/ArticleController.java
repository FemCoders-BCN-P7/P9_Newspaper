package com.newspaper.newspaper.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newspaper.newspaper.model.Article;
import com.newspaper.newspaper.service.ArticleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor


public class ArticleController {

    private final ArticleService articleService;
    @PostMapping
    public ResponseEntity<Article> createArticle(@valid @RequestBody Article article){
        Article createdArticle = articleService.createArticle(article);
        URI location = URI.create("/api/articles/" + createdArticle.getId());
        return ResponseEntity.created(location).body(createdArticle);


    }
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @Valid @RequestBody Article updatedArticle) {
        Article article = articleService.updateArticle(id, updatedArticle);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    
}
