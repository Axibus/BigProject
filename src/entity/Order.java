package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	private int id;
	private int cartid;
	private int userid;
	private int addressid;
	private String orderStatus;
	
	
	public Order(){};
	public Order(int id,int cartid,int userid, int addressid,String orderstatus){
		this.id = id;
		this.cartid = cartid;
		this.userid = userid;
		this.addressid = addressid;
		this.orderStatus = orderstatus;
	};
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
