package com.example.tp2.dao;

import com.example.tp2.entity.Article;

import java.util.List;

public interface IDAO {
    Article findById(Long id);
    List<Article> findAll();
    void save(Article article);
    void deleteById(Long id);
}
