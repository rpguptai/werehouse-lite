/**
 * 
 */
package com.ik.warehouse.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ravi
 *
 */
@Entity
@Table(name = "Inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

	@Id
	private long id;
	private String name;
	private long stock;

}
