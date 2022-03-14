/**
 * 
 */
package com.ik.warehouse.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ik.warehouse.domain.Article;

/**
 * @author Ravi
 *
 */
public interface ArticleRepository extends MongoRepository<Article, String> {
	
}