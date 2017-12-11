package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import entity.Address;
import entity.Cart;
import entity.Orders;
import entity.User;

public interface OrderService {
	
	public void addOrder(Address a,Cart c);
	public Orders findOrderById(int id);
	public List findAllOrder();
	public List findOrderByUserId(int id);
	public Boolean deleteOrder(int id);
	public Orders findOrderByUserIdAndProductId(int uid,int pid);
	public void updateOrder(Orders c);

}
