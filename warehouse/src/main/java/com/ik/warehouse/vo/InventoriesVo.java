
package com.ik.warehouse.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inventory"
})
@Getter
@Setter
public class InventoriesVo {

    @JsonProperty("inventory")
    private List<InventoryVo> inventory = null;

}
