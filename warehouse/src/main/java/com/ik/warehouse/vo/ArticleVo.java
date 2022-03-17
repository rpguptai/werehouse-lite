/**
 * 
 */
package com.ik.warehouse.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "art_id",
    "name",
    "description"
})
@Getter
@Setter
public class ArticleVo {
	
	 	@JsonProperty("art_id")
	    private String artId;
	    @JsonProperty("name")
	    private String name;
	    @JsonProperty("description")
	    private String description;

}
