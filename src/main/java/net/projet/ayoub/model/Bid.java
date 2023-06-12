package net.projet.ayoub.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="bid_id")
@Entity
@Table(name ="Bid")
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid_id")
	private long bid_id;
	@Column(name = "price")
	private double price;
	@ManyToOne
	@JoinColumn(name = "utilisateur_id",nullable = false,referencedColumnName = "utilisateur_id" )
	private Utilisateur utilisateur;
	@ManyToOne(fetch = FetchType.EAGER)
	//@JsonBackReference
	@JoinColumn(name = "item_id",nullable=false,referencedColumnName="item_id")
	
	private Item item;
	
	public Bid() {
		super();
	}
	public Bid(long bid_id, double price, Utilisateur utilisateur, Item item) {
		super();
	
		this.bid_id = bid_id;
		this.price = price;
		this.utilisateur = utilisateur;
		this.item = item;
		
	}
	public long getBid_id() {
		return bid_id;
	}
	public void setBid_id(long bid_id) {
		this.bid_id = bid_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	
	}
	
	


	
}
