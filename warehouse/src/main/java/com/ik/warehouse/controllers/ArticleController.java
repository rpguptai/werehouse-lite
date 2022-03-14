/**
 * 
 */
package com.ik.warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value = "/api/products/{articleName}")
	@ApiOperation(value = "Get Article by article ID")
	public ArticleVo getProduct(@PathVariable Long id) {
		return ArticleMappingUtils.getArticleVoFromDomain(articleRepository.findById(String.valueOf(id)).get());
	}
}
