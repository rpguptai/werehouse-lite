
package com.ik.warehouse.vo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"art_id",
	"amount_of"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticleVo {
	@JsonProperty("art_id")
	private long artId;
	@JsonProperty("amount_of")
	private long amountOf;
}
