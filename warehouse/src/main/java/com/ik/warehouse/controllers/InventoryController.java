/**
 * 
 */
package com.ik.warehouse.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ik.warehouse.domain.Inventory;
import com.ik.warehouse.exception.ResourceNotFoundException;
import com.ik.warehouse.repositories.InventoryRepository;
import com.ik.warehouse.utils.InventoryMappingUtils;
import com.ik.warehouse.vo.InventoriesVo;
import com.ik.warehouse.vo.InventoryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Ravi
 *
 */

@RestController
@Api(tags = "API for Inventories")
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;

	@GetMapping(value = "/api/inventories/{id}")
	@ApiOperation(value = "Get Inventory Details with article id")
	public InventoryVo getInventory(@PathVariable long id) {
		Optional<Inventory> inventory = inventoryRepository.findById(id);
		if (inventory.isPresent()) {
			return InventoryMappingUtils.getInventoryVoFromDomain(inventory.get());
		} else {
			throw new ResourceNotFoundException("Inventory Details not found for " + id);
		}
	}

	@PostMapping(value = "/api/inventory")
	@ApiOperation(value = "save a article inventory details")
	@Transactional
	public InventoryVo saveInventory(@RequestBody InventoryVo inventoryVo) {
		inventoryRepository.save(InventoryMappingUtils.getInventoryFromVo(inventoryVo));
		return inventoryVo;
	}

	@PostMapping(value = "/api/inventories")
	@ApiOperation(value = "save multiple article inventory details")
	@Transactional
	public InventoriesVo saveInventories(@RequestBody InventoriesVo inventoriesVo) {
		inventoriesVo.getInventory().stream().forEach(x -> saveInventory(x));
		return inventoriesVo;
	}

	@PutMapping(value = "/api/inventories")
	@ApiOperation(value = "Modify a article inventory details")
	@Transactional
	public InventoryVo updateInventory(@RequestBody InventoryVo inventoryVo) {
		Optional<Inventory> inventory = inventoryRepository.findById(inventoryVo.getArtId());
		if (inventory.isPresent()) {
			inventory.get().setName(inventoryVo.getName());
			inventory.get().setStock(inventoryVo.getStock());
			inventoryRepository.save(inventory.get());
		} else {
			throw new RuntimeException();
		}
		return inventoryVo;
	}

	@DeleteMapping(value = "/api/inventories/{id}")
	@ApiOperation(value = "delete a article inventory details")
	@Transactional
	public void deleteInventory(@PathVariable long id) {
		inventoryRepository.deleteById(id);
	}
}
