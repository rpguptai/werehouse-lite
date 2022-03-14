/**
 * 
 */
package com.ik.warehouse.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ik.warehouse.config.ApplicationConfig;
import com.ik.warehouse.domain.Product;
import com.ik.warehouse.repositories.InventoryRepository;
import com.ik.warehouse.repositories.ProductRepository;

/**
 * @author Ravi
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest({ ProductController.class, InventoryController.class, ApplicationConfig.class })
public class ProductControllerTest {

	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private InventoryRepository inventoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testfindProductByName() throws Exception {
		Optional<Product> product = Optional.ofNullable(new Product("Product1", null));
		Mockito.when(productRepository.findById("Product1")).thenReturn(product);

		mockMvc.perform(get("/api/products/Product1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Product1")));
	}

}
