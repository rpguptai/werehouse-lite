/**
 * 
 */
package com.ik.warehouse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ik.warehouse.domain.Inventory;
import com.ik.warehouse.domain.Product;
import com.ik.warehouse.domain.ProductArticle;

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

	@Test
	public void findProductRepositoryByIdTest() {
		List<ProductArticle> productArticles = new ArrayList<ProductArticle>();
		productArticles.add(new ProductArticle(1, 200));
		productArticles.add(new ProductArticle(2, 50));
		
		Product p1 = new Product("Product1",productArticles);
		productRepository.save(p1);
		Optional<Product> product = productRepository.findById("Product1");
		assertEquals("Product1", product.get().getName());
		assertEquals(2, product.get().getProductArticles().size());
	}

}
