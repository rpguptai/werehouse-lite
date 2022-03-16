/**
 * 
 */
package com.ik.warehouse.utils;

import com.ik.warehouse.domain.Article;
import com.ik.warehouse.vo.ArticleVo;

/**
 * @author Ravi
 *
 */
public class ArticleMappingUtils {
	
	private ArticleMappingUtils() {
		
	}

	public static Article getArticleFromVo(ArticleVo articleVo) {
		Article article = new Article();
		article.setArticleId(articleVo.getArtId());
		article.setArticleName(articleVo.getName());
		article.setArticleDescription(articleVo.getDescription());
		return article;
	}

	public static ArticleVo getArticleVoFromDomain(Article article) {
		ArticleVo articleVo = new ArticleVo();
		articleVo.setArtId(article.getArticleId());
		articleVo.setName(article.getArticleName());
		articleVo.setDescription(article.getArticleDescription());
		return articleVo;
	}

}
