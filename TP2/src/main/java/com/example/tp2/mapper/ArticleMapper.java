package com.example.tp2.mapper;

import com.example.tp2.dto.ArticleDTO;
import com.example.tp2.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleMapper {

    public static Article toEntity(ArticleDTO dto) {
        if (dto == null) return null;
        return Article.builder().
                id(dto.getId()).
                description(dto.getDescription()).
                price(dto.getPrice()).
                quantity(dto.getQuantity()).build();
    }

    public static ArticleDTO toDTO(Article bo) {
        if (bo == null) return null;
        return ArticleDTO.builder().
                id(bo.getId()).
                description(bo.getDescription()).
                price(bo.getPrice()).
                quantity(bo.getQuantity()).
                build();
    }

    public static List<Article> toEntites(List<ArticleDTO> dtoList) {
        List<Article> boList = new ArrayList<>();
        dtoList.forEach(a -> boList.add(toEntity(a)));
        return boList;
    }
    public static List<ArticleDTO> toDTOs(List<Article> boList) {
        List<ArticleDTO> dtoList = new ArrayList<>();
        boList.forEach(a -> dtoList.add(toDTO(a)));
        return dtoList;
    }

}
