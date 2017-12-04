package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class User {
	@Id
	private int userId;
	private String userName;
	private String password;
	private String eMail;
	@Transient
	private Set<Product> productSet = new HashSet<Product>();
	
	public User(){};
	public User(int id,String name,String password,String eMail,Set pSet){
		super();
		this.userId = id;
		this.userName = name;
		this.password = password;
		this.eMail = eMail;
		this.productSet = pSet;
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
