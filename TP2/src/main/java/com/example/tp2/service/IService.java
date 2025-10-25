package com.example.tp2.service;

import com.example.tp2.dto.ArticleDTO;

import java.util.List;

public interface IService {

    ArticleDTO getById(Long id);
    List<ArticleDTO> getAll();
    void create(ArticleDTO article);
    void update(Long id, ArticleDTO article);
    void deleteById(Long id);
}
