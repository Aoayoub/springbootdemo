package net.projet.ayoub.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property="status_id")
@Entity
@Table(name ="Status")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	 private long status_id;
	@Column(name="status_label")
	 private String status_label;
	
	@OneToMany( mappedBy = "status", cascade = CascadeType.ALL)
	 private List<Item> item;
	
	public Status() {
		super();
	}

	public Status(long status_id, String status_label) {
		super();
		this.status_id = status_id;
		this.status_label = status_label;
		
	}

	public long getStatus_id() {
		return status_id;
	}

	public void setStatus_id(long status_id) {
		this.status_id = status_id;
	}

	public String getStatus_label() {
		return status_label;
	}

	public void setStatus_label(String status_label) {
		this.status_label = status_label;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	

}
