package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Impls.AddressServiceImpl;
import Impls.CartServiceImpl;
import Impls.OrderServiceImpl;
import Impls.ProductServiceImpl;
import Impls.UserServiceImpl;
import entity.Address;
import entity.Cart;
import entity.Orders;
import entity.Product;
import entity.ProductType;
import entity.User;

@Controller
public class OrderController {
	@Resource
	private OrderServiceImpl osi;
	@Resource
	private UserServiceImpl usi;
	@Resource
	private AddressServiceImpl asi;
	@Resource
	private CartServiceImpl csi;
	@Resource
	private ProductServiceImpl psi;
	@Resource
	private SessionFactory sessionFactory;
	
	@RequestMapping("addOrder/{cid}")
	public ModelAndView addOrder(@PathVariable int cid){
		
		Cart c = csi.findCartById(cid);
		Product p= psi.findProductById(c.getProductId());
		int count = c.getCount();
		
		ModelAndView m = new ModelAndView("checkorder");
		List<Address> list = new ArrayList<Address>();
		String hql = "from entity.Address where userid='"+c.getUserId()+"'" ;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Address>)query.list();
		//for(Product p:list){
		//	System.out.println(p.getName());
		//}
		session.flush();
		session.close();
		
		m.addObject("addresses",list);
		m.addObject("p",p);
		m.addObject("count",count);
		return m;	
	}
	
	@RequestMapping("/orderadd")	
	public ModelAndView orderAdd(@RequestParam(value="aid",required=false)int aid,@RequestParam(value="pid",required=false)int pid,HttpSession session){
		
		User u = (User)session.getAttribute("user");
		
		Cart c = csi.findCartByUserIdAndProductId(u.getUserId(), pid);
		Address a = asi.findAddressById(aid);
		
		osi.addOrder(a, c);
		csi.deleteCart(c.getId());
		ModelAndView m = new ModelAndView("orderview");
		List<Orders> orderlist = osi.findOrderByUserId(u.getUserId());
		Map productmap = new HashMap();
		for(Orders o:orderlist){
			Product p = psi.findProductById(o.getProductid());
			productmap.put(o.getId(), p);
		}
		m.addObject("p",productmap);
		m.addObject("order",orderlist);
		
		return m;
		
	}
	@RequestMapping("cancelorder/{oid}")
	public ModelAndView cancelOrder(@PathVariable int oid,HttpSession session){
		Orders o = osi.findOrderById(oid);
		o.setOrderStatus("订单已取消");
		osi.updateOrder(o);
		ModelAndView m = new ModelAndView("orderview");
		
		User u = (User)session.getAttribute("user");
		
		List<Orders> orderlist = osi.findOrderByUserId(u.getUserId());
		Map productmap = new HashMap();
		for(Orders order:orderlist){
			Product p = psi.findProductById(order.getProductid());
			productmap.put(order.getId(), p);
		}
		m.addObject("p",productmap);
		m.addObject("order",orderlist);
		return m ;
		
		
	}
	
	@RequestMapping("deleteorder/{oid}")
	public ModelAndView deleteOrder(@PathVariable int oid,HttpSession session){
		User u = (User)session.getAttribute("user");
		osi.deleteOrder(oid);	
		ModelAndView m = new ModelAndView("orderview");
		List<Orders> orderlist = osi.findOrderByUserId(u.getUserId());
		Map productmap = new HashMap();
		if(orderlist!=null){
			for(Orders order:orderlist){
				Product p = psi.findProductById(order.getProductid());
				productmap.put(order.getId(), p);
			}
			m.addObject("p",productmap);
			m.addObject("order",orderlist);
			System.out.println("用户"+u.getUserName()+"删除了id为"+oid+"的订单。");
			return m ;
		}else{
			System.out.println("用户"+u.getUserName()+"删除了id为"+oid+"的订单。");
			return m;
		}
		
		
	}
	@RequestMapping("adminorders")
	public ModelAndView adminOrders(){
		ModelAndView m = new ModelAndView("adminorders");
		
		
		List<Orders> orderlist = osi.findAllOrder();
		if(orderlist!=null){
			Map productmap = new HashMap();
			Map usermap = new HashMap();
			for(Orders order:orderlist){
				User u = usi.findUserById(order.getUserid());
				usermap.put(order.getId(), u);
				Product p = psi.findProductById(order.getProductid());
				productmap.put(order.getId(), p);
			}
			m.addObject("u",usermap);
			m.addObject("p",productmap);
			m.addObject("order",orderlist);
			return m ;
		}else{
			return m;
		}
		
	}
	@RequestMapping("searchorder")
	public ModelAndView searchOrder(@RequestParam(value="uid",required=false)int uid,@RequestParam(value="pid",required=false)int pid){
		List o = osi.findOrderByUserIdAndProductId(uid, pid);
		Product p = psi.findProductById(pid);
		ModelAndView m = new ModelAndView("ordersearchresult");
		m.addObject("o",o);
		m.addObject("p",p);
		return m ;
		
	}
	@RequestMapping("vieworder")
	public ModelAndView viewOrder(HttpSession session){
		User u = (User)session.getAttribute("user");
		ModelAndView m;
		if(u==null){
			m= new ModelAndView("loginfirst");
			return m;
		}else{
		m = new ModelAndView("orderview");
		List<Orders> orderlist = osi.findOrderByUserId(u.getUserId());
		Map productmap = new HashMap();
		for(Orders o:orderlist){
			Product p = psi.findProductById(o.getProductid());
			productmap.put(o.getId(), p);
		}
		m.addObject("u",u);
		m.addObject("p",productmap);
		m.addObject("order",orderlist);
		
		return m;
		}
		
		
	}
	
}
