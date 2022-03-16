/**
 * 
 */
package com.ik.warehouse.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ik.warehouse.config.ApplicationConfig;
import com.ik.warehouse.domain.Article;
import com.ik.warehouse.repositories.ArticleRepository;

/**
 * @author Ravi
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest({ ArticleController.class, ApplicationConfig.class })
public class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ArticleRepository articleRepository;

	@Test
	public void getArticleById_when_Article_available() throws Exception {
		Optional<Article> a1 = Optional.ofNullable(new Article(2, "Article1", "This is sample Article"));
		Mockito.when(articleRepository.findById(String.valueOf(2))).thenReturn(a1);

		mockMvc.perform(get("/api/articles/{id}", 2)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Article1")));
	}

	@Test
	public void getArticleById_when_Article_not_available() throws Exception {
		mockMvc.perform(get("/api/articles/{id}", 55)).andExpect(status().isNotFound());
	}

}
