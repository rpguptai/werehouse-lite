
package com.ik.warehouse.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "art_id",
    "name",
    "stock"
})
@Getter
@Setter
public class InventoryVo {
    @JsonProperty("art_id")
    private long artId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("stock")
    private long stock;
}
