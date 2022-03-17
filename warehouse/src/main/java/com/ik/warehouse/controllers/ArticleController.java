/**
 * 
 */
package com.ik.warehouse.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ik.warehouse.domain.Article;
import com.ik.warehouse.exception.ResourceNotFoundException;
import com.ik.warehouse.repositories.ArticleRepository;
import com.ik.warehouse.utils.ArticleMappingUtils;
import com.ik.warehouse.vo.ArticleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ravi
 *
 */

@RestController
@Api(tags = "API for Articles")
public class ArticleController {


	@Autowired
	ArticleRepository articleRepository;

	@GetMapping(value = "/api/articles/{id}")
	@ApiOperation(value = "Get Article by article ID")
	public ArticleVo getArticle(@PathVariable String id) {
		
		Optional<Article> article = articleRepository.findById(id);
		if(article.isPresent()) {
			return ArticleMappingUtils.getArticleVoFromDomain(article.get());
		}else {
			throw new ResourceNotFoundException("Article Details not found for " + id);
		}
		
	}
	
	
	@PostMapping(value = "/api/articles/")
	@ApiOperation(value = "save an article information")
	@Transactional
	public ArticleVo saveArticle(@RequestBody ArticleVo articleVo) {
		articleRepository.save(ArticleMappingUtils.getArticleFromVo(articleVo));
		return articleVo;
	}
}
