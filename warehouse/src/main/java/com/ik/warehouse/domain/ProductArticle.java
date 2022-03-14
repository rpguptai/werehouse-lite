/**
 * 
 */
package com.ik.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ravi
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticle {
	private long articleId;
	private long amount;
}
