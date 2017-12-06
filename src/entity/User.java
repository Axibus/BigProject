package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;


@Entity
public class User {
	@Id
	private int userId;
	private String userName;
	private String password;
	private String eMail;
	private Boolean admin;
	@OneToMany(mappedBy="user", targetEntity=Address.class, cascade=CascadeType.ALL)
	private Set addressSet= new HashSet<Address>();;
	
	@ManyToMany @JoinTable(name="cartinformation", 
			joinColumns=@JoinColumn(name="USERID"), 
			inverseJoinColumns=@JoinColumn(name="PRODUCTID"))
	private Set<Product> productSet = new HashSet<Product>();
	
	
	public Set getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set addressSet) {
		this.addressSet = addressSet;
	}
	
	
	public User(){};
	public User(int id,String name,String password,String eMail,Set<Product> pSet,Set<Address> addressSet){
		super();
		this.userId = id;
		this.userName = name;
		this.password = password;
		this.eMail = eMail;
		this.productSet = pSet;
		this.addressSet = addressSet;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}
	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
	
}
