package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int id;
	private String name;
	private int proTypeId;
	private double price;
	
	public Product(Integer id,String name, int proTypeId, double price){
		super();
		this.id = id;
		this.name = name;
		this.proTypeId = proTypeId;
		this.price = price;
		
	};
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProTypeId() {
		return proTypeId;
	}
	public void setProTypeId(int proTypeId) {
		this.proTypeId = proTypeId;
	}
	
}
