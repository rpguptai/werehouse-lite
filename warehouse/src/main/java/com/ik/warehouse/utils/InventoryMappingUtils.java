/**
 * 
 */
package com.ik.warehouse.utils;

import com.ik.warehouse.domain.Inventory;
import com.ik.warehouse.vo.InventoryVo;

/**
 * @author Ravi
 *
 */
public class InventoryMappingUtils {

	private InventoryMappingUtils() {

	}

	public static Inventory getInventoryFromVo(InventoryVo inventoryVo) {
		Inventory inventory = new Inventory();
		inventory.setId(inventoryVo.getArtId());
		inventory.setName(inventoryVo.getName());
		inventory.setStock(inventoryVo.getStock());
		return inventory;
	}

	public static InventoryVo getInventoryVoFromDomain(Inventory inventory) {
		InventoryVo inventoryVo = new InventoryVo();
		inventoryVo.setArtId(inventory.getId());
		inventoryVo.setName(inventory.getName());
		inventoryVo.setStock(inventory.getStock());
		return inventoryVo;
	}
}
