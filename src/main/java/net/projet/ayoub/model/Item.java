package net.projet.ayoub.model;

import java.math.BigDecimal;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="item_id")
@Entity
@Table(name ="Item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	 private long item_id;
	@Column(name ="Title")
	private String Title;
	
	@Column(name = "date_init")
	private Date date_init;
	@Column(name = "date_end")
	private Date date_end;
	@Column(name ="Initial_price")
	private double initial_price;
	
	@OneToMany(mappedBy="item",fetch =FetchType.LAZY)
	//@JsonManagedReference
	private List<Bid> bid;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable=false,referencedColumnName="category_id")
	private Category category;
	public Item() {
		super();
	}
	public Item(long item_id, Date date_init, Date date_end, double initial_price, Status status,
			Category category) {
		super();
		this.item_id = item_id;
		this.date_init = date_init;
		this.date_end = date_end;
		this.initial_price = initial_price;
		this.status = status;
		this.category = category;
	}
	
	
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public Date getDate_init() {
		return date_init;
	}
	public void setDate_init(Date date_init) {
		this.date_init = date_init;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	public double getInitial_price() {
		return initial_price;
	}
	public void setInitial_price(double initial_price) {
		this.initial_price = initial_price;
	}
	public List<Bid> getBids() {
		return bid;
	}
	public void setBids(List<Bid> bids) {
		bid = bids;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	

}
