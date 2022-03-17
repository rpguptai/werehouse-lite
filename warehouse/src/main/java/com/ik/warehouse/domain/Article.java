/**
 * 
 */
package com.ik.warehouse.domain;

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
public class Article {
	@Id
	private String articleId;
	private String articleName;
	private String articleDescription;
}
