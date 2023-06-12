package net.projet.ayoub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="category_id")
@Entity
@Table(name = "Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	 private long category_id;
	@Column(name ="cat_label")
	 private String cat_label;
	
	@OneToMany( mappedBy = "category", cascade = CascadeType.ALL)
	 private List<Item> item;
	public Category() {
		super();
	}
	public Category(long category_id, String cat_label) {
		super();
		this.category_id = category_id;
		this.cat_label = cat_label;
		
	}
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public String getCat_label() {
		return cat_label;
	}
	public void setCat_label(String cat_label) {
		this.cat_label = cat_label;
	}
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
}
