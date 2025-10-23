package com.newspaper.newspaper.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newspaper.newspaper.model.Article;
import com.newspaper.newspaper.model.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    
    
    List<Article> findByUser(User user);

    List<Article> findByCategory(String category);



