// Java
package com.example.tp2.unit;

import com.example.tp2.controller.ArticleController;
import com.example.tp2.dto.ArticleDTO;
import com.example.tp2.service.IService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    private MockMvc mvc;

    @Mock
    private IService service;

    @InjectMocks
    private ArticleController controller;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<ArticleDTO> articles = Arrays.asList(
                new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d),
                new ArticleDTO(2L, "ECRAN", 1500d, 10d),
                new ArticleDTO(3L, "CAMERA LG", 3000d, 10d),
                new ArticleDTO(4L, "SOURIS", 200d, 10d)
        );
        when(service.getAll()).thenReturn(articles);

        mvc.perform(get("/api/articles/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].description").value("ECRAN"))
                .andExpect(jsonPath("$[2].price").value(3000.0))
                .andExpect(jsonPath("$[3].quantity").value(10.0));
    }
}
