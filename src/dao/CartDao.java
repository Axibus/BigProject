package dao;

import java.util.List;

import entity.Cart;

public interface CartDao {
	public void addCart(Cart c);
	public Cart findCartById(int id);
	public List findAllCart();
	public List findCartByUserId(int id);
	public Boolean deleteCart(int id);
	public Cart findCartByUserIdAndProductId(int uid,int pid);
	public void updateCart(Cart c);
	public List findCartByIds(int[] ids);
	
}
