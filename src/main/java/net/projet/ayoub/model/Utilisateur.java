package net.projet.ayoub.model;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utilisateur_id")
	private long utilisateur_id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@OneToMany(mappedBy = "utilisateur",fetch = FetchType.LAZY)
	private List<Bid> Bid;
	
	public Utilisateur() {
		super();
	}
	public Utilisateur(long utilisateur_id, String firstName, String lastName, String email, String password) {
		super();
		this.utilisateur_id = utilisateur_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}
	public long getutilisateur_id() {
		return utilisateur_id;
	}
	public void setutilisateur_id(long utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}