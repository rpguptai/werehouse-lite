/**
 * 
 */
package com.ik.warehouse.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi
 *
 */
@Document
@Getter
@Setter
public class Article {
	@Id
	private long articleId;
	private String articleName;
	private String articleDescription;
}
