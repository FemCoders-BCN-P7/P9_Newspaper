package com.newspaper.newspaper.service;

import com.newspaper.newspaper.dto.ArticleDTO;
import com.newspaper.newspaper.entity.Article;
import com.newspaper.newspaper.entity.User;
import com.newspaper.newspaper.mapper.ArticleMapper;
import com.newspaper.newspaper.repository.ArticleRepository;
import com.newspaper.newspaper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDTO createArticle(ArticleDTO dto) {
        if (dto == null || dto.getUser() == null || dto.getUser().getId() == null) {
            throw new IllegalArgumentException("User id is required to create an article");
        }

        Article entity = articleMapper.toEntity(dto);

        User author = userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + dto.getUser().getId()));
        entity.setUser(author);

        Article saved = articleRepository.save(entity);
        return articleMapper.toDTO(saved);
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDTO)
                .toList();
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Article entity = getArticleEntity(id);
        return articleMapper.toDTO(entity);
    }

    @Override
    public ArticleDTO updateArticle(Long id, ArticleDTO dto) {
        Article existing = getArticleEntity(id);

        articleMapper.updateEntityFromDto(dto, existing);

        Article saved = articleRepository.save(existing);
        return articleMapper.toDTO(saved);
    }

    @Override
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article not found with id " + id);
        }
        articleRepository.deleteById(id);
    }

    private Article getArticleEntity(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id " + id));
    }
}
