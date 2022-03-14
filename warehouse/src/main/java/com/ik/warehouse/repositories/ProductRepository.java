/**
 * 
 */
package com.ik.warehouse.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ik.warehouse.domain.Product;

/**
 * @author Ravi
 *
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
}