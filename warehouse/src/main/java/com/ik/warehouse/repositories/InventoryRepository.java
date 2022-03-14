/**
 * 
 */
package com.ik.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ik.warehouse.domain.Inventory;

/**
 * @author Ravi
 *
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
