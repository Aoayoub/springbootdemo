package net.projet.ayoub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.projet.ayoub.exception.ResourceNotFoundException;
import net.projet.ayoub.model.Item;
import net.projet.ayoub.repository.ItemRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	
	@GetMapping("/items")
	public List<Item> getAllItems(){
		return itemRepository.findAll();
	}		
	
	
	@PostMapping("/items")
	public Item createItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(item);
	}
	
	
	
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateitem(@PathVariable Long id, @RequestBody Item itemDetails){
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		item.setCategory(itemDetails.getCategory());
		item.setDate_init(itemDetails.getDate_init());
		item.setDate_end(itemDetails.getDate_end());
		item.setInitial_price(itemDetails.getInitial_price());
		item.setStatus(itemDetails.getStatus());
		item.setItem_id(itemDetails.getItem_id());
		
		
		Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable Long id){
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
