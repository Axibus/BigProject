package dao;

import java.util.List;

import entity.Orders;

public interface OrderDao {
	public void addOrder(Orders c);
	public Orders findOrderById(int id);
	public List findAllOrder();
	public List findOrderByUserId(int id);
	public Boolean deleteOrder(int id);
	public Orders findOrderByUserIdAndProductId(int uid,int pid);
	public void updateOrder(Orders c);
}
