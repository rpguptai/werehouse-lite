
package com.ik.warehouse.vo;

import java.util.List;

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
    "products"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsVo {

    @JsonProperty("products")
    private List<ProductVo> products = null;

}
