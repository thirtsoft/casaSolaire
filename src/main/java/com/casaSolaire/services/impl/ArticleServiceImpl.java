package com.casaSolaire.services.impl;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Article;
import com.casaSolaire.models.Category;
import com.casaSolaire.repository.ArticleRepository;
import com.casaSolaire.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

    }

    @Override
    public ArticleDto findById(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntityToDto(article.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not article with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
