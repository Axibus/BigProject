package Impls;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import entity.Address;
import entity.Cart;
import entity.Orders;
import entity.User;
import service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Resource
	CartServiceImpl cdi;
	@Resource
	OrderDaoImpl odi;
	@Resource
	UserDaoImpl udi;

	@Override
	public void addOrder(Address a,Cart c) {
		Orders o = new Orders();
		o.setAddressid(a.getId());
		o.setUserid(c.getUserId());
		o.setProductid(c.getProductId());
		o.setOrderStatus("订单已生成");
		odi.addOrder(o);
	}

	@Override
	public Orders findOrderById(int id) {
		
		return odi.findOrderById(id);
	}

	@Override
	public List findAllOrder() {
		
		return odi.findAllOrder();
	}

	@Override
	public List findOrderByUserId(int id) {
		
		return odi.findOrderByUserId(id);
	}

	@Override
	public Boolean deleteOrder(int id) {
		return odi.deleteOrder(id);
	}

	@Override
	public Orders findOrderByUserIdAndProductId(int uid, int pid) {
		
		return odi.findOrderByUserIdAndProductId(uid, pid);
	}

	@Override
	public void updateOrder(Orders c) {
		odi.updateOrder(c);
		
	}

}
