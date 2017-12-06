package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Product {
	@Id
	private int id;
	private String name;
	private int proTypeId;
	private Double price;
	private String img1;

	private String img2;
	private String img3;
	@ManyToMany(mappedBy="productSet")
	private Set<User> userSet = new HashSet<User>();
	
	public Double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	

	public String getImg1(){
		return img1;
	}


	public void setImg1(String img1) {
		this.img1 = img1;
	}


	public String getImg2() {
		return img2;
	}


	public void setImg2(String img2) {
		this.img2 = img2;
	}


	public String getImg3() {
		return img3;
	}


	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public Product(){};
	
	public Product(Integer id,String name, int proTypeId, double price,String img1,String img2,String img3,Set uSet){
		super();
		this.id = id;
		this.name = name;
		this.proTypeId = proTypeId;
		this.price = price;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.userSet = uSet;
		
	};
	
	
	public Set<User> getUserSet() {
		return userSet;
	}


	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}


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
