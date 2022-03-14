/**
 * 
 */
package com.ik.warehouse.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ik.warehouse.domain.Inventory;

/**
 * @author Ravi
 *
 */
@DataJpaTest
public class InventoryControllerTest {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Test
	public void findAllInventoryTest() {
		Inventory i1 = new Inventory(2,"TEST",100);
		inventoryRepository.save(i1);
		Iterable<Inventory> products = inventoryRepository.findAll();
		assertThat(products).hasSize(1);
	}

}
