/**
 * 
 */
package com.ik.warehouse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ik.warehouse.domain.Product;

/**
 * @author Ravi
 *
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	 @Test
	  public void findAllProductTest() {
		Product p1 = new Product();
		productRepository.save(p1);
	    Product p2 = new Product();
	    productRepository.save(p2);
	    Product p3 = new Product();
	    productRepository.save(p3);
	    Iterable<Product> products = productRepository.findAll();
	    assertThat(products).hasSize(3);
	  }

}
