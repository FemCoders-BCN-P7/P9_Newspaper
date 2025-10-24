package com.newspaper.newspaper.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newspaper.newspaper.dto.ArticleDTO;
import com.newspaper.newspaper.dto.UpdateContentDTO;
import com.newspaper.newspaper.service.ArticleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO dto) {
        ArticleDTO created = articleService.createArticle(dto);
        URI location = URI.create("/api/v1/articles/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PatchMapping("/{id}/content")
    public ResponseEntity<ArticleDTO> updateContent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateContentDTO dto) {

        ArticleDTO partial = new ArticleDTO();
        partial.setContent(dto.getContent());
        ArticleDTO updated = articleService.updateArticle(id, partial);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
