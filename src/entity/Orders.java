package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {
	@Id
	private int id;
	private int productid;
	private int userid;
	private int addressid;
	private String orderStatus;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Orders(){};
	public Orders(int id,int productid,int userid, int addressid,String orderstatus,int count){
		this.id = id;
		this.productid = productid;
		this.userid = userid;
		this.addressid = addressid;
		this.orderStatus = orderstatus;
		this.count = count;
	};
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
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
