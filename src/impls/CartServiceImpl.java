package Impls;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import entity.Cart;
import service.CartService;


@Service
public class CartServiceImpl implements CartService{
	@Resource
	CartDaoImpl cdi;
	@Override
	public void addCart(int uid, int pid) {
		Cart c = new Cart();
		c.setUserId(uid);
		c.setProductId(pid);
		int count = c.getCount();
		count++;
		c.setCount(count);
		cdi.addCart(c);		
	}
	@Override
	public Cart findCartById(int id) {
		return cdi.findCartById(id);
	}
	@Override
	public List findAllCart() {
	
		return cdi.findAllCart();
	}
	@Override
	public List findCartByUserId(int id) {
		return cdi.findCartByUserId(id);
	}
	@Override
	public Boolean deleteCart(int id) {
		
		return cdi.deleteCart(id);
	}
	@Override
	public Cart findCartByUserIdAndProductId(int uid, int pid) {
		
		return cdi.findCartByUserIdAndProductId(uid, pid);
	}
	@Override
	public void updateCart(Cart c,int count) {
		count +=c.getCount();
		c.setCount(count);
		cdi.updateCart(c);
	}

}
