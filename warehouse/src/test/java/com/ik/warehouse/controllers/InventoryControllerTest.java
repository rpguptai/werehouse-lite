package com.ik.warehouse.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ik.warehouse.config.ApplicationConfig;
import com.ik.warehouse.domain.Inventory;
import com.ik.warehouse.repositories.InventoryRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ InventoryController.class, ApplicationConfig.class })
public class InventoryControllerTest {

	@MockBean
	private InventoryRepository inventoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getInventoryById_when_Inventory_available() throws Exception {
		Optional<Inventory> i1 = Optional.ofNullable(new Inventory(2, "TEST", 100));
		Mockito.when(inventoryRepository.findById(2l)).thenReturn(i1);

		mockMvc.perform(get("/api/inventories/{id}", "2")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("TEST")));
	}

	@Test
	public void getInventoryById_when_Inventory_not_available() throws Exception {
		mockMvc.perform(get("/api/inventories/{id}", 55)).andExpect(status().isNotFound());
	}

	@Test
	public void deleteInventoryTest() throws Exception {
		mockMvc.perform(delete("/api/inventories/{id}", 55).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveSingleInventoryTest() throws Exception {
		mockMvc.perform(post("/api/inventory").content(asJsonString(new Inventory(2, "TEST", 100)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("TEST")));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
