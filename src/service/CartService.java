package service;

import java.util.List;

import entity.Cart;

public interface CartService {
	public void addCart(int uid,int pid);
	public Cart findCartById(int id);
	public List findAllCart();
	public List findCartByUserId(int id);
	public Boolean deleteCart(int id);
	public Cart findCartByUserIdAndProductId(int uid,int pid);
	public void updateCart(Cart c,int count);
}
