/**
 * 
 */
package com.ik.warehouse.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
		Inventory i1 = new Inventory(2, "TEST", 100);
		inventoryRepository.save(i1);
		Inventory i2 = new Inventory(3, "TEST", 500);
		inventoryRepository.save(i2);
		Iterable<Inventory> inventoryList = inventoryRepository.findAll();
		assertThat(inventoryList).hasSize(2);
	}

	@Test
	public void findInventoryByIdTest() {
		Inventory i1 = new Inventory(2, "TEST", 100);
		inventoryRepository.save(i1);
		Optional<Inventory> inventory = inventoryRepository.findById(2l);
		assertEquals("TEST", inventory.get().getName());
	}

	@Test
	public void deleteInventoryByIdTest() {
		Inventory i1 = new Inventory(2, "TEST", 100);
		inventoryRepository.save(i1);
		Inventory i2 = new Inventory(3, "TEST", 500);
		inventoryRepository.save(i2);
		Iterable<Inventory> inventoryList = inventoryRepository.findAll();
		assertThat(inventoryList).hasSize(2);
		inventoryRepository.deleteById(i1.getId());
		Iterable<Inventory> inventoryList1 = inventoryRepository.findAll();
		assertThat(inventoryList1).hasSize(1);
	}

}
