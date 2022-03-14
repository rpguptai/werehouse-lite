/**
 * 
 */
package com.ik.warehouse.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ravi
 *
 */

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {	
    @Id
    private String name;
    private List<ProductArticle> productArticles;
}
