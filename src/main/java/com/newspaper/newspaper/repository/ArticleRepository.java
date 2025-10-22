package com.newspaper.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.newspaper.newspaper.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
