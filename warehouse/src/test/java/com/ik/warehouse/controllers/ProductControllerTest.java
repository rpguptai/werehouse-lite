/**
 * 
 */
package com.ik.warehouse.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ik.warehouse.config.ApplicationConfig;
import com.ik.warehouse.domain.Product;
import com.ik.warehouse.domain.ProductArticle;
import com.ik.warehouse.repositories.InventoryRepository;
import com.ik.warehouse.repositories.ProductRepository;
import com.ik.warehouse.vo.InventoryVo;

/**Ō
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
	@MockBean
    private RestTemplate restTemplate;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getProductByName_when_product_available() throws Exception {
		Optional<Product> product = Optional.ofNullable(new Product("product1", null));
		Mockito.when(productRepository.findById("product1")).thenReturn(product);

		mockMvc.perform(get("/api/products/{productName}", "product1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("product1")));
	}

	@Test
	public void getProductByName_when_product_not_available() throws Exception {
		mockMvc.perform(get("/api/products/{productName}", "product123")).andExpect(status().isNotFound());
	}

	@Test
	public void deleteProductTest() throws Exception {
		mockMvc.perform(delete("/api/products/{productName}", "product1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveSingleProductTest() throws Exception {
		mockMvc.perform(post("/api/product").content(asJsonString(new Product("product9", null)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("product9")));
	}

	@Test
	public void getProductStockTest() throws Exception {
		ProductArticle productArticle1 = new ProductArticle(1, 15);
		ProductArticle productArticle2 = new ProductArticle(2, 10);
		List<ProductArticle> productArticleList = new ArrayList<ProductArticle>();
		productArticleList.add(productArticle1);
		productArticleList.add(productArticle2);
		Optional<Product> product = Optional.ofNullable(new Product("product1", productArticleList));
		Optional<InventoryVo> inventoryVo1 = Optional.ofNullable(new InventoryVo(1, "testI1", 34));
		Optional<InventoryVo> inventoryVo2 = Optional.ofNullable(new InventoryVo(1, "testI1", 500));
		Mockito.when(productRepository.findById("product1")).thenReturn(product);

		Mockito.when(restTemplate.getForObject("http://localhost:8080/api/inventories/1", InventoryVo.class))
        .thenReturn(inventoryVo1.get());
		
		Mockito.when(restTemplate.getForObject("http://localhost:8080/api/inventories/2", InventoryVo.class))
        .thenReturn(inventoryVo2.get());
		
		mockMvc.perform(get("/api/productStock/{productName}", "product1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("product1")))
				.andExpect(jsonPath("$.stock", is(2)));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
