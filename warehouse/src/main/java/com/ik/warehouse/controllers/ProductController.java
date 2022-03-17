/**
 * 
 */
package com.ik.warehouse.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ik.warehouse.domain.Product;
import com.ik.warehouse.exception.ResourceNotFoundException;
import com.ik.warehouse.repositories.ProductRepository;
import com.ik.warehouse.utils.ProductMapperUtils;
import com.ik.warehouse.vo.InventoryVo;
import com.ik.warehouse.vo.ProductStockVo;
import com.ik.warehouse.vo.ProductVo;
import com.ik.warehouse.vo.ProductsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ravi
 *
 */

@Slf4j
@RestController
@Api(tags = "API for Products")
public class ProductController {

	@Value( "${inventory.get.endpoint}" )
	private String inventoryGetEndpoint;
	
	@Value( "${inventory.put.endpoint}" )
	private String inventoryPutEndpoint;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/api/products/{productName}")
	@ApiOperation(value = "Get details of product with product name")
	public ProductVo getProduct(@PathVariable String productName) {
		Optional<Product> product = productRepository.findById(productName);
		if (product.isPresent()) {
			return ProductMapperUtils.getProductVoFromDomain(product.get());
		} else {
			throw new ResourceNotFoundException("No Product not found with name " + productName);
		}
	}

	@PostMapping(value = "/api/product")
	@ApiOperation(value = "save a single product")
	public ProductVo saveProduct(@RequestBody ProductVo productVo) {
		productRepository.save(ProductMapperUtils.getProductFromVo(productVo));
		return productVo;
	}

	@PostMapping(value = "/api/products")
	@ApiOperation(value = "save multiple products")
	public ProductsVo saveProducts(@RequestBody ProductsVo productsVo) {
		productsVo.getProducts().stream().forEach(x -> saveProduct(x));
		return productsVo;
	}

	@DeleteMapping(value = "/api/products/{productName}")
	@ApiOperation(value = "delete product with product name")
	public void deleteProduct(@PathVariable String productName) {
		try {
			productRepository.deleteById(productName);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Product not found with name " + productName);
		}
	}

	@GetMapping(value = "/api/productStock/{productName}")
	@ApiOperation(value = "Get quantities available for single product")
	public ProductStockVo getProductStock(@PathVariable String productName) {
		Optional<Product> product = productRepository.findById(productName);
		if(product.isPresent()) {
			long productStock = product.get().getProductArticles().stream()
					.map(x -> getPartialCount(x.getArticleId(), x.getAmount())).min(Long::compare).orElse((long) 0);
			return new ProductStockVo(product.get().getName(), productStock);
		}else {
			throw new ResourceNotFoundException("There is no product with name " + productName);
		}
		
	}

	@GetMapping(value = "/api/productStocks")
	@ApiOperation(value = "Get quantities available for all products")
	public List<ProductStockVo> getProductStocks() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().map(x -> getProductStock(x.getName())).collect(Collectors.toList());
	}

	@PutMapping(value = "/api/sellProducts/{productName}")
	@ApiOperation(value = "sell a product with product name")
	public void sellProduct(@PathVariable String productName) {
		Optional<Product> product = productRepository.findById(productName);
		if(product.isPresent()) {
			product.get().getProductArticles().stream().forEach(x -> updateInventories(x.getArticleId(), x.getAmount()));
		}else {
			throw new ResourceNotFoundException("Product not found with name " + productName);
		}
		
	}

	private void updateInventories(long inventoryId, long count) {
		String url = inventoryGetEndpoint + inventoryId;
		
		Optional<InventoryVo> inventoryVo = Optional.ofNullable(restTemplate.getForObject(url, InventoryVo.class));
		if(inventoryVo.isPresent()) {
			inventoryVo.get().setStock(inventoryVo.get().getStock() - count);
		}else {
			throw new ResourceNotFoundException("Inventory not found with id " + inventoryId);
		}
		restTemplate.put(inventoryPutEndpoint, inventoryVo);
	}
	
	private long getPartialCount(long inventoryId, long count) {
		String url = inventoryGetEndpoint + inventoryId;
		Optional<InventoryVo> inventoryVo;
		try {
			inventoryVo =Optional.ofNullable(restTemplate.getForObject(url, InventoryVo.class));
		} catch (HttpClientErrorException ex) {
			return 0;
		}
		if(inventoryVo.isPresent()) {
			return (long) Math.floor(inventoryVo.get().getStock() / count);
		}else {
			return 0;
		}
		
	}

}
