// Java
package com.example.tp2.integration;

import com.example.tp2.dto.ArticleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void testGetById() throws Exception {
        ArticleDTO article = new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ArticleDTO> result = this.restTemplate.exchange(
                "http://localhost:" + port + "/api/articles/id/" + article.getId(),
                HttpMethod.GET,
                entity,
                ArticleDTO.class
        );

        assertThat(result).isNotNull();
        ArticleDTO dto = result.getBody();
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(article.getId());
        assertThat(dto.getDescription()).isEqualTo(article.getDescription());
        assertThat(dto.getPrice()).isEqualTo(article.getPrice());
        assertThat(dto.getQuantity()).isEqualTo(article.getQuantity());
    }
}
