package com.example.tp2.service;


import com.example.tp2.dao.IDAO;
import com.example.tp2.dto.ArticleDTO;
import com.example.tp2.entity.Article;
import com.example.tp2.exception.BusinessException;
import com.example.tp2.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements IService{
    private IDAO dao;

    @Override
    public ArticleDTO getById(Long id) {
        Article articleFound = dao.findById(id);
        if (articleFound == null) {
            throw new BusinessException(String.format("no article with id= %s exist", id));
        }
        return ArticleMapper.toDTO(articleFound);

    }

    @Override
    public List<ArticleDTO> getAll() {
        return ArticleMapper.toDTOs(dao.findAll());
    }

    @Override
    public void create(ArticleDTO article) {
        if (article.getId() == null) {
            dao.save(ArticleMapper.toEntity(article));
            return;
        }
        Article articleFound = dao.findAll().stream().
                filter(a -> article.getId().equals(a.getId())).
                findFirst().orElse(null);
        if (articleFound != null)
            throw new BusinessException(String.format("Article with the same Id=%s exist in database", article.getId()));
                    dao.save(ArticleMapper.toEntity(article));

    }

    @Override
    public void update(Long id, ArticleDTO article) {
        if (id == null)
            throw new BusinessException(String.format("Article id=%s should not be null", id));
        dao.findAll().stream().
                filter(a -> a.getId().equals(id)).findFirst().orElseThrow(() -> new BusinessException(String.format("No article with the id=%s exist in database", id)));
                        article.setId(id);
        dao.save(ArticleMapper.toEntity(article));
    }

    @Override
    public void deleteById(Long id) {
        Article articleFound = dao.findById(id);
        if (articleFound == null) {
            throw new BusinessException(String.format("no article with id= %s exist", id));
        }
        dao.deleteById(id);
    }
}
