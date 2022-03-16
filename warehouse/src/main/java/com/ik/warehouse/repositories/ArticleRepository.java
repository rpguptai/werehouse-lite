/**
 * 
 */
package com.ik.warehouse.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ik.warehouse.domain.Article;

/**
 * @author Ravi
 *
 */
@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
	
}