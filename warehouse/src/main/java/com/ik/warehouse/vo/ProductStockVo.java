/**
 * 
 */
package com.ik.warehouse.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ravi
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "stock" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockVo {
	@JsonProperty("name")
	private String name;
	@JsonProperty("stock")
	private Long stock;
}
