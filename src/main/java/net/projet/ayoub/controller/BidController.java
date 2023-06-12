package net.projet.ayoub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.projet.ayoub.exception.ResourceNotFoundException;
import net.projet.ayoub.model.Bid;
import net.projet.ayoub.model.Item;
import net.projet.ayoub.model.Utilisateur;
import net.projet.ayoub.repository.BidRepository;
import net.projet.ayoub.repository.ItemRepository;
import net.projet.ayoub.repository.UtilisateurRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class BidController {
	@Autowired
	private BidRepository bidRepository;
	@Autowired
	private ItemRepository itemRepository;
	@GetMapping("/bids")
	public List<Bid> getAllBids(){
		
		return bidRepository.findAll();
	}		
	
	
	@PostMapping("/bids")
	public ResponseEntity<String> createBid(@RequestBody Bid bid) {
		    
		double price =bid.getPrice() ;
		Optional<Item> item = itemRepository.findById(bid.getItem().getItem_id());
		if(!item.get().getBids().isEmpty())
		{
		if(!item.isEmpty())
		{
		        if(price < item.get().getInitial_price())
		        {
		        	return ResponseEntity.badRequest().body("bid connot be less or equal to initial price");
		        }
		        else
		        {
		        	for(Bid b: item.get().getBids())
		        	{
		        	if(price <= b.getPrice())
		        	{
		        		return ResponseEntity.badRequest().body("bid connot be less or equal to last price");
		        	}
		        	
		        	}
		        	bidRepository.save(bid);
		        	return ResponseEntity.ok().build();
		        }
		}
		else {
			return ResponseEntity.badRequest().body("resultat 0");
		}
		}
		else {
			if( price > item.get().getInitial_price() )
			{
				
				bidRepository.save(bid);
				return ResponseEntity.ok().build();
				
			}
			else
			{
				return ResponseEntity.badRequest().body("interior of bids price must be > of intial_price");
			}
		}
		
	}
	
	
	@GetMapping("/bids/{id}")
	public ResponseEntity<Bid> getBidById(@PathVariable Long id) {
		Bid bid = bidRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("bids not exist with id :" + id));
		return ResponseEntity.ok(bid);
	}
	
	
	
	@PutMapping("/bids/{id}")
	public ResponseEntity<Bid> updateBid(@PathVariable Long id, @RequestBody Bid bidDetails){
		Bid bid = bidRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("bids not exist with id :" + id));
		bid.setItem(bidDetails.getItem());
		bid.setPrice(bidDetails.getPrice());
		bid.setUtilisateur(bidDetails.getUtilisateur());
		Bid updatedBid = bidRepository.save(bid);
		return ResponseEntity.ok(updatedBid);
	}
	
	@DeleteMapping("/bids/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBid(@PathVariable Long id){
		Bid bid = bidRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("bids not exist with id :" + id));
		
		bidRepository.delete(bid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
