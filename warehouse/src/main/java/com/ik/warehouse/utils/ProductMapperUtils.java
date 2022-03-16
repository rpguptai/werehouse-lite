/**
 * 
 */
package com.ik.warehouse.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ik.warehouse.domain.Product;
import com.ik.warehouse.domain.ProductArticle;
import com.ik.warehouse.vo.ProductArticleVo;
import com.ik.warehouse.vo.ProductVo;

/**
 * @author Ravi
 *
 */
public class ProductMapperUtils {
	
	private ProductMapperUtils() {
		
	}

	public static Product getProductFromVo(ProductVo productVo) {
		Product product = new Product();
		product.setName(productVo.getName());

		Optional<List<ProductArticleVo>> productArticleVoList = Optional
				.ofNullable(productVo.getProductArticleVoList());
		if (productArticleVoList.isPresent()) {
			product.setProductArticles(productVo.getProductArticleVoList().stream()
					.map(x -> new ProductArticle(x.getArtId(), x.getAmountOf())).collect(Collectors.toList()));
		}

		return product;
	}

	public static ProductVo getProductVoFromDomain(Product product) {
		ProductVo productVo = new ProductVo();
		productVo.setName(product.getName());
		Optional<List<ProductArticle>> productArticleList = Optional.ofNullable(product.getProductArticles());
		if (productArticleList.isPresent()) {
			productVo.setProductArticleVoList(productArticleList.get().stream()
					.map(x -> new ProductArticleVo(x.getArticleId(), x.getAmount())).collect(Collectors.toList()));
		}
		return productVo;
	}

}
