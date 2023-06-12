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
import net.projet.ayoub.model.Utilisateur;
import net.projet.ayoub.repository.UtilisateurRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class UtilisateurController {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	@GetMapping("/utilisateurs")
	public List<Utilisateur> getAllUtilisateurs(){
		return utilisateurRepository.findAll();
	}		
	
	
	@PostMapping("/utilisateurs")
	public Utilisateur createUtilisateur(@RequestBody Utilisateur user) {
		return utilisateurRepository.save(user);
	}
	
	
	@GetMapping("/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(utilisateur);
	}
	
	
	
	@PutMapping("/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails){
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		utilisateur.setFirstName(utilisateurDetails.getFirstName());
		utilisateur.setLastName(utilisateurDetails.getLastName());
		utilisateur.setEmail(utilisateurDetails.getEmail());
		utilisateur.setPassword(utilisateurDetails.getPassword());
		Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
		return ResponseEntity.ok(updatedUtilisateur);
	}
	
	@DeleteMapping("/utilisateurs/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUtilisateur(@PathVariable Long id){
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		utilisateurRepository.delete(utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
