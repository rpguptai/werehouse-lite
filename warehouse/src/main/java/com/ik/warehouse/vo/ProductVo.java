
package com.ik.warehouse.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "contain_articles"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("contain_articles")
    private List<ProductArticleVo> productArticleVoList;
}
